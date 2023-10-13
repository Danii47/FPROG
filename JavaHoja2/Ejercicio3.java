/*
  Escribir un programa Java que escriba en pantalla el doble del número
  obtenido invirtiendo el orden de las cifras del de entrada, siendo este positivo
  y de 3 cifras. (Ejemplo: para 123, escribirá 642).
*/

import java.util.Scanner;

public class Ejercicio3 {
  public static void main(String[] args) {

    System.out.println("Introduce un número para invertirlo y darte su doble: ");
    Scanner in = new Scanner(System.in);
    int number = in.nextInt();
    in.close();

    int numberToEdit = number;
    int inverted = 0;
    int rest;

    // Obtengo el módulo en base 10 del número y añado ese valor a la derecha del
    // número invertido. Posteriormente divido el número entre 10 para eliminar el
    // último número.
    while (numberToEdit > 0) {
      rest = numberToEdit % 10;
      inverted = inverted * 10 + rest;
      numberToEdit /= 10;
    }
    System.out.println("El número " + number + " al reves es: " + inverted + ". Por lo tanto, el doble del inverso es: " + (inverted * 2) + ".");
  }
}
