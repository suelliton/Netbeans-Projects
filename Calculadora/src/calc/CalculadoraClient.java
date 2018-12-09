/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;
 
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Scanner;
 
class CalculadoraClient {
 
  public static void main(String args[]) throws Exception {
    URL url = new URL("http://127.0.0.1:9876/calc?wsdl");//chama o wsdl pra saber funcoes do server
    //define URI para acesso aos serviços do server(funcoes)
    QName qname = new QName("http://calc/","CalculadoraServerImplService");
    Service ws = Service.create(url, qname);//cria o serviço instanciando com o endereço do wsdl e do serviço
    CalculadoraServer calc = ws.getPort(CalculadoraServer.class);//cria obejto calculadora pegando do servidor
 
    Scanner scanner = new Scanner(System.in);
    int op = 0; 
    while(op!=5){
    System.out.println("Digite a opção desejada para\n 1-somar \n 2-subtrair \n 3-multiplicar \n 4-dividir \n 5-sair");
    op = scanner.nextInt();
    System.out.println("Digite 1* numero" );
    float n1 = scanner.nextFloat();
    System.out.println("Digite 2* numero" );
    float n2 = scanner.nextFloat();
    switch(op){
        case 1:
            System.out.println("Resultado " + calc.soma(n1,n2));
        break;
        case 2:
            System.out.println("Resultado" + calc.subtracao(n1,n2));
        break;
        case 3:
            System.out.println("Resultado " + calc.multiplicacao(n1,n2));
        break;
        case 4:
            System.out.println("Resultado " + calc.divisao(n1,n2));
        break;
        
    
    }}
    
    
    

    
 
  }
}