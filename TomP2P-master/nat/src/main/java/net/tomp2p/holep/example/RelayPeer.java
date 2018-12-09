package net.tomp2p.holep.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sctp4nat.connection.SctpDefaultStreamConfig;
import net.sctp4nat.core.SctpChannelFacade;
import net.sctp4nat.origin.SctpDataCallback;
import net.tomp2p.nat.PeerBuilderNAT;
import net.tomp2p.p2p.Peer;
import net.tomp2p.p2p.PeerBuilder;

public class RelayPeer extends AbstractPeer {

	private static final Logger LOG = LoggerFactory.getLogger(RelayPeer.class);

	protected final Peer peer;

	public RelayPeer(InetSocketAddress local) throws IOException {
		super(local);
		LOG.debug("start creating masterpeer...");
		peer = new PeerBuilder(masterPeerId).port(local.getPort()).start();
		new PeerBuilderNAT(peer).start();
		LOG.debug("masterpeer created! with peerId:" + peer.peerID());

		LOG.debug("now starting bootstrap...");

		peer.sctpDataCallback(new SctpDataCallback() {

			@Override
			public void onSctpPacket(byte[] data, int sid, int ssn, int tsn, long ppid, int context, int flags,
					SctpChannelFacade facade) {
				System.out.println("I WAS HERE");
				System.out.println("got data: " + new String(data, StandardCharsets.UTF_8));
				facade.send(data, new SctpDefaultStreamConfig());
			}
		});
	}
}
