/*
  Escribir un programa Java que muestre en pantalla el cuadrante (1, 2, 3 o 4)
  que alcanza un ángulo dado en grados.
*/

import java.util.Scanner;

public class Ejercicio9 {
  public static void main(String[] args) {
    
    System.out.println("Introduce un ángulo en grados: ");
    Scanner in = new Scanner(System.in);
    int angle = in.nextInt();
    in.close();
    int quadrant = ((angle % 360) / 90) + 1;
    System.out.println("El ángulo " + angle + "º está en el cuadrante " + quadrant + ".");
  }
}
