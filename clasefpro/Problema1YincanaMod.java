package clasefpro;

/*
Calcular los números primos del 100 al 200 y calcular la multiplicación de los mismos.
*/

public class Problema1YincanaMod {
  public static void main(String[] args) {
    long multiplicacion = 1;

    for (int i = 100; i <= 200; i++) {
      int j = 2;
      boolean primo = true;
      while (primo && j <= Math.sqrt(i)) {
        if (i % j == 0) {
          primo = false;
        } else {
          j++;
        }
      }

      if (primo) {
        System.out.println(i + " es primo.");
        multiplicacion *= i;
      }
    }

    System.out.println("La multiplicación de los números múltiplos de 4 y 10 del 100 al 200 es: " + multiplicacion);
  }
}
