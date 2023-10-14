
/*
  Escribir un método Java que tome tres valores positivos como parámetros y
  devuelva True o False según sea posible o no formar un triángulo con segmentos
  de esos valores. (Por ejemplo, si los valores son 12, 1, 2, no se podrá formar un
  triángulo con lados de esas longitudes). Para que pueda formarse, cada una de
  las longitudes tiene que ser menor que la suma de las otras dos.
*/

import java.util.Scanner;

public class Ejercicio11 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce 3 números enteros positivos: ");
    int firstNumber = in.nextInt();
    int secondNumber = in.nextInt();
    int thirdNumber = in.nextInt();
    in.close();

    if (firstNumber < 0 || secondNumber < 0 || thirdNumber < 0) {

      System.out.println("Alguno de los números introducidos no es positivo");

    } else {

      boolean isTriangle = isTriangle(firstNumber, secondNumber, thirdNumber);

      if (isTriangle) {
        System.out.println("Se puede formar un triángulo con los 3 números introducidos.");
      } else {
        System.out.println("No se puede formar un triángulo con los 3 números introducidos.");
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
