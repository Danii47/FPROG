package LAB;

import java.util.Scanner;

/*
  Escribe un programa que construya una matriz de tamaño introducido por teclado (si el tamaño no es de al menos 1x1 el programa volverá a pedir
  de forma repetitiva un nuevo tamaño), la rellene de forma aleatoria con 3 valores: "Piedra", "Papel", "Tijera", y luego cuente cuántos
  valores hay de cada tipo.*
  NO SE PUEDE USAR NI equals(), euqlsIgnoreCase(), compareTo() ni compareToIgnoreCase().
*/

public class SegundoExamenLabTipoA_2024 {
  public static void main(String[] args) {
    int filas, columnas;

    Scanner in = new Scanner(System.in);

    do {
      System.out.print("Introduce el numero de filas: ");

      filas = in.nextInt();

      if (filas <= 0) {
        System.out.println("ERROR: El numero de filas debe ser estrictamente positivo.");
      }
    } while (filas <= 0);

    do {
      System.out.print("Introduce el numero de filas: ");

      columnas = in.nextInt();

      if (columnas <= 0) {
        System.out.println("ERROR: El numero de columnas debe ser estrictamente positivo.");
      }
    } while (columnas <= 0);

    in.close();

    String[][] matriz = new String[filas][columnas];
    String[] simbolos = { "Piedra", "Papel", "Tijera" };

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        matriz[i][j] = simbolos[(int) (Math.random() * 3)];
      }
    }

    int piedra = 0, papel = 0, tijera = 0;

    // NO SE PUEDE USAR NI equals(), equalsIgnoreCase(), compareTo() ni compareToIgnoreCase().

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz[i].length; j++) {
        if (matriz[i][j].indexOf("Piedra") != -1) { // Tambien: if (matriz[i][j].indexOf("Piedra") != -1)
          piedra++;
        } else if (matriz[i][j].indexOf("Tijera") != -1) { // Tambien: if (matriz[i][j].indexOf("Tijera") != -1)
          tijera++;
        } else {
          papel++;
        }
      }
    }

    System.out.println("Piedra: " + piedra);
    System.out.println("Papel: " + papel);
    System.out.println("Tijera: " + tijera);
  }
}
