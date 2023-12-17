import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// https://fsymbols.com/generators/wide/
// TODO: Explicar previousTable(), pushTable() y comprobateTable()
// TODO: Puntuaciones guardar en archivo con nombre y contraseña?
// TODO: COMENTAR

public class FProject {

  /**
   * Valores de las casillas del tablero.
   */
  public static final int[] symbolNumbers = { 0, 5, 7, 8 };

  /**
   * Simbolos del tablero.
   */
  public static final char[] symbols = { ' ', 'x', 'o', '?' };

  /**
   * Valores de las casillas del tablero inicial.
   */
  public static final int[] symbolsStartNumbers = { 4, 3 };
  /**
   * Simbolos del tablero inicial.
   */
   public static final char[] symbolsStart = { 'X', 'O' };

  /**
   * Valores de las casillas del tablero que serán usadas para operar.
   */
  public static final int[] defaultSymbolNumbers = { 0, 2, 1, 5 };

  /**
   * La diferencia en ASCII entre una mayúscula y una minúscula.
   * Usado para convertir mayúsculas a minúsculas y viceversa.
   */
  public static final int valueToChangeCase = 'a' - 'A';
  /**
   * Anchura del tablero.
   */
  public static final int width = 6;
  /**
   * Altura del tablero.
   */
  public static final int height = 6;

  /**
   * Ruta del archivo de tableros.
   */
  public static final String tablesFilePath = "./ProyectoFinal/tableros.txt";
  /**
   * Ruta del archivo de partida guardada.
   */
  public static final String tableSavedFilePath = "./ProyectoFinal/saveGame.txt";

  /**
   * Ruta del archivo de puntuaciones.
   */
  public static final String rankingFilePath = "./ProyectoFinal/ranking.txt";

  /**
   * 
   * Metodo principal del programa.
   * 
   * @param args Argumentos de la línea de comandos.
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    String startTableString;

    int table[][];
    int startTable[][];
    int solvedTables[][][];
    int tables[][][] = new int[1][height][width];

    int winGames = 0;
    int playedGames = 0;

    double winPercentage;

    boolean finishGame = false;
    boolean leftGame = false;

    String playedTables[] = new String[0];
    String userInput;

    while (!finishGame) {

      startTableString = getRandomTableString(playedTables);
      table = createTable(width, height, startTableString, true);
      startTable = createTable(width, height, startTableString, true);
      tables[0] = startTable;

      if (savedGame() && continueSavedGame(in)) {

        String[] savedGameInfo = getSavedGameInfo();

        if (isValidGameInfo(savedGameInfo)) {

          String passwordSavedEncrypted = savedGameInfo[savedGameInfo.length - 1];

          if (comprobatePassword(passwordSavedEncrypted, in)) {

            System.out.println("Contraseña correcta, cargando tablero...");

            int heightSaved = savedGameInfo[0].charAt(0) - '0';
            int widthSaved = savedGameInfo[1].charAt(0) - '0';

            startTableString = savedGameInfo[2];
            startTable = createTable(widthSaved, heightSaved, startTableString, false);
            tables[0] = startTable;

            for (int i = 3; i < savedGameInfo.length - 1; i++) {
              tables = pushTable(createTable(widthSaved, heightSaved, savedGameInfo[i], false), tables);
            }

            table = tables[tables.length - 1];

          }

        }
      }

      playedTables = pushStringValue(playedTables, startTableString);
      solvedTables = getSolvedTables(startTable);

      do {

        drawTable(table);

        System.out.print("Jugada: ");
        userInput = in.nextLine();

        switch (userInput) {
          case "s":
            leftGame = confirmExit(in);
            finishGame = leftGame;

            if (leftGame) {
              if (confirmSaveGame(in)) {

                saveTableGame(tables, askPassword(in));
              }
            }

            break;

          case "-":
            // Si tables es <= 1 significa que no hay más tableros hacía atras, solo el del
            // inicio de juego.
            if (tables.length > 1)
              tables = previousTable(table, tables);
            else
              System.out.println("No hay jugadas anteriores.");

            break;

          case "reiniciar":
            table = createTable(width, height, startTableString, true);
            tables = createArrayOfTables(startTable);
            break;

          case "?":
            table = getHint(table, solvedTables);
            break;

          case "":
            if (isFullTable(table)) {
              if (comprobateTable(table, true)) {
                System.out.println("¡Enhorabuena, has competado el tablero!");
                winGames++;
              }
              playedGames++;
              leftGame = true;

            } else {
              System.out.println("El tablero no está completo, ¡continua jugando!");
            }

            break;

          default:

            if (isValidPlay(table, startTable, userInput)) {
              table = insertPlay(table, userInput);
              tables = pushTable(table, tables);
            }

            break;
        }
      } while (!leftGame);

      if (!finishGame) {
        finishGame = wantStopPlaying(in);
        leftGame = finishGame;
      }

    }

    winPercentage = Math.round(((double) winGames / playedGames) * 10000) / 100;

    System.out.println("Partidas jugadas: " + playedGames);
    System.out.println("Partidas ganadas: " + winGames);
    System.out.println("Porcentaje de ganadas: " + winPercentage + "%");

    System.out.println();

    if (wantSaveUserData(in)) {
      saveUserData(in, playedGames, winGames);
    }

    in.close();
  }

  /**
   * 
   * Cuenta el número de lineas que hay en el archivo de tableros.
   * 
   * @param file Archivo de tableros.
   * @return Número de tableros que hay en el archivo.
   */
  public static int countFileLines(File file) {
    int lines = 0;

    try {
      Scanner readFile = new Scanner(file);

      while (readFile.hasNext()) {
        lines++;
        readFile.nextLine();
      }

      readFile.close();
    } catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el archivo.");
    }

