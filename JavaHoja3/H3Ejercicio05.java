
/*
  Escribir el método Java necesario para completar el programa siguiente:
*/

import java.util.Scanner;
import java.lang.Math;

public class H3Ejercicio05 {
  public static void main(String[] args) {
    /* De polares a cartesianas, dim 2 */
    Scanner in = new Scanner(System.in);


    double angulo, radio;
    System.out.print("Escriba un ángulo (en radianes): ");
    angulo = in.nextDouble();
    System.out.print("y un radio (positivo): ");
    radio = in.nextDouble();
    in.close();
    System.out.println("Angulo " + angulo + ", radio " + radio);
    System.out.print("El punto en cartesianas es ");
    EscribirEnCartesianasLasPolares(radio, angulo); // SENTENCIA
    System.out.println();
  }
  public static void EscribirEnCartesianasLasPolares(double radio, double angulo) {
    double x = radio * Math.cos(angulo);
    double y = radio * Math.sin(angulo);
    System.out.print("(" + x + ", " + y + ")");
  }
}
