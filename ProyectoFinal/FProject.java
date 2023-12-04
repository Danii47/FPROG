import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

// https://fsymbols.com/generators/wide/
// TODO: Explicar previousTable(), addTable() y comprobateTable()
// TODO: Recuperar tablero guardado
// TODO: MAYUSCULAS Y MINUSCULAS PARA DIFERENCIAR LAS INCIALES
// TODO: COMENTAR

public class FProject {
  public static final char X = 'x';
  public static final char O = 'o';
  public static final char XStart = 'X';
  public static final char OStart = 'O';
  public static final int valueToChangeCase = 'a' - 'A';
  public static final int width = 6;
  public static final int height = 6;
  public static final String tablesFilePath = "./ProyectoFinal/tableros.txt";
  public static final String tableSavedFilePath = "./ProyectoFinal/saveGame.txt";

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    // String startTableString = "111222 221121 112212 221121 112212 221121";
    // String startTableString = "010021 021002 000000 210120 200000 000022";
    String startTableString = getRandomTableString();

    int table[][];
    int startTable[][];
    int tables[][][];

    int winGames = 0;
    int playedGames = 0;

    double winPercentage;

    boolean finishGame = false;
    boolean leftGame = false;
    boolean saveUserData;
    boolean validPlay;
    boolean saveGame;

    String userInput;
    String continuePlayingAnswer;
    String saveUserDataAnswer;
    String userName;
    String password;

    while (!finishGame) {

      table = createTable(width, height, startTableString);
      startTable = createTable(width, height, startTableString);
      printTables(getTableSolved(table));

      // Asigno la variable tables para almacenar todos los tableros que van
      // surgiendo a medida que se juega para, posteriormente, retrodecer las jugadas
      tables = createArrayOfTables(startTable);

      do {
        drawTable(table);

        System.out.print("Jugada: ");
        userInput = in.nextLine();

        switch (userInput) {
          case "s":
            leftGame = confirmExit(in);
            finishGame = leftGame;
            if (leftGame) {
              saveGame = confirmSaveGame(in);
              if (saveGame) {
                System.out.println("Introduce una contraseña para poder recuperar la partida:");
                password = in.nextLine();
                saveTableGame(tables, password);
              }
            }

            break;

          case "-":
            tables = previousTable(table, tables);
            break;

          case "reiniciar":
            table = createTable(width, height, startTableString);
            tables = createArrayOfTables(startTable);
            break;

          case "?":
            getHint(table, startTable);
            break;

          case "":
            if (!isFullTable(table)) {
              System.out.println("El tablero no está completo, ¡continua jugando!");
            } else {
              if (comprobateTable(table, true)) {
                System.out.println("¡Enhorabuena, has competado el tablero!");
                winGames++;
              }
              playedGames++;
              leftGame = true;
            }

            break;

          default:
            validPlay = insertPlay(table, startTable, userInput); // TODO: mala práctica, cambiar valor de vector

            if (validPlay)
              tables = addTable(table, tables);

            break;
        }
      } while (!leftGame);

      if (!finishGame) {
        do {
          System.out.println("¿Quieres jugar otro tablero? (SI/NO)");
          continuePlayingAnswer = myToLowerCase(in.nextLine());
          finishGame = (continuePlayingAnswer.equals("no")) ? true : false;
        } while (!continuePlayingAnswer.equals("si") && !continuePlayingAnswer.equals("no"));
      }
    }

    winPercentage = Math.round(((double) winGames / playedGames) * 10000) / 100;

    System.out.println("Partidas jugadas: " + playedGames);
    System.out.println("Partidas ganadas: " + winGames);
    System.out.println("Porcentaje de ganadas: " + winPercentage + "%");

    System.out.println();

    do {
      System.out.println("¿Quieres guardar/sobreescribir tu puntuación? (s -> sí | n -> cancelar)");
      saveUserDataAnswer = myToLowerCase(in.nextLine());
      saveUserData = (saveUserDataAnswer.equals("s")) ? true : false;

    } while (!saveUserDataAnswer.equals("s") && !saveUserDataAnswer.equals("n"));

    if (saveUserData) {
      System.out.print("Introduce tu nombre (si quieres que se sobreescriba introduce el mismo que introdujiste la última vez): ");
      userName = in.nextLine();
    }

    // TODO: Puntuaciones guardar en archivo

