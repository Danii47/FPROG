/*
  Escribir un programa que calcule la diferencia entre las expresiones Java
  a*b/c y a*(b/c) siendo a, b y c entradas de tipo entero
*/

import java.util.Scanner;

public class Ejercicio7 {
  public static void main(String[] args) {
    System.out.println("Introduce tres números enteros para comprobar la diferencia entre a*b/c y a*(b/c): ");
    Scanner in = new Scanner(System.in);

    int numbers[] = new int[3];

    // Compruebo que los valores introducidos sean números enteros.
    for (int i = 0; i < numbers.length; i++) {
      if (!in.hasNextInt()) {
        System.out.println("El valor " + (i + 1) + " introducido no es un número entero.");
        in.close();
        return;
      }
      numbers[i] = in.nextInt();
    }


    in.close();

    System.out.println("a*b/c = " + numbers[0] * numbers[1] / numbers[2]);
    System.out.println("a*(b/c) = " + numbers[0] * (numbers[1] / numbers[2]));
  }
}
