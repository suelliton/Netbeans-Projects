/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;
 
import javax.xml.ws.Endpoint;
 
public class CalculadoraServerPublish {
 
  public static void main(String[] args){
    //define url de acesso do serviço e coloca no ar  
    Endpoint.publish("http://127.0.0.1:9876/calc", new CalculadoraServerImpl());
  }
}