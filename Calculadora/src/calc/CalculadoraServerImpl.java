/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;
 
import javax.jws.WebService;
 
@WebService(endpointInterface = "calc.CalculadoraServer")//referencia a classe interface
public class CalculadoraServerImpl implements CalculadoraServer {
     
  @Override
  public float soma(float num1, float num2) {
    return num1 + num2;
  }
   
  @Override
  public float subtracao(float num1, float num2) {
    return num1 - num2;
  }
 
  @Override
  public float multiplicacao(float num1, float num2) {
    return num1 * num2;
  }
 
  @Override
  public float divisao(float num1, float num2) {
    return num1 / num2;
  }
 
}