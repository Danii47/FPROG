/*
  Escriba un método que devuelva el valor numérico entero de una cadena,
  imponiendo como precondición que la cadena argumento contenga solamente
  dígitos.
*/

import java.util.Scanner;
import java.lang.Math;

public class H6Ejercicio06 {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Introduce una cadena de caracteres:");
    String str = in.nextLine();

    in.close();

    int num = stringToInt(str);

    System.out.println("El valor numérico de la cadena '" + str + "' es " + num + ".");

  }

  public static int stringToInt(String str) {

    int num = 0;

    for (int i = str.length() - 1; i >= 0; i--) {
      num += (Character.getNumericValue(str.charAt(i)) * Math.pow(10, str.length() - 1 - i));
    }

    return num;

  }
}
