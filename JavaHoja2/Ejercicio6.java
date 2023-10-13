
/*
  Escribir un programa en Java que muestre el producto de un número de
  tres cifras (multiplicando) y otro de dos (multiplicador), mostrando los
  resultados parciales que suelen obtenerse cuando se hace la
  multiplicación a mano. Por ejemplo, si las entradas son 739 y 12, en la
  pantalla debe aparecer: 
  739 x 12
=
 739 x 10
 + 739 x 2
=
 7390
 + 1478
=
 8868 
*/

import java.util.Scanner;

public class Ejercicio6 {
  public static void main(String[] args) {
    System.out.println("Introduce un multiplicando y un multiplicador: ");
    Scanner in = new Scanner(System.in);
    int multiplicand = in.nextInt();
    int multiplier = in.nextInt();
    in.close();
    System.out.println("\n\n   " + multiplicand + " x " + multiplier);
    System.out.println("= ");

    System.out.println("      " + multiplicand + " x " + ((multiplier / 10) * 10));

    System.out.println("   +  " + multiplicand + " x " + (multiplier % 10));
    System.out.println("= ");

    System.out.println("     " + multiplicand * ((multiplier / 10) * 10));
    System.out.println("   + " + multiplicand * (multiplier % 10));
    int totalValue = multiplicand * ((multiplier / 10) * 10) + multiplicand * (multiplier % 10);
    System.out.println("= ");
    System.out.println("     " + totalValue);

  }
}
