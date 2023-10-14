
/*
  Escribir una función Java que devuelva el menor de tres números que recibe
  como parámetros.
*/

import java.util.Scanner;

public class Ejercicio10 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce 3 números enteros: ");
    int firstNumber = in.nextInt();
    int secondNumber = in.nextInt();
    int thirdNumber = in.nextInt();
    in.close();

    int min = getMin(firstNumber, secondNumber, thirdNumber);
    System.out.println("El menor de los 3 números es: " + min);
  }
  
  public static int getMin(int firstNumber, int secondNumber, int thirdNumber) {
    int min = firstNumber;
    if (secondNumber < min) {
      min = secondNumber;
    }
    if (thirdNumber < min) {
      min = thirdNumber;
    }
    return min;
  }

}
