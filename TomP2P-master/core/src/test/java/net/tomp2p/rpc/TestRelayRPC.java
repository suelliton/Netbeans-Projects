package net.tomp2p.rpc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import net.sctp4nat.core.SctpChannelFacade;
import net.sctp4nat.origin.Sctp;
import net.sctp4nat.origin.SctpDataCallback;
import net.tomp2p.connection.DefaultConnectionConfiguration;
import net.tomp2p.futures.BaseFutureAdapter;
import net.tomp2p.futures.FutureDone;
import net.tomp2p.message.Message;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerBuilder;
import net.tomp2p.peers.Number160;
import net.tomp2p.peers.PeerAddress;
import net.tomp2p.peers.PeerSocketAddress;
import net.tomp2p.rpc.RelayRPC;
import net.tomp2p.utils.Pair;
import net.tomp2p.utils.Triple;

public class TestRelayRPC {
	
	@Rule
    public TestRule watcher = new TestWatcher() {
	   protected void starting(Description description) {
          System.out.println("Starting test: " + description.getMethodName());
       }
    };
	
	@Test
	public void testSetupRelay() throws InterruptedException {
		Sctp.getInstance().init();
		Peer behindNAT1Peer = null;
		Peer behindNAT2Peer = null;
		Peer relayPeer = null;
		try {
			PeerBuilder pb1 = new PeerBuilder(new Number160("0x1234")).p2pId(55).enableMaintenance(false).port(1234);
			//pb1.behindFirewall(true);
			behindNAT1Peer = pb1.start();
			RelayRPC behindNAT1Relay = new RelayRPC(behindNAT1Peer);
			DirectDataRPC d1 = new DirectDataRPC(behindNAT1Peer.peerBean(), behindNAT1Peer.connectionBean());
			
			PeerBuilder pb2 = new PeerBuilder(new Number160("0x9876")).p2pId(55).enableMaintenance(false).port(9876);
			//pb2.behindFirewall(true);
			behindNAT2Peer = pb2.start();
			
			RelayRPC behindNAT2Relay = new RelayRPC(behindNAT2Peer);
			DirectDataRPC d2 = new DirectDataRPC(behindNAT2Peer.peerBean(), behindNAT2Peer.connectionBean());
			
			relayPeer = new PeerBuilder(new Number160("0x5665")).p2pId(55).enableMaintenance(false).port(5665).start();
			RelayRPC relayRelay = new RelayRPC(relayPeer);
			
			//add relay info to peer 2
			PeerAddress pa = behindNAT2Peer.peerAddress();
			Collection<PeerSocketAddress> relays = new ArrayList<>();
			relays.add(relayPeer.peerAddress().ipv4Socket());
			behindNAT2Peer.peerBean().serverPeerAddress(pa.withReachable4UDP(false).withPortPreserving(true).withRelays(relays));
			
			
			//peer2 behind nat needs to have a relay
			
			FutureDone<Message> f = behindNAT2Relay.sendSetupMessage(relayPeer.peerAddress()).element0();
			f.awaitUninterruptibly();
			Assert.assertTrue(f.isSuccess());
			
			Pair<FutureDone<Message>, FutureDone<SctpChannelFacade>> t = 
					d1.send(behindNAT2Peer.peerAddress(), new DefaultConnectionConfiguration());
			FutureDone<Message> holeP = t.element0().awaitUninterruptibly();
			Assert.assertTrue(holeP.isSuccess()); //there is no firewall even though we said so
			
			d2.dataCallbackSCTP(new SctpDataCallback() {
				
				@Override
				public void onSctpPacket(byte[] data, int sid, int ssn, int tsn, long ppid, int context, int flags,
						SctpChannelFacade facade) {
					System.err.println("got "+data.length);
					facade.send(new byte[201], true, 0, 0);
				}
			});
			
			CountDownLatch l = new CountDownLatch(1);
			t.element1().addListener(new BaseFutureAdapter<FutureDone<SctpChannelFacade>>() {

				@Override
				public void operationComplete(FutureDone<SctpChannelFacade> future) throws Exception {
					future.object().setSctpDataCallback(new SctpDataCallback() {
						
						@Override
						public void onSctpPacket(byte[] data, int sid, int ssn, int tsn, long ppid, int context, int flags,
								SctpChannelFacade facade) {
							if(data.length==201) {
								l.countDown();
							}
							System.err.println("got "+data.length);
						}
					});
					System.err.println("sending 401 bytes /"+future.isSuccess());
					future.object().send(new byte[401], true, 0, 0);
				}
			});
			
			l.await();
		
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
            if (behindNAT1Peer != null) {
            	behindNAT1Peer.shutdown().await();
            }
            if (behindNAT2Peer != null) {
            	behindNAT2Peer.shutdown().await();
            }
            if (relayPeer != null) {
            	relayPeer.shutdown().await();
            }
        }
	}
}
