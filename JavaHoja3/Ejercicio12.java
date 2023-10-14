/*
  Escribir un programa Java que lea tres valores positivos y escriba en pantalla el
  área de un triángulo cuyos lados tengan esas longitudes, o 0 si no es posible
  formarlo.
*/

import java.util.Scanner;

public class Ejercicio12 {
  
  public static void main(String[] args) {

    System.out.println("Introduce 3 números enteros positivos: ");
    Scanner in = new Scanner(System.in);
    int firstSide = in.nextInt();
    int secondSide = in.nextInt();
    int thirdSide = in.nextInt();
    in.close();

    if (firstSide < 0 || secondSide < 0 || thirdSide < 0) {

      System.out.println("Alguno de los números introducidos no es positivo");

    } else {

      if (!isTriangle(firstSide, secondSide, thirdSide)) {

        System.out.println("No se puede formar un triángulo con los 3 números introducidos.");
      } else {

        double semiPerimeter = (firstSide + secondSide + thirdSide) / 2.0;
        double area = Math.sqrt(semiPerimeter * (semiPerimeter - firstSide) * (semiPerimeter - secondSide) * (semiPerimeter - thirdSide)); // Formula de Herón

        System.out.println("El área del triángulo es: " + area);

      }
    }
  }

  public static boolean isTriangle(int firstNumber, int secondNumber, int thirdNumber) {

    boolean isTriangle = false;

    if (firstNumber < secondNumber + thirdNumber &&
        secondNumber < firstNumber + thirdNumber &&
        thirdNumber < firstNumber + secondNumber) {

      isTriangle = true;
    }

    return isTriangle;
  }
}
