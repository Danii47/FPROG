package ProyectoFinal2024;

import java.util.Scanner;

public class FProject {
  public static void main(String[] args) {
    int opcion;
    char[][] tablero;

    Scanner in = new Scanner(System.in);

    do {
      opcion = menu(in);

      switch (opcion) {
        case 1:
          int[] dimensiones = pedirDimensiones(in);

          tablero = crearTablero(dimensiones[0], dimensiones[1]);

          for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
              System.out.print(tablero[i][j]);
            }
            System.out.println();
          }

          break;
        case 2:
          System.out.println("Cargar Partida");
          break;
        case 3:
          System.out.println("Ver Resultados");
          break;
        case 4:
          System.out.println("Salir");
          break;
      }
    } while (opcion != 4);


    in.close();
  }
  
  public static int menu(Scanner in) {
    String opcion;

    do {
      System.out.println("Bienvenido a ¡La Conquista!");
      System.out.println("---------------------------");
      System.out.println("1. Nuevo Juego");
      System.out.println("2. Cargar Partida");
      System.out.println("3. Ver Resultados");
      System.out.println("4. Salir");
      System.out.println("---------------------------");
      System.out.print("Elige una opción: ");

      opcion = in.nextLine();

      if (opcion.length() != 1 || opcion.charAt(0) < '1' || opcion.charAt(0) > '4') {
        System.out.println("ERROR: Opción incorrecta.");
      }

    } while (opcion.length() != 1 || opcion.charAt(0) < '1' || opcion.charAt(0) > '4');

    int numeroOpcion = opcion.charAt(0) - '0';

    return numeroOpcion;
  }

  public static int[] pedirDimensiones(Scanner in) {
    String filas, columnas;
    int numeroFilas, numeroColumnas;

    do {
      System.out.print("Introduce el número de filas de cuadrados: ");

      filas = in.nextLine();

      if (filas.length() != 1 || filas.charAt(0) < '1' || filas.charAt(0) > '5') {
        System.out.println("ERROR: Opción incorrecta.");
      }

    } while (filas.length() != 1 || filas.charAt(0) < '1' || filas.charAt(0) > '5');

    do {
      System.out.print("Introduce el número de columnas de cuadrados: ");

      columnas = in.nextLine();

      if (columnas.length() != 1 || columnas.charAt(0) < '1' || columnas.charAt(0) > '5') {
        System.out.println("ERROR: Opción incorrecta.");
      }

    } while (columnas.length() != 1 || columnas.charAt(0) < '1' || columnas.charAt(0) > '5');

    numeroFilas = filas.charAt(0) - '0';
    numeroColumnas = columnas.charAt(0) - '0';

    int[] dimensiones = { numeroFilas, numeroColumnas };

    return dimensiones;
  }
  
  public static char[][] crearTablero(int filas, int columnas) {
    char[][] tablero = new char[filas * 2 + 1][columnas * 4 + 1];

    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    int contador = 0; // 5 -> 5 * 4 + 1 = 21
                      // 4 -> 4 * 4 + 1 = 17 · A · B · C · D ·
                      // 3 -> 3 * 4 + 1 = 13

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero[i].length; j++) {
        if (i % 2 == 0) {
          if (j % 4 == 0) {
            tablero[i][j] = '·';
          } else if ((j - 1) % 2 == 0) {
            tablero[i][j] = ' ';
          } else {
            tablero[i][j] = caracteres.charAt(contador);
            contador++;
          }
        } else {
          if (j % 4 == 0) {
            tablero[i][j] = caracteres.charAt(contador);
            contador++;
          } else {
            tablero[i][j] = ' ';
          }
        }
        
      }
    }

    return tablero;
  }
}
