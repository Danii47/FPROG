/*
  * CONJETURA DE COLLATZ
  Tomemos un entero mayor que 1. Si es par, lo dividimos por 2. Si es impar, lo
  multiplicamos por 3 y le sumamos 1. Con el resultado volvemos a hacer lo mismo.
  Obtendremos así una sucesión de números. Sólo detendremos el proceso cuando aparezca
  el 1. (Por ejemplo: 6, 3, 10, 5, 16, 8, 4, 2, 1). El matemático Lothar Collatz conjeturó en
  1937 que para cualquier número siempre pararemos (aparecerá un 1), pero todavía no se
  ha podido demostrar ni refutar tal conjetura. Escriba un programa Java que lea un número
  entero mayor que 1 y muestre en pantalla la sucesión asociada, así como la longitud de la
  secuencia. Supóngase que ningún cálculo va a provocar desbordamiento.
*/

import java.util.Scanner;

public class H4Ejercicio05 {
  public static void main(String[] args) {
    
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce un número con el que aplicar la conjetura de Collatz:");
    long number = in.nextLong();
    int iterations = 0;
    in.close();
    while (number > 1) {
      if (number % 2 == 0) {
        System.out.println(number /= 2);
      } else {
        System.out.println(number = (number * 3) + 1);
      }
      iterations++;
    }

    System.out.println("El programa ha finalizado con " + iterations + " iteraciones. Te ha ganado Collatz ^^.");
  }
}

