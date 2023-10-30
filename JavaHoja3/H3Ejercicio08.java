/*
  Escribir el método Java necesario para completar el programa siguiente:
*/

import java.util.Scanner;
import java.lang.Math;

public class H3Ejercicio08 {

  public static void main(String[] args) {
    /* De cartesianas a polares, dim 2 */
    Scanner in = new Scanner(System.in);

    double x, y;
    System.out.print("Escriba las 2 coordenadas de un punto: ");
    x = in.nextDouble();
    y = in.nextDouble();
    in.close();
    System.out.println("Punto (" + x + ", " + y + ")");
    System.out.print("En polares es: ");
    System.out.println("Radio= " + rad(x, y) + ", ángulo = " + ang(x, y)); // EXPRESIONES

  }

  public static double rad(double x, double y) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  public static double ang(double x, double y) {
    return Math.atan2(y, x);
  }
}
