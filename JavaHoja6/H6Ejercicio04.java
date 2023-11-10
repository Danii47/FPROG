/*
  Escriba un método que, dadas dos cadenas de caracteres, imprima en pantalla
  las posiciones en las que la segunda aparece en la primera. (Puede tener el
  mismo nombre que el del ejercicio 3).
*/

import java.util.Scanner;

public class H6Ejercicio04 {
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce una cadena de caracteres:");
    String str = in.nextLine();

    System.out.println("Introduce otra cadena de caracteres:");
    String str2 = in.nextLine();

    in.close();

    printStrPositions(str, str2);

  }

  public static void printStrPositions(String str, String str2) {
    int findString = 0;

    do {
      findString = str.indexOf(str2, findString);
      if (findString != -1) {
        System.out.println("La cadena '" + str2 + "' aparece en la posición " + findString + " de la cadena '" + str + "'.");
        findString += str2.length();
      }
    } while (findString != -1);

  }
}