    in.close();
  }

  public static void saveTableGame(int[][][] tables, String password) {

    File file = new File(tableSavedFilePath);
    
    try {
      PrintWriter writer = new PrintWriter(file);

      writer.println(tables[0].length);
      writer.println(tables[0][0].length);
      for (int i = 0; i < tables.length; i++) {
        for (int row = 0; row < tables[i].length; row++) {
          for (int column = 0; column < tables[i][row].length; column++) {
            writer.print(tables[i][row][column]);
          }
          writer.print(" ");
        }
        writer.println();
      }

      writer.print(encryptPassword(password));

      writer.close();
    } catch (FileNotFoundException e) {
      System.out.println("No fue posible guardar la partida. El archivo no fue encontrado.");
    }
  }

  public static String encryptPassword(String password) {
    String encryptedPassword = "";
    String rotateString = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0246813579";

    for (int i = password.length() - 1; i >= 0; i--) {
      int rotatedIndex = (rotateString.indexOf(password.charAt(i)) + password.length()) % rotateString.length();
      encryptedPassword += rotateString.charAt(rotatedIndex);
    }

    return encryptedPassword;
  }

  public static void getHint(int[][] table, int[][] startTable) {

    // TODO: deberia dar una pista que 100 por 100 correcta o simplemente una pista
    // que no rompa la partida?
    // TODO: pista que no rompa la partida, implementación -> que sea 100 por 100
    // correcta

  }

  public static void printTables(int[][][] tables) {
    for (int i = 0; i < tables.length; i++) {
      System.out.println("Tabla " + (i + 1));
      drawTable(tables[i]);
    }
  }

  public static int[][][] getTableSolved(int[][] table) {

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

              switch (solvedTables[i][row][column]) {
                case 2:
                  xCont++;
                  break;
                case 1:
                  oCont++;
                  break;
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

                if (solvedTables[i][row][column] == solvedTables[i][row][column + 1]
                    && solvedTables[i][row][column] != 0) {

                  if (column != solvedTables[i][0].length - 2 && solvedTables[i][row][column + 2] == 0) {
                    solvedTables[i][row][column + 2] = (solvedTables[i][row][column] == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }
                  if (column != 0 && solvedTables[i][row][column - 1] == 0) {
                    solvedTables[i][row][column - 1] = (solvedTables[i][row][column] == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }

                }
                if (column != 0 && solvedTables[i][row][column - 1] == solvedTables[i][row][column + 1]
                    && solvedTables[i][row][column] == 0
                    && solvedTables[i][row][column - 1] != 0) {
                  solvedTables[i][row][column] = (solvedTables[i][row][column - 1] == 1) ? 2 : 1;
                  sameTables[i] = false;
                }
              }
            }

            xCont = 0;
            oCont = 0;

          }

          for (int column = 0; column < solvedTables[i][0].length; column++) {

            for (int row = 0; row < solvedTables[i].length; row++) {

              switch (solvedTables[i][row][column]) {
                case 2:
                  xCont++;
                  break;
                case 1:
                  oCont++;
                  break;
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

                if (solvedTables[i][row][column] == solvedTables[i][row + 1][column]
                    && solvedTables[i][row][column] != 0) {

                  if (row != solvedTables[i].length - 2 && solvedTables[i][row + 2][column] == 0) {
                    solvedTables[i][row + 2][column] = (solvedTables[i][row][column] == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }
                  if (row != 0 && solvedTables[i][row - 1][column] == 0) {
                    solvedTables[i][row - 1][column] = (solvedTables[i][row][column] == 1) ? 2 : 1;
                    sameTables[i] = false;
                  }

                }
                if (row != 0 && solvedTables[i][row - 1][column] == solvedTables[i][row + 1][column]
                    && solvedTables[i][row][column] == 0
                    && solvedTables[i][row - 1][column] != 0) {
                  solvedTables[i][row][column] = (solvedTables[i][row - 1][column] == 1) ? 2 : 1;
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

      System.out.println(solvedTables.length);
      for (int i = solvedTables.length - 1; i >= 0; i--) {
        if (isFullTable(solvedTables[i]) && !comprobateTable(solvedTables[i], false)) {
          solvedTables = removeTable(solvedTables, i);
          sameTables = removeBooleanValue(sameTables, i);
        }
      }

    }

    return solvedTables;
  }

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

  public static boolean[] pushBooleanValue(boolean[] array, boolean value) {
    boolean[] newArray = new boolean[array.length + 1];

    for (int i = 0; i < array.length; i++) {
      newArray[i] = array[i];
    }

    newArray[array.length] = value;

    return newArray;
  }

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
   * table incial para ir añadiendo jugadas posteriormente.
   * 
   * @param startTable String codificado como indica el enunciado. (más explicado
   *                   en createTable())
   * @return Array de matrices de juego
   */
  public static int[][][] createArrayOfTables(int[][] table) {
    int[][][] tables = { table };
    return tables;
  }

  /**
   * 
   * @param table  Matriz del table actual.
   * @param tables Array de matrices de todos los tableros de juegos.
   * @return Array de matrices de todos los tableros de juegos añadiendo table (el
   *         último jugado).
   */
  public static int[][][] addTable(int[][] table, int[][][] tables) {

    // Creo el nuevo Array de matrices de juegos. Este debe tener la longitud del
    // que ya había + 1.
    int[][][] newTables = new int[tables.length + 1][table.length][table[0].length];

    // Primero se copian los tableros que ya había en el nuevo array (es infiferente
    // hacer una copia de exhaustiva ya que tables va a ser reasignado)
    for (int i = 0; i < tables.length; i++) {

      newTables[i] = tables[i];

    }

    // Añado la nueva matriz del table actualizado en la ultima posición. (está
    // vez si hago una copia exhaustiva ya que table podría cambiar su valor durante
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
   * Actualiza el valor de table al anterior jugado basandose en el array de
   * matrices de juego tables.
   * 
   * @param table  table actual, se usa para poder actualizarlo al anterior
   *               basandose en tables.
   * @param tables array de matrices de juego
   * @return array de matrices de juego quitando la última matriz
   */
  public static int[][][] previousTable(int[][] table, int[][][] tables) {

    int[][][] newTables = new int[tables.length - 1][table.length][table[0].length];

    // Si tables es <= 1 significa que no hay más tableros hacía atras, solo el del
    // inicio de juego.
    clearConsole();

    if (tables.length <= 1) {
      newTables = tables;
      System.out.println("No hay más jugadas hacía atrás.");
    } else {

      // Replico el array de matrices tables en un nuevo array de matrices pero sin
      // añadir el último.
      for (int i = 0; i < newTables.length; i++) {

        for (int row = 0; row < table.length; row++) {

          for (int column = 0; column < table[0].length; column++) {

            newTables[i][row][column] = tables[i][row][column];

          }

        }

      }

      // Establezco los valores de table al úlimo elemento de newTables, que se trata
      // del table de la anterior jugada
      for (int row = 0; row < table.length; row++) {

        for (int column = 0; column < table[0].length; column++) {

          table[row][column] = newTables[newTables.length - 1][row][column];

        }
      }

    }

    return newTables;
  }

  /**
   * 
   * Comprueba si el table no tiene ningún espacio vacio.
   * 
   * @param table Matriz del table.
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

    clearConsole();

    System.out.println("Estas seguro de que quieres salir? (s -> sí | n -> cancelar)");
    String confirmExitStr = in.nextLine();

    boolean confirmExit = confirmExitStr.equals("s") ? true : false;

    if (confirmExit)
      System.out.println("¡Que te vaya bien!");
    else
      clearConsole();
    return confirmExit;
  }

  public static boolean confirmSaveGame(Scanner in) {

    clearConsole();

    System.out.println("¿Quieres guardar la partida? (s -> sí | n -> no)");
    String confirmSaveGameStr;

    do {
      confirmSaveGameStr = myToLowerCase(in.nextLine());
    } while (!confirmSaveGameStr.equals("s") && !confirmSaveGameStr.equals("n"));

    boolean confirmSaveGame = confirmSaveGameStr.equals("s") ? true : false;

    clearConsole();
    return confirmSaveGame;
  }

  /**
   * 
   * Actualiza la tabla con la decisión tomada por el usuario y comprueba si es
   * válida o no. Tiene un valor de retorno que depende de si ha sido una decisión
   * válida o no.
   * 
   * @param table      Matriz de juego.
   * @param startTable Matriz del inicio de juego.
   * @param userInput  Cadena de texto introducida por el usuario.
   * @return Valor booleano dependiendo de si la jugada es válida o no.
   */
  public static boolean insertPlay(int[][] table, int[][] startTable, String userInput) {

    boolean validPlay = false;

    clearConsole();

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
          switch (table[userInputNumberInt][userInputCharacterInt]) {
            case 0:
              table[userInputNumberInt][userInputCharacterInt] = 2;
              break;
            case 1:
              table[userInputNumberInt][userInputCharacterInt] = 0;
              break;
            case 2:
              table[userInputNumberInt][userInputCharacterInt] = 1;
              break;
          }
          if (isFullTable(table)) {
            System.out.println("Has llenado el tablero. Si quieres comprobar si has ganado, pulsa ENTER.");
          }
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
   * Comprueba si la matriz del juego es válida. Debe tener el mismo número de X's
   * (2) que de O's (1) tanto por columna como por filas y tener todos los huecos
   * con X u O. Además, no puede haber ni 2 filas ni 2 columnas iguales.
   * 
   * @param table Matriz rellenada del juego
   * @return
   */
  public static boolean comprobateTable(int[][] table, boolean notifyUser) {

    final int O = 1;
    final int X = 2;

    int xCont = 0;
    int oCont = 0;
    int xAdjacentCont = 0;
    int oAdjacentCont = 0;
    int sameValueCont = 0;

    boolean validTable = true;

    // ! Comprobar filas
    for (int row = 0; row < table.length && validTable; row++) {

      for (int column = 0; column < table[row].length && validTable; column++) {
        switch (table[row][column]) {
          case X:
            xCont++;
            xAdjacentCont++;
            oAdjacentCont = 0;
            break;
          case O:
            oCont++;
            oAdjacentCont++;
            xAdjacentCont = 0;
            break;
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

          if (row != auxRow && table[row][column] == table[auxRow][column])
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

        switch (table[row][column]) {
          case X:
            xCont++;
            xAdjacentCont++;
            oAdjacentCont = 0;
            break;
          case O:
            oCont++;
            oAdjacentCont++;
            xAdjacentCont = 0;
            break;
          default:
            validTable = false;
            break;
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

          if (column != auxColumn && table[row][column] == table[row][auxColumn])
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
   * table predefinido a través del estilo de String explicado en el enunciado.
   * Funciona para cualquier tamaño siempre y cuando el String del table
   * mantenga el patrón.
   * 
   * @param width         Tamaño de ancho del table (número entero).
   * @param height        Tamaño de alto del table (número entero).
   * @param tableToCreate Linea de texto que marca el table a crear.
   *                      tableToCreate -> "002200 002000 000000 001000 200101
   *                      100001".
   *                      Las cadenas individuales tienen que tener la longitud
   *                      del ancho y tiene que haber tantas cadenas como altura
   * 
   * @return La matriz del table creado rellenada con ceros, unos y doses
   */
  public static int[][] createTable(int width, int height, String tableToCreate) {
    int[][] table = new int[height][width];
    int gapValue;

    for (int row = 0; row < table.length; row++) {

      for (int column = 0; column < table[row].length; column++) {

        gapValue = tableToCreate.charAt((row * (width + 1)) + column) - '0';
        table[row][column] = gapValue;

      }

    }

    return table;
  }

  /**
   * 
   * Dibuja la matriz del table en la terminal como indica el enunciado.
   * 
   * @param table Matriz del table
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

        switch (table[row][column]) {
          case 0:
            gapToDraw = ' ';
            break;
          case 1:
            gapToDraw = O;
            break;
          case 2:
            gapToDraw = X;
            break;
          default:
            gapToDraw = ' ';
            break;
        }

        System.out.print(gapToDraw + " ");

      }
    }
    System.out.println();
  }

  /**
   * 
   * Devuelve un String aleatorio de tablero de juego sacado de tableros.txt para
   * poder jugar. En caso de no encontrar el tablero devuelve uno por defecto.
   * 
   * @return String aleatorio de tablero de juego sacado de tableros.txt
   */
  public static String getRandomTableString() {
    File tablesFile = new File(tablesFilePath);
    String tableString = "002000 000000 100120 000100 000020 110200";

    try {
      Scanner readFile = new Scanner(tablesFile);
      int lines = 0;

      while (readFile.hasNextLine()) {
        readFile.nextLine();
        lines++;
      }

      readFile.close();

      readFile = new Scanner(tablesFile);

      String[] arrayLines = new String[lines];

      for (int i = 0; i < lines; i++) {
        arrayLines[i] = readFile.nextLine();
      }

      readFile.close();

      // Linea aleatoria del archivo
      int random = (int) (Math.random() * lines);

      tableString = arrayLines[random];
    } catch (FileNotFoundException e) {
      System.out.println("El archivo no fue encontrado");
    }

    return tableString;
  }

  public static void clearConsole() {

  }
}
