package clasefpro;

/*
Calcular los números multiplos de 4 y de 10 (ambos) del 100 al 200 y calcular la multiplicación de los mismos.
*/

public class Problema1Yincana {
  public static void main(String[] args) {
    int multiplicacion = 1;

    for (int i = 100; i <= 200; i++) {
      if (i % 4 == 0 && i % 10 == 0) {
        multiplicacion *= i;
      }
    }

    System.out.println("La multiplicación de los números múltiplos de 4 y 10 del 100 al 200 es: " + multiplicacion);
  }
}
