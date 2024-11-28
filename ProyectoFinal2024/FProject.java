package ProyectoFinal2024;

import java.util.Scanner;

public class FProject {

  final static char[] SIMBOLOS_CUADRITO = { '#', '*' };

  public static void main(String[] args) {
    int opcion, maxJugadas, jugadas, turno;

    boolean guardarYSalir, otraPartida;

    String[] jugadores = { "Jugador 1", "Jugador 2" };
    int[] dimensiones;
    char[][] tablero;

    Scanner in = new Scanner(System.in);

    do {
      opcion = menu(in);

      switch (opcion) {
        case 1:
        
        
        do {
            dimensiones = pedirDimensiones(in);
            tablero = crearTablero(dimensiones[0], dimensiones[1]);
            maxJugadas = obtenerMaxJugadas(tablero);
            jugadas = 0;
            turno = Math.random() < 0.5 ? 0 : 1;

            do {
              dibujarTablero(tablero);
              guardarYSalir = colocarPalito(tablero, jugadores[turno], turno, in);
              
              if (!guardarYSalir) {
                turno = turno == 0 ? 1 : 0;
                jugadas++;
              } else {
                System.out.println("Partida guardada.");
              }
            } while (!guardarYSalir && jugadas < maxJugadas);
  
            dibujarTablero(tablero);
  
            if (!guardarYSalir) {
              System.out.println("Juego Terminado!!");
              mostrarResultados(tablero, jugadores);
              otraPartida = preguntarOtraPartida(in);
            } else {
              // guardarPartida(tablero, jugadores, turno);
              otraPartida = false;
            }


          } while (otraPartida);

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
    int contador = 0;

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

  public static void dibujarTablero(char[][] tablero) {
    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero[i].length; j++) {
        System.out.print(tablero[i][j]);
      }
      System.out.println();
    }
  }

  public static boolean letraEnTablero(char[][] tablero, char letra) {
    boolean encontrada = false;

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero[i].length; j++) {
        if (tablero[i][j] == letra) {
          encontrada = true;
        }
      }
    }

    return encontrada;
  }

  public static boolean colocarPalito(char[][] tablero, String nombreJugador, int turno, Scanner in) {
    String posicion;
    boolean guardarYSalir = true, letraEnTablero;

    do {
      System.out.print("[" + nombreJugador + "] Próximo palito (** para guardar y salir): ");

      posicion = in.nextLine();

      letraEnTablero = letraEnTablero(tablero, posicion.charAt(0));

      if (posicion.length() != 1 || !letraEnTablero) {
        System.out.println("ERROR: Posición incorrecta.");
      }

    } while ((posicion.length() != 1 || !letraEnTablero) && !posicion.equals("**"));

    if (!posicion.equals("**")) {
      char caracter = posicion.charAt(0);

      for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
          if (tablero[i][j] == caracter) {
            if (i % 2 == 0) {
              tablero[i][j - 1] = '-';
              tablero[i][j] = '-';
              tablero[i][j + 1] = '-';
            } else {
              tablero[i][j] = '|';
            }
          }
        }
      }

      ponerCuadritos(tablero, turno);

      guardarYSalir = false;
    }

    return guardarYSalir;
  }

  public static void ponerCuadritos(char[][] tablero, int turno) {
    for (int i = 2; i < tablero.length; i += 2) {
      for (int j = 2; j < tablero[i].length; j += 4) {
        if (tablero[i][j] == '-' &&
            tablero[i - 1][j - 2] == '|' &&
            tablero[i - 1][j + 2] == '|' &&
            tablero[i - 2][j] == '-' &&
            tablero[i - 1][j] == ' ') {
          tablero[i - 1][j] = SIMBOLOS_CUADRITO[turno];
        }
      }
    }
  }

  public static int obtenerMaxJugadas(char[][] tablero) {
    int maxJugadas = 0;

    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero[i].length; j++) {
        if (tablero[i][j] != '·' && tablero[i][j] != ' ') {
          maxJugadas++;
        }
      }
    }

    return maxJugadas;
  }

  public static void mostrarResultados(char[][] tablero, String[] jugadores) {
    int[] puntos = { 0, 0 };

    for (int i = 1; i < tablero.length; i += 2) {
      for (int j = 2; j < tablero[i].length; j += 4) {
        if (tablero[i][j] == SIMBOLOS_CUADRITO[0]) {
          puntos[0]++;
        } else if (tablero[i][j] == SIMBOLOS_CUADRITO[1]) {
          puntos[1]++;
        }
      }
    }

    for (int i = 0; i < jugadores.length; i++) {
      System.out.println(jugadores[i] + ": " + puntos[i] + " cuadrado/s");
    }
  }
  
  public static boolean preguntarOtraPartida(Scanner in) {
    String respuesta;

    do {
      System.out.print("¿Quieres jugar otra partida? (s/n): ");

      respuesta = in.nextLine();

      if (respuesta.length() != 1 || (respuesta.charAt(0) != 's' && respuesta.charAt(0) != 'n')) {
        System.out.println("ERROR: Respuesta incorrecta.");
      }

    } while (respuesta.length() != 1 || (respuesta.charAt(0) != 's' && respuesta.charAt(0) != 'n'));

    return respuesta.charAt(0) == 's';
  }
}
