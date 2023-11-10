/*
  Escriba un método que, dadas dos cadenas de caracteres, devuelva el número
  de veces que la segunda aparece en la primera. (Puede tener el mismo nombre
  que el del ejercicio 1).
*/

import java.util.Scanner;

public class H6Ejercicio03 {
  public static void main(String[] args) {
      
      Scanner in = new Scanner(System.in);
  
      System.out.println("Introduce una cadena de caracteres:");
      String str = in.nextLine();
  
      System.out.println("Introduce otra cadena de caracteres:");
      String str2 = in.nextLine();
  
      in.close();
  
      int times = countStrWithIndexOf(str, str2);
  
      System.out.println("La cadena '" + str2 + "' aparece " + times + " veces en la cadena '" + str + "'.");

  }

  public static int countStrWithIndexOf(String str, String str2) {
    int times = 0;
    int findString = 0;

    do {
      findString = str.indexOf(str2, findString);
      if (findString != -1) {
        times++;
        findString += str2.length();
      }
    } while (findString != -1);

    return times;

  }
}
