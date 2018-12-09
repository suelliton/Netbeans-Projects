/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote1;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author suelliton
 */
@WebService(serviceName = "Calculadora")
@Stateless()

public class Calculadora{

   
    public double somar(double num1, double num2) {
       return num1+num2;
    }

    public double subtracao(double num1, double num2) {
        return num1-num2;
    }

    public double multiplicacao(double num1, double num2) {
       return num1*num2;
    }

    public double divisao(double num1, double num2) {
       return num1/num2;
    }

  
}
