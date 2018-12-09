

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suelliton
 */
public class ConversorClient {
    
  public static void main(String args[]) throws Exception {
   
    String celsius = "25";
    String result = celsiusToFahrenheit(celsius);
    System.out.println("De celsius para Fahrenheit "+result);
    
    String  fahrenheit= "90";
    String result2 = fahrenheitToCelsius(fahrenheit);
    System.out.println("De Fahrenheit para celsius "+result2);
    
  }

    private static String celsiusToFahrenheit(java.lang.String celsius) {
        https.www_w3schools_com.xml.TempConvert service = new https.www_w3schools_com.xml.TempConvert();
        https.www_w3schools_com.xml.TempConvertSoap port = service.getTempConvertSoap();
        return port.celsiusToFahrenheit(celsius);
    }

    private static String fahrenheitToCelsius(java.lang.String fahrenheit) {
        https.www_w3schools_com.xml.TempConvert service = new https.www_w3schools_com.xml.TempConvert();
        https.www_w3schools_com.xml.TempConvertSoap port = service.getTempConvertSoap();
        return port.fahrenheitToCelsius(fahrenheit);
    }
    
}
