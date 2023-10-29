/*
  Escriba una funcion Java que devuelva el primer divisor mayor que 1 de un entero
  positivo. Utilice esta función en un programa Java que escriba en pantalla los factores
  primos de un número entero positivo.
*/

import java.util.Scanner;

public class H4Ejercicio06 {
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce un número para factorizar:");
    long numberToFactorice = in.nextLong();

    in.close();

    if (numberToFactorice <= 1) System.out.print("El número debe ser mayor de 1");
    else {

      System.out.print("El número " + numberToFactorice + " factorizado en primos es: ");
      
      while (numberToFactorice > 1) {
  
        long factor = firstDivisor(numberToFactorice);
        numberToFactorice /= factor;
        System.out.print(factor);
        if (numberToFactorice > 1) System.out.print("x");
        
      }

    }

  }

  public static long firstDivisor(long number) {

    for (long i = 2; i < number; i++) {
      if (number % i == 0) return i;
    }
    return number;

  }
}