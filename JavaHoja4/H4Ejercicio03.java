/*
  Escriba un programa que pida 12 números entre 0 y 10, que se supone que son notas de un
  examen, y muestre en pantalla la nota media de la serie. Suponga que el usuario escribe
  entradas correctas (entre 0 y 10).
*/

import java.util.Scanner;

public class H4Ejercicio03 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int numbersSum = 0;
    int totalNumbers = 12;

    System.out.println("Introduce " + totalNumbers + " notas para calcular la media:");
    for (int i = 0; i < totalNumbers; i++) {
      System.out.print("Nota " + (i + 1) + ": ");
      numbersSum += in.nextInt();
    }

    in.close();

    float average = (float) numbersSum / totalNumbers;
    System.out.println("La media de los " + totalNumbers + " es " + average);
  }
}
