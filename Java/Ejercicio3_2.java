
/*
  Escribir un programa que, cuando se ejecute, escriba en pantalla un número
  entero entre 1 y 6 (como la tirada de un dado). Cada vez que se ejecute podrá
  dar un resultado distinto (use la función Math.random()).
*/

import java.lang.Math;

public class Ejercicio3_2 {
  public static void main(String args[]) {
    int number = (int) (Math.random() * 6) + 1;
    
    System.out.println("En el dado ha salido: " + number);
  }
}
