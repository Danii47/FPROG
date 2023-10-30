
/*
Escriba un programa que pida números entre 0 y 10, que se supone que son notas de un
examen, y muestre en pantalla las notas máxima y mínima de la serie. Cada vez que el
usuario escribe una nota, se le pregunta si quiere introducir más, con respuesta 1 si quiere
introducir más u otro número si no. Suponga que el usuario escribe entradas correctas
(entre 0 y 10).
*/

import java.util.Scanner;

public class H4Ejercicio02 {
  public static void main(String[] args) {
    int min = 10;
    int max = 0;
    int note;
    boolean userContinue = true;

    Scanner in = new Scanner(System.in);

    while (userContinue) {
      System.out.println("Introduce una nota entera del 1 al 10");
      note = in.nextInt();
      if (note < min) min = note;
      if (note > max) max = note; 
      System.out.println("¿Quieres continuar? (1 -> Seguir; cualquier cosa -> Parar)");
      userContinue = (in.nextInt() == 1) ? true : false;
    }

    in.close();

    System.out.println("La nota más alta es: " + max + " y la nota más pequeña es: " + min + ".");

  }
}
