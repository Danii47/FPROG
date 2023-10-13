
/*
  Escribir un programa Java que calcule el valor de la suma de dos ángulos
  expresados como 3 enteros que representan grados, minutos y segundos,
  expresada de la misma manera (los grados en [0, 360)).
*/

import java.util.Scanner;

public class Ejercicio5 {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Introduce el primer ángulo en grados, minutos y segundos: ");
    int firstAngle[] = { in.nextInt(), in.nextInt(), in.nextInt() };

    System.out.println("Introduce el segundo ángulo en grados, minutos y segundos: ");
    int secondAngle[] = { in.nextInt(), in.nextInt(), in.nextInt() };

    in.close();

    int totalDegrees, totalMinutes, totalSeconds;
    totalSeconds = firstAngle[2] + secondAngle[2];
    totalMinutes = (firstAngle[1] + secondAngle[1]) + (totalSeconds / 60);
    totalSeconds = totalSeconds % 60;
    totalDegrees = (firstAngle[0] + secondAngle[0]) + (totalMinutes / 60);
    totalMinutes = totalMinutes % 60;

    totalDegrees = totalDegrees % 360;

    System.out.println("La suma de los ángulos es: " + totalDegrees + "º " + totalMinutes + "' " + totalSeconds + "''.");
  }
}