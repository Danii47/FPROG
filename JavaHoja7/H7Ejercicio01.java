/*
  Elaborar un método para normalizar un vector de números reales, con el criterio de que el
  nuevo vector resultante tenga módulo 1 (es decir, con la misma dirección pero tamaño 1).
*/

import java.util.Scanner;

public class H7Ejercicio01 {
  
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce la longitud del vector:");
    int longitud = in.nextInt();
    double[] vector = new double[longitud];
  
    for (int i = 0; i < vector.length; i++) {
      System.out.println("Introduce el valor de la posición " + (i + 1) + ":");
      vector[i] = in.nextDouble();
    }

    in.close();

    System.out.println("El módulo del vector es: " + getModule(vector));
    System.out.print("El vector unitario es: ");
    for (int i = 0; i < vector.length; i++) {
      System.out.print(getUnitVector(vector)[i] + " ");
    }

  }

  public static double getModule(double[] vector) {

    double module = 0;

    for (int i = 0; i < vector.length; i++) {
      module += Math.pow(vector[i], 2);
    }

    return Math.sqrt(module);
  }

  public static double[] getUnitVector(double[] vector) {

    double[] unitVector = new double[vector.length];
    double module = getModule(vector);

    for (int i = 0; i < vector.length; i++) {
      unitVector[i] = vector[i] / module;
    }

    return unitVector;
  }
}
