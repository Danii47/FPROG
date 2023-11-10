/*
  Escriba un método que devuelva una cadena que sea la inversa de su cadena
  argumento (como si se leyera de atrás a delante).
*/

import java.util.Scanner;

public class H6Ejercicio05 {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Introduce una cadena de caracteres:");
    String str = in.nextLine();

    in.close();

    String reverseString = reverseString(str);

    System.out.println("La cadena '" + str + "' invertida es '" + reverseString + "'.");

  }

  public static String reverseString(String str) {

    String reverseString = "";

    for (int i = str.length() - 1; i >= 0; i--) {
      reverseString += str.charAt(i);
    }
    
    return reverseString;

  }
}
