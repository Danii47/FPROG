/*
  Escribir un programa Java que escriba en pantalla las cifras del número 753
  en orden inverso.
*/

public class Ejercicio3 {
  public static void main(String[] args) {
    int number = 753, numberToEdit = number;
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

    System.out.println("El número " + number + " al reves es: " + inverted);
  }
}
