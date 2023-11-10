/*
  Escriba un procedimiento que imprima en pantalla las posiciones en las que un
  carácter aparece en una cadena de caracteres.
*/

import java.util.Scanner;

public class H6Ejercicio02 {
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce una cadena de caracteres:");
    String str = in.nextLine();

    System.out.println("Introduce un carácter:");
    char c = in.nextLine().charAt(0);

    in.close();

    printCharPositions(str, c);

  }

  public static void printCharPositions(String str, char c) {
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == c) System.out.println("El carácter '" + c + "' aparece en la posición " + i + " de la cadena.");
    }
  }
}
