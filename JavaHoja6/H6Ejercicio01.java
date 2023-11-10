/*
  Escriba una función que, dada una cadena de caracteres (String) y un carácter
  (char) devuelva el número de veces que el carácter aparece en la cadena.
*/

import java.util.Scanner;

public class H6Ejercicio01 {
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce una cadena de caracteres:");
    String str = in.nextLine();

    System.out.println("Introduce un carácter:");
    char c = in.nextLine().charAt(0);

    in.close();

    int times = countCharWithCharAt(str, c);

    System.out.println("El carácter '" + c + "' aparece " + times + " veces en la cadena.");

  }

  public static int countCharWithCharAt(String str, char c) {
    int times = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == c) times++;
    }
    return times;
  }

  public static int countCharWithIndexOf(String str, char c) {
    int times = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.indexOf(c, i) == i) times++;
    }
    return times;
  }
}
