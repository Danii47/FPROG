/*
  Escribir funciones para transformar grados en radianes y viceversa
*/

import java.util.Scanner;
import java.lang.Math;

public class H3Ejercicio07 {
  public static void main(String[] args) {
    System.out.println("Introduce un ángulo en grados para pasarlo a radianes: ");

    Scanner in = new Scanner(System.in);
    double degrees = in.nextDouble();
    System.out.println("El ángulo " + degrees + "º en radianes es: " + degreesToRadians(degrees));

    System.out.println("Introduce un ángulo en radianes para pasarlo a grados: ");
    double radians = in.nextDouble();
    System.out.println("El ángulo " + radians + " radianes en grados es: " + radiansToDegrees(radians));
    
    in.close();
  }

  public static double degreesToRadians(double degrees) {
    return degrees * (Math.PI / 180);
  }

  public static double radiansToDegrees(double radians) {
    return radians * (180 / Math.PI);
  }
}
