/*
  Escribir un programa Java que pida el usuario un número positivo y escriba en
  pantalla sus raíces cuadrada, cúbica, cuarta, y quinta.
*/

import java.util.Scanner;
import java.lang.Math;

public class Ejercicio1 {
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);
    double number = -1;
    while (number < 0) {
      System.out.print("Introduce un número positivo: ");
      if (in.hasNextDouble()) {
        number = in.nextDouble();
      }
      in.nextLine();
    }
    in.close();

    System.out.println("\n> Raíz cuadrada: " + Math.sqrt(number) + "\n> Raíz cúbica: " + Math.cbrt(number) + "\n> Raíz cuarta: " + Math.pow(number, 1.0/4.0) + "\n> Raíz quinta: " + Math.pow(number, 1.0/5.0));
  }  
}
