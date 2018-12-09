
import com.cdyne.ws.IPInformation;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suelliton
 */
public class CorreiosCliente {
        public static void main(String args[]){
        IPInformation ip =  resolveIP("192.169.3.132", "");
        System.out.print(ip.toString());
        
        
        
        }

    private static IPInformation resolveIP(java.lang.String ipAddress, java.lang.String licenseKey) {
        com.cdyne.ws.IP2Geo service = new com.cdyne.ws.IP2Geo();
        com.cdyne.ws.IP2GeoSoap port = service.getIP2GeoSoap();
        return port.resolveIP(ipAddress, licenseKey);
    }
        
        
}
