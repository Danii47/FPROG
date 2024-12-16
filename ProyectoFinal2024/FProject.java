package ProyectoFinal2024;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FProject {

  final static char[] SIMBOLOS_CUADRITO = { '#', '*' };
  final static String RUTA_ARCHIVO_PARTIDA_GUARDADA = "./ProyectoFinal2024/ficheros/partidaGuardada.txt";
  final static String RUTA_ARCHIVO_RESULTADOS = "./ProyectoFinal2024/ficheros/resultados.txt";
  final static String[] NOMBRES_JUGADORES = { "Jugador 1", "Jugador 2" };

  public static void main(String[] args) {
    int opcion, maxJugadas, jugadas, turno;

    boolean guardarYSalir, otraPartida;

    int[] dimensiones, resultados;
    char[][] tablero;

    Scanner in = new Scanner(System.in);

    do {
      opcion = menu(in);

      switch (opcion) {
        case 1:
        case 2:

          do {
            if (opcion == 1) {

              dimensiones = pedirDimensiones(in);
              tablero = crearTablero(dimensiones[0], dimensiones[1]);
              maxJugadas = obtenerMaxJugadas(tablero);
              turno = Math.random() < 0.5 ? 0 : 1;

            } else {
              String[] datosPartidaGuardada = obtenerDatosFicheroPartidaGuardada();

              if (datosPartidaGuardada != null) {
                turno = datosPartidaGuardada[0].charAt(0) - '0';
                dimensiones = new int[] { (datosPartidaGuardada[1].length() - 1) / 4, (datosPartidaGuardada.length - 1) / 2 };
  
                tablero = crearTablero(dimensiones[0], dimensiones[1]);
  
                for (int i = 1; i < datosPartidaGuardada.length; i++) {
                  for (int j = 0; j < datosPartidaGuardada[i].length(); j++) {
                    tablero[i - 1][j] = datosPartidaGuardada[i].charAt(j);
                  }
                }
  
                maxJugadas = obtenerMaxJugadas(tablero);
              } else {
                dimensiones = pedirDimensiones(in);
                tablero = crearTablero(dimensiones[0], dimensiones[1]);
                maxJugadas = obtenerMaxJugadas(tablero);
                turno = Math.random() < 0.5 ? 0 : 1;
              }
            }

            jugadas = 0;

            do {
              dibujarTablero(tablero);
              guardarYSalir = colocarPalito(tablero, turno, in);

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
              resultados = obtenerResultados(tablero);
              mostrarPuntuacionesPartida(resultados);
              guardarResultados(dimensiones, resultados);
              otraPartida = preguntarOtraPartida(in);
              opcion = 1;
            } else {
              guardarPartida(tablero, turno);
              otraPartida = false;
            }

          } while (otraPartida);

          break;

        case 3:
          mostrarResultados();
          break;
        case 4:
          System.out.println("Hasta la próxima!");
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

  public static boolean colocarPalito(char[][] tablero, int turno, Scanner in) {
    String posicion;
    boolean guardarYSalir = true, letraEnTablero;

    do {
      System.out.print("[" + NOMBRES_JUGADORES[turno] + "] Próximo palito (** para guardar y salir): ");

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
        if ((tablero[i][j] >= 'A' && tablero[i][j] <= 'Z') || (tablero[i][j] >= 'a' && tablero[i][j] <= 'z') || (tablero[i][j] >= '0' && tablero[i][j] <= '9')) {
          maxJugadas++;
        }
      }
    }

    return maxJugadas;
  }

  public static int[] obtenerResultados(char[][] tablero) {
    int[] puntos = new int[NOMBRES_JUGADORES.length];

    for (int i = 1; i < tablero.length; i += 2) {
      for (int j = 2; j < tablero[i].length; j += 4) {
        for (int k = 0; k < SIMBOLOS_CUADRITO.length; k++) {
          if (tablero[i][j] == SIMBOLOS_CUADRITO[k]) {
            puntos[k]++;
          }
        }
      }
    }

    return puntos;
  }

  public static void mostrarPuntuacionesPartida(int[] resultados) {
    for (int i = 0; i < NOMBRES_JUGADORES.length; i++) {
      System.out.println(NOMBRES_JUGADORES[i] + ": " + resultados[i] + " cuadrado/s");
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

  public static void guardarResultados(int[] dimensiones, int[] resultados) {

    String contenido = "";

    try {
      Scanner archivoResultados = new Scanner(new File(RUTA_ARCHIVO_RESULTADOS));

      while (archivoResultados.hasNextLine()) {
        contenido += archivoResultados.nextLine() + "\n";
      }

      archivoResultados.close();
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: No se ha encontrado el fichero de resultados.");
    }

    try {
      FileWriter archivoResultados = new FileWriter(RUTA_ARCHIVO_RESULTADOS);
      String fechaActual = obtenerFechaActual();

      archivoResultados.write(fechaActual + " Tam: " + dimensiones[0] + "x" + dimensiones[1] + "\t");
      for (int i = 0; i < NOMBRES_JUGADORES.length; i++) {
        archivoResultados.write(NOMBRES_JUGADORES[i] + ": " + resultados[i]);
        if (i < NOMBRES_JUGADORES.length - 1) {
          archivoResultados.write(" vs. ");
        }
      }

      archivoResultados.write("\n" + contenido);

      archivoResultados.close();
    } catch (IOException e) {
      System.out.println("ERROR: No se ha encontrado el fichero de resultados.");

    }
  }

  public static String obtenerFechaActual() {
    Date fechaActual = new Date();

    SimpleDateFormat formato = new SimpleDateFormat("[E MMM dd HH:mm:ss z yyyy]");

    String fechaFormateada = formato.format(fechaActual);

    return fechaFormateada;
  }

  public static void mostrarResultados() {
    try {
      Scanner archivoResultados = new Scanner(new File(RUTA_ARCHIVO_RESULTADOS));

      if (!archivoResultados.hasNextLine()) {
        System.out.println("No hay resultados guardados.");
      } else {
        System.out.println();
        while (archivoResultados.hasNextLine()) {
          System.out.println(archivoResultados.nextLine());
        }
        System.out.println();
      }

      archivoResultados.close();
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: No se ha encontrado el fichero de resultados.");
    }
  }

  public static void guardarPartida(char[][] tablero, int turno) {
    try {
      FileWriter archivoPartidaGuardada = new FileWriter(RUTA_ARCHIVO_PARTIDA_GUARDADA);

      archivoPartidaGuardada.write(turno + "\n");

      for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
          archivoPartidaGuardada.write(tablero[i][j]);
        }

        archivoPartidaGuardada.write("\n");

      }

      archivoPartidaGuardada.close();

    } catch (IOException e) {
      System.out.println("ERROR: No se ha encontrado el fichero de partida guardada.");
    }
  }

  /**
   * Obtiene los datos del fichero de partida guardada.
   * 
   * Formato (Array<String> de 3 elementos):
   * - Turno (0 o 1)
   * - Tablero
   * 
   * @return Array con el primer elemento el turno y el resto las lineas del
   *         tablero
   */
  public static String[] obtenerDatosFicheroPartidaGuardada() {
    int lineas = 0;
    String[] datos = null;

    try {
      Scanner archivoLeerLineas = new Scanner(new File(RUTA_ARCHIVO_PARTIDA_GUARDADA));

      while (archivoLeerLineas.hasNextLine()) {
        archivoLeerLineas.nextLine();
        lineas++;
      }

      archivoLeerLineas.close();

      Scanner archivoPartidaGuardada = new Scanner(new File(RUTA_ARCHIVO_PARTIDA_GUARDADA));

      
      // Leer el turno (0 o 1)
      if (!archivoPartidaGuardada.hasNextLine()) {
        System.out.println("ERROR: El fichero de partida guardada está vacío.");
      } else {
        datos = new String[lineas]; // El máximo de filas del tablero es 11 (pongo 15 por si acaso)
        datos[0] = archivoPartidaGuardada.nextLine();
  
        // Leer el tablero
        int i = 1;
        while (archivoPartidaGuardada.hasNextLine()) {
          datos[i] = archivoPartidaGuardada.nextLine();
          i++;
        }
        archivoPartidaGuardada.close();
      }
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: No se ha encontrado el fichero de partida guardada.");
    }

    return datos;
  }
}
