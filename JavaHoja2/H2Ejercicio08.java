/*
  Escribir un programa Java que muestre en pantalla el número de vueltas de
  un ángulo dado en grados.
*/

import java.util.Scanner;

public class H2Ejercicio08 {
  public static void main(String[] args) {

    System.out.println("Introduce un ángulo en grados: ");
    Scanner in = new Scanner(System.in);
    int angle = in.nextInt();
    in.close();

    int turns = angle / 360;

    System.out.println("El ángulo " + angle + "º da " + turns + " vueltas y sobran " + angle % 360 + " grados.");
  }
}