    return lines;
  }

  /**
   * 
   * Actualiza el archivo de puntuaciones con los datos de la partida actual.
   * 
   * @param userName Nombre del usuario.
   * @param playedGames Número de partidas jugadas.
   * @param winGames Número de partidas ganadas.
   */
  public static void updateUserDataFile(String userName, int playedGames, int winGames) {
    File file = new File(rankingFilePath);

    try {
      file.createNewFile();

      Scanner readRanking = new Scanner(file);

      int lines = countFileLines(file);
      String ranking[] = new String[lines];

      for (int i = 0; i < ranking.length; i++) {
        ranking[i] = readRanking.nextLine();
      }

      boolean userFound = false;

      for (int i = 0; i < ranking.length && !userFound; i++) {

        if (ranking[i].equals(userName)) {
          userFound = true;

          int totalPlayedGamesNumber = 0;
          int totalWinGames = 0;

          for (int j = 0; j < ranking[i + 1].length(); j++) {

            if (ranking[i + 1].charAt(j) >= '0' && ranking[i + 1].charAt(j) <= '9') {
              totalPlayedGamesNumber = totalPlayedGamesNumber * 10 + (ranking[i + 1].charAt(j) - '0');
            }

          }

          for (int j = 0; j < ranking[i + 2].length(); j++) {

            if (ranking[i + 2].charAt(j) >= '0' && ranking[i + 2].charAt(j) <= '9') {
              totalWinGames = totalWinGames * 10 + (ranking[i + 2].charAt(j) - '0');
            }

          }

          ranking[i + 1] = totalPlayedGamesNumber + playedGames + "";
          ranking[i + 2] = totalWinGames + winGames + "";

        }
      }

      if (!userFound) {
        ranking = pushStringValue(ranking, userName);
        ranking = pushStringValue(ranking, playedGames + "");
        ranking = pushStringValue(ranking, winGames + "");
      }

      readRanking.close();

      PrintWriter writer = new PrintWriter(file);

      for (int i = 0; i < ranking.length; i++) {
        writer.println(ranking[i]);
      }

      writer.close();

    } catch (FileNotFoundException e) {
      System.out.println("No fue posible guardar la partida. El archivo no fue encontrado.");
    } catch (IOException e) {
      System.out.println("Ocurrió un error al crear el archivo. Puede deberse a que el archivo es de solo lectura.");
    }
  }

  /**
   * 
   * Comprueba si el tablero está completo.
   * 
   * @param passwordEncrypted Contraseña encriptada.
   * @param in Variable para entrada de teclado.
   * @return Valor booleano dependiendo de si la contraseña introducida por el usuario es correcta o no.
   */
  public static boolean comprobatePassword(String passwordEncrypted, Scanner in) {
    System.out.println("Introduce la contraseña para recuperar el tablero:");
    String password = in.nextLine();
    boolean isCorrectPassword = encryptPassword(password).equals(passwordEncrypted);

    while (!isCorrectPassword && !password.equals("c")) {
      System.out.println("Contraseña incorrecta, vuelve a intentarlo (c -> cancelar):");
      password = in.nextLine();
      isCorrectPassword = encryptPassword(password).equals(passwordEncrypted);
    }

    if (password.equals("c"))
      System.out.println("Cancelando recuperación de partida...");

    return isCorrectPassword;
  }

  /**
   * 
   * Comprueba si el String del tablero tiene el formato adecuado.
   * 
   * @param tableString String del tablero.
   * @param height Altura del tablero.
   * @param width Anchura del tablero.
   * @return Valor booleano dependiendo de si el String del tablero tiene el formato adecuado.
   */
  public static boolean comprobateTableString(String tableString, int height, int width) {
    boolean isValidStringTable = true;

    if (tableString.length() != (height * width) + (height - 1))
      isValidStringTable = false;
      
    for (int j = 0; j < tableString.length() && isValidStringTable; j++) {

      if (j != 0 && (j + 1) % (width + 1) == 0) {
        if (tableString.charAt(j) != ' ') {
          isValidStringTable = false;
          System.out.println(isValidStringTable + " " + j);
        }

      } else if (tableString.charAt(j) < '0' || tableString.charAt(j) > '5') {
        isValidStringTable = false;
        System.out.println(isValidStringTable + " " + j);
        
      }
    }

    return isValidStringTable;
  }

  /**
   * 
   * Comprueba si las lineas del archivo de partida guardada tienen el formato adecuado.
   * 
   * @param savedGameInfo
   * @return
   */
  public static boolean isValidGameInfo(String[] savedGameInfo) {
    boolean isValidGameInfo = false;
    boolean isValidStringTable = true;

    String height = savedGameInfo[0];
    String width = savedGameInfo[1];

    if (savedGameInfo.length >= 4) {
      if (height.length() == 1 && height.charAt(0) >= '0' && height.charAt(0) <= '9') {
        if (width.length() == 1 && width.charAt(0) >= '0' && width.charAt(0) <= '9') {

          int heightNumber = height.charAt(0) - '0';
          int widthNumber = width.charAt(0) - '0';

          if (savedGameInfo[savedGameInfo.length - 1].length() >= 4) {

            for (int i = 2; i < savedGameInfo.length - 1 && isValidStringTable; i++) {

              isValidStringTable = comprobateTableString(savedGameInfo[i], heightNumber, widthNumber);

            }

            if (isValidStringTable)
              isValidGameInfo = true;

          }
        }
      }
    }

    if (!isValidGameInfo) {
      System.out.println("El archivo de guardado está corrupto.");
      System.out.println("Cancelando recuperación de partida...");
    }

    return isValidGameInfo;
  }

  /**
   * 
   * Pregunta al usuario si quiere jugar otro tablero
   * 
   * @param in Variable para entrada de teclado.
   * @return Valor booleano dependiendo de si el usuario quieres seguir jugando o no.
   */
  public static boolean wantStopPlaying(Scanner in) {

    String continuePlayingAnswer;
    boolean wantStopPlaying = false;

    do {

      System.out.println("¿Quieres jugar otro tablero? (SI/NO)");
      continuePlayingAnswer = myToLowerCase(in.nextLine());
      wantStopPlaying = (continuePlayingAnswer.equals("no")) ? true : false;

    } while (!continuePlayingAnswer.equals("si") && !continuePlayingAnswer.equals("no"));

    return wantStopPlaying;
  }

  /**
   * 
   * Pregunta al usuario el nombre y guarda su puntiación.
   * 
   * @param in Variable para entrada de teclado.
   * @param playedGames Número de partidas jugadas.
   * @param winGames Número de partidas ganadas.
   */
  public static void saveUserData(Scanner in, int playedGames, int winGames) {
    System.out.print("Introduce tu nombre (debe tener únicamente 3 caracteres): ");
    String userName = in.nextLine();

    while (userName.length() != 3) {
      System.out.print("El nombre debe tener únicamente 3 caracteres, vuelve a intentarlo: ");
      userName = in.nextLine();
    }

    updateUserDataFile(userName, playedGames, winGames);

  }

  /**
   * 
   * Pregunta al usuario por la contraseña para poder recuperar la partida.
   * 
   * @param in Variable para entrada de teclado.
   * @return Contraseña introducida por el usuario.
   */
  public static String askPassword(Scanner in) {
    System.out.println("Introduce una contraseña para poder recuperar la partida:");
    String password = in.nextLine();

    while (password.length() <= 3) {
      System.out.println("La contraseña debe tener más de 3 caracteres, vuelve a intentarlo:");
      password = in.nextLine();
    }

    return password;
  }

  /**
   * 
   * Pregunta al usuario si desea guardar o sobreescribir su puntuación.
   *
   * @param in Variable para entrada de teclado.
   * @return true si el usuario quiere guardar/sobreescribir su puntuación, false en caso contrario.
   */
  public static boolean wantSaveUserData(Scanner in) {
    String saveUserDataAnswer;
    boolean saveUserData;

    do {
      System.out.println("¿Quieres guardar/sobreescribir tu puntuación? (s -> sí | n -> cancelar)");
      saveUserDataAnswer = myToLowerCase(in.nextLine());

      saveUserData = (saveUserDataAnswer.equals("s")) ? true : false;

    } while (!saveUserDataAnswer.equals("s") && !saveUserDataAnswer.equals("n"));

    return saveUserData;
  }

  /**
   * 
   * Pregunta al usuario si quiere continuar la partida guardada.
   * 
   * @param in Variable para entrada de teclado.
   * @return Valor booleano dependiendo de si el usuario quiere continuar la
   *         partida guardada.
   */
  public static boolean continueSavedGame(Scanner in) {
    String continueSavedGameAnswer;
    boolean continueSavedGameAnswerBool = false;

    do {
      System.out.println("Se ha encontrado un tablero guardado, ¿quieres continuarlo? (s -> sí | n -> no)");
      continueSavedGameAnswer = myToLowerCase(in.nextLine());
    } while (!continueSavedGameAnswer.equals("s") && !continueSavedGameAnswer.equals("n"));

    if (continueSavedGameAnswer.equals("s"))
      continueSavedGameAnswerBool = true;

    return continueSavedGameAnswerBool;
  }

  /**
   * 
   * Obtiene a partir del archivo de tablero guardado un array de Strings con los
   * datos del tablero guardado.
   * En cada posición del array se almacena una fila del archivo (tablero con n
   * jugadas).
   * 
   * [0] -> Altura del tablero
   * [1] -> Anchura del tablero
   * [2] -> Tablero inicial
   * [3] -> Tablero jugada 1
   * [4] -> Tablero jugada 2
   * [n + 2] -> Tablero jugada n
   * [n + 3] -> Contraseña encriptada
   * 
   * @return Array de Strings con los datos del tablero guardado.
   */
  public static String[] getSavedGameInfo() {

    File file = new File(tableSavedFilePath);
    String[] savedGameInfo = new String[0];

    try {
      Scanner readFile = new Scanner(file);
      while (readFile.hasNext()) {
        savedGameInfo = pushStringValue(savedGameInfo, readFile.nextLine());
      }

      readFile.close();
    } catch (FileNotFoundException e) {
      System.out.println("No se ha encontrado el archivo de guardado.");
    }

    return savedGameInfo;

  }

  /**
   * 
   * Comprueba si hay algo en el tablero de partidas guardadas.
   * 
   * @return Valor booleano dependiendo de si se ha encontrado un archivo de
   *         guardado con algo escrito o no.
   */
  public static boolean savedGame() {
    File file = new File(tableSavedFilePath);
    boolean savedGame = false;

    try {

      Scanner readFile = new Scanner(file);
      if (readFile.hasNext())
        savedGame = true;

      readFile.close();
    } catch (FileNotFoundException e) {

    }

    return savedGame;
  }

  /**
   * 
   * Guarda el tablero de juego en un archivo de texto. El archivo se guarda con
   * el siguiente formato (tablero con n jugadas):
   * 
   * [0] -> Altura del tablero
   * [1] -> Anchura del tablero
   * [2] -> Tablero inicial
   * [3] -> Tablero jugada 1
   * [4] -> Tablero jugada 2
   * [n + 2] -> Tablero jugada n
   * [n + 3] -> Contraseña encriptada
   * 
   * @param tables   Array de matrices de juego.
   * @param password Contraseña para encriptar el archivo.
   */
  public static void saveTableGame(int[][][] tables, String password) {

    File file = new File(tableSavedFilePath);

    try {

      file.createNewFile();

      PrintWriter writer = new PrintWriter(file);

      writer.println(tables[0].length);
      writer.println(tables[0][0].length);
      for (int i = 0; i < tables.length; i++) {
        for (int row = 0; row < tables[i].length; row++) {
          for (int column = 0; column < tables[i][row].length; column++) {
            writer.print(tables[i][row][column]);
          }

          // Si no es la última fila de la matriz, añado un espacio en blanco para separar
          // las filas en el archivo.
          if (row != tables[i].length - 1)
            writer.print(" ");
        }
        writer.println();
      }

      writer.print(encryptPassword(password));

      writer.close();
    } catch (FileNotFoundException e) {
      System.out.println("No fue posible guardar la partida. El archivo no fue encontrado.");
    } catch (IOException e) {
      System.out.println("Ocurrió un error al crear el archivo.");
    }
  }

  /**
   * 
   * Encripta la contraseña pasada por parametro de la siguiente manera:
   * 1. Invierte el String de la contraseña.
   * 2. Rota cada caracter del String de la contraseña en tantas posiciones como
   * su longitud respecto al String "rotateString".
   * 
   * Ejemplo 1:
   * * Hola -> aloH (longitud 4)
   * * a + 4 = c (la suma es respecto el String rotateString en todos los casos)
   * * l + 4 = n
   * * o + 4 = q
   * * H + 4 = J
   * 
   * * Hola -> cnqJ
   * 
   * Ejemplo 2:
   * * Adios -> soidA (longitud 5)
   * * s + 5 = V
   * * o + 5 = R
   * * i + 5 = L
   * * d + 5 = G
   * * A + 5 = c
   * 
   * * Adios -> VRLGc
   * 
   * @param password Contraseña a encriptar.
   * @return Contraseña encriptada.
   */
  public static String encryptPassword(String password) {
    String encryptedPassword = "";
    String rotateString = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0246813579";

    for (int i = password.length() - 1; i >= 0; i--) {
      int rotatedIndex = (rotateString.indexOf(password.charAt(i)) + password.length()) % rotateString.length();
      encryptedPassword += rotateString.charAt(rotatedIndex);
    }

    return encryptedPassword;
  }

  /**
   * 
   * Da una pistas de la jugada que debería hacer el usuario. En caso de haber
   * algo mal en el tablero, lo indica.
   * 
   * @param table        Matriz del tablero.
   * @param solvedTables Array de matrices de posibles soluciones (por lo general
   *                     solo habrá una solución).
   * @return Devuelve el núevo tablero con el interrogante (?) de pista en la
   *         posición indicada.
   */
  public static int[][] getHint(int[][] table, int[][][] solvedTables) {

    int[][] newTable = new int[table.length][table[0].length];
    int[] lastHintCoordenates = new int[2];

    for (int i = 0; i < lastHintCoordenates.length; i++)
      lastHintCoordenates[i] = -1;

    int[] sameValues = new int[solvedTables.length];

    for (int row = 0; row < table.length; row++) {
      for (int column = 0; column < table[0].length; column++) {
        if (table[row][column] == defaultSymbolNumbers[3]) {
          lastHintCoordenates[0] = row;
          lastHintCoordenates[1] = column;
        }

        newTable[row][column] = table[row][column];
      }
    }

    for (int i = 0; i < solvedTables.length; i++) {
      for (int row = 0; row < solvedTables[i].length; row++) {
        for (int column = 0; column < solvedTables[i][row].length; column++) {
          if (solvedTables[i][row][column] == newTable[row][column]) {
            sameValues[i]++;
          }
        }
      }
    }

    int maxValue = 0;
    // Tabla de solución con más valores iguales a lo que está haciendo el usuario
    // por si existiera más de una solución.
    int maxCoincidenceValueIndex = 0;

    for (int i = 0; i < sameValues.length; i++) {
      if (sameValues[i] > maxValue) {
        maxValue = sameValues[i];
        maxCoincidenceValueIndex = i;
      }
    }

    if (!isFullTable(newTable)) {
      int[] randomCoordenates = new int[2];

      do {
        randomCoordenates[0] = (int) (Math.random() * solvedTables[maxCoincidenceValueIndex].length);
        randomCoordenates[1] = (int) (Math.random() * solvedTables[maxCoincidenceValueIndex][0].length);

      } while (newTable[randomCoordenates[0]][randomCoordenates[1]] != 0);

      System.out.println(
          "Deberías poner una "
              + ((solvedTables[maxCoincidenceValueIndex][randomCoordenates[0]][randomCoordenates[1]] == 1)
                  ? symbolsStart[1]
                  : symbolsStart[0])
              +
              " en la fila " + (randomCoordenates[0] + 1) +
              " y columna " + ((char) ('A' + randomCoordenates[1])) +
              ".");

      newTable[randomCoordenates[0]][randomCoordenates[1]] = symbolsStart[2];

      if (lastHintCoordenates[0] != -1 && lastHintCoordenates[1] != -1)
        newTable[lastHintCoordenates[0]][lastHintCoordenates[1]] = 0;

    } else {
      boolean failFound = false;
      for (int row = 0; row < newTable.length && !failFound; row++) {
        for (int column = 0; column < newTable[0].length && !failFound; column++) {
          if (newTable[row][column] != solvedTables[maxCoincidenceValueIndex][row][column]) {
            failFound = true;
            System.out.println("Hay un error en la fila " + (row + 1) + " y columna " + ((char) ('A' + column)) + ".");
          }
        }
      }

      if (!failFound)
        System.out.println("No hay pista disponible.");

    }

    return newTable;
  }

  /**
   * 
   * Intenta colocar los valores que tienen que ir sí o sí en el tablero (Ej: si
   * hay dos X juntas debe poner una O a cada lado).
   * En caso de no poder colocar nada divide la matriz en dos matrices añadiendo
   * ambos valores y vuelve a intentar el algoritmo.
   * Así sucesivamente hasta que se encuentre una solución.
   * 
   * @param table Matriz del tablero.
   * @return Array de matrices de posibles soluciones (por lo general solo habrá
   *         una solución).
   */
  public static int[][][] getSolvedTables(int[][] table) {

    boolean[] sameTables = new boolean[1];
    sameTables[0] = true;

    int[][][] solvedTables = new int[1][table.length][table[0].length];
    for (int row = 0; row < table.length; row++) {
      for (int column = 0; column < table[0].length; column++) {

        solvedTables[0][row][column] = table[row][column];
      }
    }

    int xCont = 0;
    int oCont = 0;

    boolean allTablesFull = false;
    boolean itemSplited = false;

    while (!allTablesFull) {

      for (int i = 0; i < solvedTables.length; i++) {
        sameTables[i] = true;

        if (!isFullTable(solvedTables[i])) {

          for (int row = 0; row < solvedTables[i].length; row++) {

            for (int column = 0; column < solvedTables[i][0].length; column++) {

              if (solvedTables[i][row][column] == defaultSymbolNumbers[1] || solvedTables[i][row][column] == symbolsStartNumbers[0]) {
                xCont++;
              } else if (solvedTables[i][row][column] == defaultSymbolNumbers[2] || solvedTables[i][row][column] == symbolsStartNumbers[1]) {
                oCont++;
              }

              if (xCont >= solvedTables[i][0].length / 2 || oCont >= solvedTables[i][0].length / 2) {
                for (int auxColumn = 0; auxColumn < solvedTables[i][0].length; auxColumn++) {
                  if (solvedTables[i][row][auxColumn] == 0) {
                    solvedTables[i][row][auxColumn] = (xCont >= solvedTables[i][0].length / 2) ? 1 : 2;
                    sameTables[i] = false;
                  }
                }
              }

              if (column != solvedTables[i][0].length - 1) {

                if (solvedTables[i][row][column] % 2 == solvedTables[i][row][column + 1] % 2
                    && solvedTables[i][row][column] != 0 && solvedTables[i][row][column + 1] != 0) {

                  if (column != solvedTables[i][0].length - 2 && solvedTables[i][row][column + 2] == 0) {
                    solvedTables[i][row][column + 2] = (solvedTables[i][row][column] % 2 == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }
                  if (column != 0 && solvedTables[i][row][column - 1] == 0) {
                    solvedTables[i][row][column - 1] = (solvedTables[i][row][column] % 2 == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }

                }
                if (column != 0 && solvedTables[i][row][column - 1] % 2 == solvedTables[i][row][column + 1] % 2
                    && solvedTables[i][row][column] == 0
                    && solvedTables[i][row][column - 1] != 0
                    && solvedTables[i][row][column + 1] != 0) {
                  solvedTables[i][row][column] = (solvedTables[i][row][column - 1] % 2 == 1) ? 2 : 1;
                  sameTables[i] = false;
                }
              }
            }

            xCont = 0;
            oCont = 0;

          }

          for (int column = 0; column < solvedTables[i][0].length; column++) {

            for (int row = 0; row < solvedTables[i].length; row++) {

              if (solvedTables[i][row][column] == defaultSymbolNumbers[1] || solvedTables[i][row][column] == symbolsStartNumbers[0]) {
                xCont++;
              } else if (solvedTables[i][row][column] == defaultSymbolNumbers[2] || solvedTables[i][row][column] == symbolsStartNumbers[1]) {
                oCont++;
              }

              if (xCont >= solvedTables[i].length / 2 || oCont >= solvedTables[i].length / 2) {
                for (int auxRow = 0; auxRow < solvedTables[i].length; auxRow++) {
                  if (solvedTables[i][auxRow][column] == 0) {
                    solvedTables[i][auxRow][column] = (xCont >= solvedTables[0].length / 2) ? 1 : 2;
                    sameTables[i] = false;
                  }
                }
              }

              if (row != solvedTables[i].length - 1) {

                if (solvedTables[i][row][column] % 2 == solvedTables[i][row + 1][column] % 2
                    && solvedTables[i][row][column] != 0 && solvedTables[i][row + 1][column] != 0) {

                  if (row != solvedTables[i].length - 2 && solvedTables[i][row + 2][column] == 0) {
                    solvedTables[i][row + 2][column] = (solvedTables[i][row][column] % 2 == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }
                  if (row != 0 && solvedTables[i][row - 1][column] == 0) {
                    solvedTables[i][row - 1][column] = (solvedTables[i][row][column] % 2 == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }

                }
                if (row != 0 && solvedTables[i][row - 1][column] % 2 == solvedTables[i][row + 1][column] % 2
                    && solvedTables[i][row][column] == 0
                    && solvedTables[i][row - 1][column] != 0 && solvedTables[i][row + 1][column] != 0) {
                  solvedTables[i][row][column] = (solvedTables[i][row - 1][column] % 2 == 1) ? 2 : 1;
                  sameTables[i] = false;
                }

              }
            }

            xCont = 0;
            oCont = 0;
          }
        }

      }

      itemSplited = false;
      allTablesFull = true;

      for (int i = 0; i < solvedTables.length; i++) {

        if (!isFullTable(solvedTables[i])) {
          if (sameTables[i]) {
            for (int row = 0; row < solvedTables[i].length && !itemSplited; row++) {
              for (int column = 0; column < solvedTables[i][0].length && !itemSplited; column++) {
                if (solvedTables[i][row][column] == 0) {

                  solvedTables = toSplitMatrix(solvedTables, i, row, column);
                  sameTables = pushBooleanValue(sameTables, false);
                  itemSplited = true;
                }
              }
            }
          }

          allTablesFull = false;
        }
      }

      for (int i = solvedTables.length - 1; i >= 0; i--) {
        if (isFullTable(solvedTables[i]) && !comprobateTable(solvedTables[i], false)) {
          solvedTables = removeTable(solvedTables, i);
          sameTables = removeBooleanValue(sameTables, i);
        }
      }

    }

    return solvedTables;
  }

  /**
   * 
   * Elimina un valor de un array de booleanos.
   * 
   * @param array         Array de booleanos.
   * @param indexToRemove Índice del elemento a eliminar.
   * @return Array de booleanos con el elemento eliminado.
   */
  public static boolean[] removeBooleanValue(boolean[] array, int indexToRemove) {
    boolean[] newArray = new boolean[array.length - 1];

    for (int i = 0; i < newArray.length; i++) {
      if (i < indexToRemove) {
        newArray[i] = array[i];
      } else {
        newArray[i] = array[i + 1];
      }
    }

    return newArray;
  }

  /**
   * 
   * Añade un valor a un array de booleanos.
   * 
   * @param array Array de booleanos.
   * @param value Valor a añadir al array.
   * @return Array de booleanos con el valor añadido.
   */
  public static boolean[] pushBooleanValue(boolean[] array, boolean value) {
    boolean[] newArray = new boolean[array.length + 1];

    for (int i = 0; i < array.length; i++) {
      newArray[i] = array[i];
    }

    newArray[array.length] = value;

    return newArray;
  }

  /**
   * 
   * Añade un valor a un array de Strings.
   * 
   * @param array Array de Strings.
   * @param value Valor a añadir al array.
   * @return Array de Strings con el valor añadido.
   */
  public static String[] pushStringValue(String array[], String value) {
    String[] newArray = new String[array.length + 1];
    for (int i = 0; i < array.length; i++) {
      newArray[i] = array[i];
    }
    newArray[array.length] = value;
    return newArray;
  }

  /**
   * 
   * Elimina una matriz de un array de matrices.
   * 
   * @param tables        Array de matrices.
   * @param indexToRemove Índice de la matriz a eliminar.
   * @return Array de matrices con la matriz eliminada.
   */
  public static int[][][] removeTable(int[][][] tables, int indexToRemove) {

    int[][][] newTables = new int[tables.length - 1][tables[0].length][tables[0][0].length];

    for (int i = 0; i < newTables.length; i++) {
      if (i < indexToRemove) {
        newTables[i] = tables[i];
      } else {
        newTables[i] = tables[i + 1];
      }
    }

    return newTables;
  }

  /**
   * 
   * De una matriz de juego, generá dos matrices, una con una X y otra con una O
   * en la posición indicada.
   * 
   * @param tables        Array de matrices.
   * @param indexToSplit  Índice de la matriz a dividir.
   * @param rowToSplit    Fila de la matriz a dividir.
   * @param columnToSplit Columna de la matriz a dividir.
   * @return Array de matrices con la matriz dividida en dos.
   */
  public static int[][][] toSplitMatrix(int[][][] tables, int indexToSplit, int rowToSplit, int columnToSplit) {
    int[][][] newMatrixArray = new int[tables.length + 1][tables[0].length][tables[0][0].length];

    for (int i = 0; i < tables.length; i++) {
      newMatrixArray[i] = tables[i];
    }

    newMatrixArray[indexToSplit][rowToSplit][columnToSplit] = 1;

    for (int row = 0; row < tables[0].length; row++) {
      for (int column = 0; column < tables[0][0].length; column++) {
        if (row == rowToSplit && column == columnToSplit) {
          newMatrixArray[tables.length][row][column] = 2;
        } else {
          newMatrixArray[tables.length][row][column] = tables[indexToSplit][row][column];
        }
      }
    }

    return newMatrixArray;
  }

  /**
   * 
   * Convierte mayusculas a minusculas en un String.
   * 
   * @param str String a convertir a minúsculas.
   * @return String en minúsculas.
   */
  public static String myToLowerCase(String str) {

    String newStr = "";

    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
        newStr += (char) (str.charAt(i) + valueToChangeCase);
      else
        newStr += str.charAt(i);
    }
    return newStr;
  }

  /**
   * 
   * Crea un array de las matrices de juego, inicializando está en un array con el
   * tablero incial para ir añadiendo jugadas posteriormente.
   * 
   * @param table String codificado como indica el enunciado. (más explicado
   *              en createTable())
   * @return Array de matrices de juego
   */
  public static int[][][] createArrayOfTables(int[][] table) {
    int[][][] tables = { table };
    return tables;
  }

  /**
   * 
   * Añade una matriz de juego al array de matrices de juego.
   * 
   * @param table  Matriz del tablero actual.
   * @param tables Array de matrices de todos los tableros de juegos.
   * @return Array de matrices de todos los tableros de juegos añadiendo tablero
   *         (el
   *         último jugado).
   */
  public static int[][][] pushTable(int[][] table, int[][][] tables) {

    // Creo el nuevo Array de matrices de juegos. Este debe tener la longitud del
    // que ya había + 1.
    int[][][] newTables = new int[tables.length + 1][table.length][table[0].length];

    // Primero se copian los tableros que ya había en el nuevo array (es infiferente
    // hacer una copia de exhaustiva ya que tables va a ser reasignado)
    for (int i = 0; i < tables.length; i++) {

      newTables[i] = tables[i];

    }

    // Añado la nueva matriz del tablero actualizado en la ultima posición. (está
    // vez si hago una copia exhaustiva ya que tablero podría cambiar su valor
    // durante
    // la ejecucion)
    for (int row = 0; row < table.length; row++) {

      for (int column = 0; column < table[0].length; column++) {

        newTables[tables.length][row][column] = table[row][column];

      }
    }

    return newTables;
  }

  /**
   * 
   * Actualiza el valor de tablero al anterior jugado basandose en el array de
   * matrices de juego tables.
   * 
   * @param table  tablero actual, se usa para poder actualizarlo al anterior
   *               basandose en tables.
   * @param tables array de matrices de juego
   * @return array de matrices de juego quitando la última matriz
   */
  public static int[][][] previousTable(int[][] table, int[][][] tables) {

    int[][][] newTables = new int[tables.length - 1][table.length][table[0].length];

    // Replico el array de matrices tables en un nuevo array de matrices pero sin
    // añadir el último.
    for (int i = 0; i < newTables.length; i++) {

      for (int row = 0; row < table.length; row++) {

        for (int column = 0; column < table[0].length; column++) {

          newTables[i][row][column] = tables[i][row][column];

        }

      }

    }

    // Establezco los valores de tablero al úlimo elemento de newTables, que se
    // trata
    // del tablero de la anterior jugada
    for (int row = 0; row < table.length; row++) {

      for (int column = 0; column < table[0].length; column++) {

        table[row][column] = newTables[newTables.length - 1][row][column];

      }
    }

    return newTables;
  }

  /**
   * 
   * Comprueba si el tablero no tiene ningún espacio vacio.
   * 
   * @param table Matriz del tablero.
   * @return Valor booleano dependiendo de si ha encontrado casilla con un 0 o no.
   */
  public static boolean isFullTable(int[][] table) {

    boolean isFull = true;

    for (int row = 0; row < table.length && isFull; row++) {

      for (int column = 0; column < table[row].length && isFull; column++) {
        if (table[row][column] == 0)
          isFull = false;
      }

    }

    return isFull;
  }

  /**
   * 
   * Muestra un mensaje para confirmar que el usuario quiere abandonar el juego.
   * 
   * @param in Variable para entrada de teclado.
   * @return Valor booleano dependiendo de si el usuario confirma que quiere salir
   *         del juego.
   */
  public static boolean confirmExit(Scanner in) {

    System.out.println("Estas seguro de que quieres salir? (s -> sí | n -> cancelar)");
    String confirmExitStr = in.nextLine();

    boolean confirmExit = confirmExitStr.equals("s") ? true : false;

    if (confirmExit)
      System.out.println("¡Que te vaya bien!");

    return confirmExit;
  }

  /**
   * 
   * Confirmación para el usuario de si quiere guardar la partida o no.
   * 
   * @param in Variable para entrada de teclado.
   * @return Valor booleano dependiendo de si el usuario confirma que quiere
   *         guardar la partida.
   */
  public static boolean confirmSaveGame(Scanner in) {

    String confirmSaveGameStr;

    do {
      System.out.println("¿Quieres guardar la partida? (s -> sí | n -> no)");
      confirmSaveGameStr = myToLowerCase(in.nextLine());
    } while (!confirmSaveGameStr.equals("s") && !confirmSaveGameStr.equals("n"));

    boolean confirmSaveGame = confirmSaveGameStr.equals("s") ? true : false;

    return confirmSaveGame;
  }

  /**
   * 
   * Comprueba si la jugada introducida por el usuario es válida o no.
   * 1. Comprueba si la longitud de la jugada es 2.
   * 2. Comprueba si el primer caracter es un número entre 1 y el número de filas
   * del tablero.
   * 3. Comprueba si el segundo caracter es una letra entre a y la última letra de
   * la columna del tablero.
   * 4. Comprueba si la casilla está bloqueada (es una casilla del tablero
   * inicial).
   * 
   * @param table      Matriz del tablero.
   * @param startTable Matriz del tablero inicial.
   * @param userInput  Cadena de texto introducida por el usuario.
   * @return Valor booleano dependiendo de si la jugada es válida o no.
   */
  public static boolean isValidPlay(int[][] table, int[][] startTable, String userInput) {
    boolean validPlay = false;

    if (userInput.length() == 2) {

      char userInputNumber = userInput.charAt(0);
      char userInputCharacter = myToLowerCase(userInput).charAt(1);

      // La letra más lejana que se puede seleccionar depende de la longitud que tenga
      // la fila del array.
      int untilInputNumber = '1' + (table.length - 1);
      int untilInputCharacter = 'a' + (table[0].length - 1);

      if ((userInputNumber >= '1' && userInputNumber <= untilInputNumber)
          && (userInputCharacter >= 'a' && userInputCharacter <= untilInputCharacter)) {

        int userInputNumberInt = (userInputNumber - '0') - 1;
        int userInputCharacterInt = userInputCharacter - 'a';

        if (startTable[userInputNumberInt][userInputCharacterInt] == 0) {

          validPlay = true;

        } else {
          System.out.println("Casilla bloqueada, se trata de una casilla del tablero inicial.");
        }

      } else {
        System.out.println("Entrada erronea. Recuerda que la jugada debe ser una coordenada del tablero.");
      }
    } else {
      System.out.println("Entrada erronea. Recuerda que la jugada debe ser una coordenada del tablero con longitud 2.");
    }

    return validPlay;
  }

  /**
   * 
   * Actualiza la tabla con la decisión tomada por el usuario. Retorna la tabla ya
   * actualizada.
   * 
   * @param table     Matriz de juego.
   * @param userInput Cadena de texto introducida por el usuario.
   * @return Valor booleano dependiendo de si la jugada es válida o no.
   */
  public static int[][] insertPlay(int[][] table, String userInput) {

    int[][] newTable = new int[table.length][table[0].length];

    for (int row = 0; row < table.length; row++) {
      for (int column = 0; column < table[row].length; column++) {

        newTable[row][column] = table[row][column];

      }
    }

    char userInputNumber = userInput.charAt(0);
    char userInputCharacter = myToLowerCase(userInput).charAt(1);

    int userInputNumberInt = (userInputNumber - '0') - 1;
    int userInputCharacterInt = userInputCharacter - 'a';

    if (newTable[userInputNumberInt][userInputCharacterInt] == defaultSymbolNumbers[0] || newTable[userInputNumberInt][userInputCharacterInt] == defaultSymbolNumbers[3])
      newTable[userInputNumberInt][userInputCharacterInt] = defaultSymbolNumbers[1];

    else if (newTable[userInputNumberInt][userInputCharacterInt] == defaultSymbolNumbers[2])
      newTable[userInputNumberInt][userInputCharacterInt] = defaultSymbolNumbers[0];

    else if (newTable[userInputNumberInt][userInputCharacterInt] == defaultSymbolNumbers[1])
      newTable[userInputNumberInt][userInputCharacterInt] = defaultSymbolNumbers[2];
    

    if (isFullTable(newTable)) {
      System.out.println("Has llenado el tablero. Si quieres comprobar si has ganado, pulsa ENTER.");
    }

    return newTable;
  }

  /**
   * 
   * Comprueba si la matriz del juego es válida.
   * 1. Debe tener el mismo número de X's que de O's tanto por columna como por
   * filas.
   * 2. No puede haber más de 2 X's u O's contiguas.
   * 3. Tener todos los huecos con X u O.
   * 4. No puede haber ni 2 filas ni 2 columnas iguales.
   * 
   * @param table      Matriz rellenada del juego
   * @param notifyUser Valor booleano que indica si se debe mostrar un mensaje al
   *                   usuario o no.
   * 
   * @return Valor booleano dependiendo de si la matriz es válida o no.
   */
  public static boolean comprobateTable(int[][] table, boolean notifyUser) {

    int xCont = 0;
    int oCont = 0;
    int xAdjacentCont = 0;
    int oAdjacentCont = 0;
    int sameValueCont = 0;

    boolean validTable = true;

    // ! Comprobar filas

    for (int row = 0; row < table.length && validTable; row++) {

      for (int column = 0; column < table[row].length && validTable; column++) {

        if (table[row][column] == defaultSymbolNumbers[1] || table[row][column] == symbolsStartNumbers[0]) {
          xCont++;
          xAdjacentCont++;
          oAdjacentCont = 0;
        } else if (table[row][column] == defaultSymbolNumbers[2] || table[row][column] == symbolsStartNumbers[1]) {
          oCont++;
          oAdjacentCont++;
          xAdjacentCont = 0;
        } else {
          validTable = false;
        }

        if (xAdjacentCont > 2 || xAdjacentCont > 2) {
          if (notifyUser)
            System.out.println("En la posición " + (row + 1) + ((char) ('A' + column))
                + " hay un tercer simbolo contiguo en fila, ¡has perdido!");
          validTable = false;
        }

      }

      if (xCont != oCont) {
        if (notifyUser)
          System.out.println("La fila " + (row + 1) + " no tiene el mismo número de X y O, ¡has perdido!");
        validTable = false;
      }

      for (int auxRow = 0; auxRow < table.length && validTable; auxRow++) {

        for (int column = 0; column < table[row].length && validTable; column++) {

          if (row != auxRow && table[row][column] % 2 == table[auxRow][column] % 2)
            sameValueCont++;
        }

        if (sameValueCont >= table[row].length) {
          if (notifyUser)
            System.out.println("La fila " + (row + 1) + " y la fila " + (auxRow + 1) + " son iguales, ¡has perdido!");
          validTable = false;
        } else {
          sameValueCont = 0;
        }

      }

      xCont = 0;
      oCont = 0;
      xAdjacentCont = 0;
      oAdjacentCont = 0;
    }

    sameValueCont = 0;

    // ! Comprobar columnas
    for (int column = 0; column < table[0].length && validTable; column++) {

      for (int row = 0; row < table.length && validTable; row++) {

        if (table[row][column] == defaultSymbolNumbers[1] || table[row][column] == symbolsStartNumbers[0]) {
          xCont++;
          xAdjacentCont++;
          oAdjacentCont = 0;
        } else if (table[row][column] == defaultSymbolNumbers[2] || table[row][column] == symbolsStartNumbers[1]) {
          oCont++;
          oAdjacentCont++;
          xAdjacentCont = 0;
        } else {
          validTable = false;
        }

        if (oAdjacentCont > 2 || xAdjacentCont > 2) {
          if (notifyUser)
            System.out.println("En la posición " + (row + 1) + ((char) ('A' + column))
                + " hay un tercer simbolo contiguo en columna, ¡has perdido!");
          validTable = false;
        }
      }

      if (xCont != oCont) {
        if (notifyUser)
          System.out
              .println("La columna " + ((char) ('A' + column)) + " no tiene el mismo número de X y 0, ¡has perdido!");
        validTable = false;
      }

      for (int auxColumn = 0; auxColumn < table[0].length && validTable; auxColumn++) {

        for (int row = 0; row < table.length && validTable; row++) {

          if (column != auxColumn && table[row][column] % 2 == table[row][auxColumn] % 2)
            sameValueCont++;
        }

        if (sameValueCont >= table.length) {
          if (notifyUser)
            System.out.println(
                "La columna " + (column + 1) + " y la columna " + (auxColumn + 1) + " son iguales, ¡has perdido!");
          validTable = false;
        } else {
          sameValueCont = 0;
        }

      }

      xCont = 0;
      oCont = 0;
      xAdjacentCont = 0;
      oAdjacentCont = 0;
    }

    return validTable;

  }

  /**
   * 
   * Crea una matriz del ancho y alto pedidos para jugar al juego. Además, crea el
   * tablero predefinido a través del estilo de String explicado en el enunciado.
   * Funciona para cualquier tamaño siempre y cuando el String del tablero
   * mantenga el patrón.
   * 
   * @param width         Tamaño de ancho del tablero (número entero).
   * @param height        Tamaño de alto del tablero (número entero).
   * @param tableToCreate Linea de texto que marca el tablero a crear.
   *                      tableToCreate -> "002200 002000 000000 001000 200101
   *                      100001".
   *                      Las cadenas individuales tienen que tener la longitud
   *                      del ancho y tiene que haber tantas cadenas como altura
   * 
   * @return La matriz del tablero creado rellenada con ceros, unos y doses
   */
  public static int[][] createTable(int width, int height, String tableToCreate, boolean setStartGaps) {
    int[][] table = new int[height][width];
    int gapValue;

    for (int row = 0; row < table.length; row++) {

      for (int column = 0; column < table[row].length; column++) {

        gapValue = tableToCreate.charAt((row * (width + 1)) + column) - '0';

        if (setStartGaps)
          table[row][column] = gapValue == 0 ? gapValue : gapValue + 2;
        else
          table[row][column] = gapValue;

      }

    }

    return table;
  }

  /**
   * 
   * Dibuja la matriz del tablero en la terminal como indica el enunciado.
   * 
   * @param table Matriz del tablero
   */
  public static void drawTable(int[][] table) {

    char gapToDraw;

    System.out.print("  ");
    for (int i = 0; i < table[0].length; i++) {
      System.out.print(((char) ('A' + i)) + " ");
    }

    for (int row = 0; row < table.length; row++) {

      System.out.println();
      System.out.print((row + 1) + " ");

      for (int column = 0; column < table[row].length; column++) {

        if (table[row][column] == defaultSymbolNumbers[2]) {
          gapToDraw = symbols[2];
        } else if (table[row][column] == defaultSymbolNumbers[1]) {
          gapToDraw = symbols[1];
        } else if (table[row][column] == symbolsStartNumbers[1]) {
          gapToDraw = symbolsStart[1];
        } else if (table[row][column] == symbolsStartNumbers[0]) {
          gapToDraw = symbolsStart[0];
        } else if (table[row][column] == defaultSymbolNumbers[3]) {
          gapToDraw = symbols[3]; // En caso de que haya un 5, se trata de una pista y se dibuja un ?. Después se vuelve a poner a 0.
        } else {
          gapToDraw = symbols[0];
        }

        System.out.print(gapToDraw + " ");

      }
    }
    System.out.println();
  }

  /**
   * 
   * En caso de que el fichero de tableros no esté con 0, 1, 2 lo cambia para
   * poder operar con los números.
   * 
   * @param tableString String del tablero a cambiar.
   * @return String del tablero cambiado.
   */
  public static String changeValuesToDefaultValues(String tableString) {
    String newTableString = "";
    
    for (int i = 0; i < tableString.length(); i++) {
      int valueToChange = tableString.charAt(i) - '0';
      char newValue = ' ';

      if (valueToChange == symbolNumbers[0])
        newValue = (char) (defaultSymbolNumbers[0] + '0');

      else if (valueToChange == symbolNumbers[1])
        newValue = (char) (defaultSymbolNumbers[1] + '0');

      else if (valueToChange == symbolNumbers[2])
        newValue = (char) (defaultSymbolNumbers[2] + '0');
      

      newTableString += ("" + newValue);
    }

    return newTableString;
  }

  /**
   * 
   * Devuelve un String aleatorio de tablero de juego sacado de tableros.txt para
   * poder jugar. En caso de no encontrar el tablero devuelve uno por defecto.
   * 
   * @param playedTables Array de Strings con los tableros ya jugados.
   * 
   * @return String aleatorio de tablero de juego sacado de tableros.txt
   */
  public static String getRandomTableString(String playedTables[]) {
    File tablesFile = new File(tablesFilePath);

    // Tablero por defecto en caso de no encontrar el archivo
    String tableString = "002000 000000 100120 000100 000020 110200";

    try {
      Scanner readFile = new Scanner(tablesFile);

      int lines = countFileLines(tablesFile);

      // readFile.close();

      // readFile = new Scanner(tablesFile);

      String[] arrayLines = new String[lines];

      for (int i = 0; i < lines; i++) {
        arrayLines[i] = readFile.nextLine();
      }

      readFile.close();

      // Linea aleatoria del archivo, en caso de haber jugado todos los tableros se
      // vuelve a jugar uno aleatorio
      boolean isAlreadyPlayed = false;
      int random = (int) (Math.random() * lines);

      if (playedTables.length != lines) {
        do {
          random = (int) (Math.random() * lines);
          for (int i = 0; i < playedTables.length && !isAlreadyPlayed; i++) {
            if (playedTables[i].equals(arrayLines[random]))
              isAlreadyPlayed = true;
          }
        } while (isAlreadyPlayed);
      }

      arrayLines[random] = changeValuesToDefaultValues(arrayLines[random]);
      
      if (comprobateTableString(arrayLines[random], height, width))
        tableString = arrayLines[random];
      else
        System.out.println("El tablero elegido del fichero no es válido. Se jugará con el tablero por defecto.");
    } catch (FileNotFoundException e) {
      System.out.println("El archivo de tableros no se ha encontrado. Se jugará con el tablero por defecto.");
    }

    return tableString;
  }

}
