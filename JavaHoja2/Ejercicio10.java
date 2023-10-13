/*
  Escribir un programa Java que muestre en pantalla el equivalente en radianes
  del ángulo dado en grados.
*/

import java.util.Scanner;
import java.lang.Math;

public class Ejercicio10 {
  public static void main(String[] args) {

    System.out.println("Introduce un ángulo en grados: ");
    Scanner in = new Scanner(System.in);
    int angle = in.nextInt();
    in.close();

    double angleInRadians = (angle * 2 * Math.PI) / 360;

    System.out.println("El ángulo " + angle + "º equivale a " + angleInRadians + " radianes.");
  }
}
