/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote1;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author suelliton
 */
    
import javax.xml.ws.WebServiceRef;

public class Cliente {
    @WebServiceRef(wsdlLocation="http://localhost:8080/Calculadora/Calculadora?xsd=1")
    static Calculadora service;

    public static void main(String[] args) {
        try {
            Cliente client = new Cliente();
            client.doTest(args);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doTest(String[] args) {
        try {
            System.out.println("Retrieving the port from the following service: " + service);
            Calculadora port = service.somar(0.0, 0.0);
            System.out.println("Invoking the sayHello operation   on the port.");

            String name;
            if (args.length > 0) {
                name = args[0];
            } else {
                name = "No Name";
            }

            String response = port;
            System.out.println(response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
    
    
 
