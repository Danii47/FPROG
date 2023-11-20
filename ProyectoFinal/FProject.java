import java.util.Scanner;

// TODO: Explicar previousTable(), addTable() y comprobateTable()

public class FProject {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";
  public static final char X = 'x';
  public static final char O = 'o';

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    final int width = 6;
    final int height = 6;

    // String startTableString = "111222 221121 112212 221121 112212 221121";
    String startTableString = "002200 002000 000000 001000 200101 100001";
    // String startTableString = "121212 212121 121212 212121 121212 212121";

    int table[][];
    int startTable[][];
    int tables[][][];

    int winGames = 0;
    int playedGames = 0;

    double winPercentage;

    boolean finishGame = false;
    boolean leftGame;
    boolean saveUserData;
    boolean validPlay;
    String userInput;
    String continuePlayingAnswer;
    String saveUserDataAnswer;
    String userName;

    // https://fsymbols.com/generators/wide/
    /*
    System.out.println(ANSI_GREEN
        + "\n██████╗░░█████╗░███╗░░██╗██╗░░██╗██╗███╗░░██╗░██████╗░\n██╔══██╗██╔══██╗████╗░██║██║░██╔╝██║████╗░██║██╔════╝░\n██████╔╝███████║██╔██╗██║█████═╝░██║██╔██╗██║██║░░██╗░\n██╔══██╗██╔══██║██║╚████║██╔═██╗░██║██║╚████║██║░░╚██╗\n██║░░██║██║░░██║██║░╚███║██║░╚██╗██║██║░╚███║╚██████╔╝\n╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚═════╝░\n"
        + ANSI_RESET);
    */

    

    while (!finishGame) {

      leftGame = false;

      table = createTable(width, height, startTableString);
      startTable = createTable(width, height, startTableString);

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
            break;
  
          case "-":
            tables = previousTable(table, tables);
            break;
  
          case "":
            if (comprobateTable(table)) {
              System.out.println("¡Enhorabuena, has competado el tablero!");
              winGames++;
            }
            playedGames++;
            leftGame = true;
            break;
  
          default:
  
            validPlay = insertPlay(table, startTable, userInput); // TODO: Preguntar sobre buenas prácticas editar una variable dentro de una fn
            if (validPlay) {
              tables = addTable(table, tables);
            }
            break;
        }
      } while(!leftGame);
        
      do {
        System.out.println("¿Quieres jugar otro tablero? (SI/NO)");
        continuePlayingAnswer = in.nextLine();
        finishGame = (continuePlayingAnswer.equals("NO") || continuePlayingAnswer.equals("no")) ? true : false; // TODO: uso toLowerCase();
      } while ((!continuePlayingAnswer.equals("SI") && !continuePlayingAnswer.equals("NO")) && (!continuePlayingAnswer.equals("si") && !continuePlayingAnswer.equals("no")));

    }

    winPercentage = (winGames / playedGames) * 100;

    System.out.println("Partidas jugadas: " + playedGames);
    System.out.println("Partidas ganadas: " + winGames);
    System.out.println("Porcentaje de ganadas: " + (Math.round(winPercentage * 100) / 100) + "%");

    System.out.println();
  
    do {
      System.out.println("¿Quieres guardar tu puntuación? (SI/NO)");
      saveUserDataAnswer = in.nextLine(); 
      saveUserData = (saveUserDataAnswer.equals("SI") || saveUserDataAnswer.equals("si")) ? true : false; // TODO: uso toLowerCase();
    } while ((!saveUserDataAnswer.equals("SI") && !saveUserDataAnswer.equals("NO")) && (!saveUserDataAnswer.equals("si") && !saveUserDataAnswer.equals("no")));

    if (saveUserData) {
      System.out.print("Introduce tu nombre: ");
      userName = in.nextLine();
    }


    // TODO: Puntuaciones guardar en archivo

    in.close();
  }

  /**
   * 
   * Crea un array de las matrices de juego, inicializando está en un array con el tablero incial para ir añadiendo jugadas posteriormente.
   * 
   * @param startTable String codificado como indica el enunciado. (más explicado en createTable())
   * @return Array de matrices de juego
   */
  public static int[][][] createArrayOfTables(int[][] startTable) {
    int[][][] tables = { startTable };
    return tables;
  }

  /**
   * 
   * @param table  Matriz del tablero actual.
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

    // Añado la nueva matriz del tablero actualizado en la ultima posición. (está
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
   * @param table  tablero actual, se usa para poder actualizarlo al anterior
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
      // del tablero de la anterior jugada
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
      char userInputCharacter = userInput.toLowerCase().charAt(1); // TODO: Preguntar sobre uso de toLowerCase()

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
  public static boolean comprobateTable(int[][] table) {

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
          default:
            validTable = false;
            System.out.println("Había alguna casilla vacia, ¡has perdido!");
            break;
        }

        if (xAdjacentCont > 2 || xAdjacentCont > 2) {
          System.out.println(
              "En la posición " + (row + 1) + ((char) ('A' + column)) + " hay un tercer simbolo contiguo en fila, ¡has perdido!");
          validTable = false;
        }

      }

      if (xCont != oCont) {
        System.out.println("La fila " + (row + 1) + " no tiene el mismo número de X y 0, ¡has perdido!");
        validTable = false;
      }

      for (int auxRow = 0; auxRow < table.length && validTable; auxRow++) {

        for (int column = 0; column < table[row].length && validTable; column++) {

          if (row != auxRow && table[row][column] == table[auxRow][column])
            sameValueCont++;
        }

        if (sameValueCont >= table[row].length) {
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
          System.out.println(
              "En la posición " + (row + 1) + ((char) ('A' + column)) + " hay un tercer simbolo contiguo en columna, ¡has perdido!");
          validTable = false;
        }
      }

      if (xCont != oCont) {
        System.out.println("La columna " + ((char) ('A' + column)) + " no tiene el mismo número de X y 0, ¡has perdido!");
        validTable = false;
      }

      for (int auxColumn = 0; auxColumn < table[0].length && validTable; auxColumn++) {

        for (int row = 0; row < table.length && validTable; row++) {

          if (column != auxColumn && table[row][column] == table[row][auxColumn])
            sameValueCont++;
        }

        if (sameValueCont >= table.length) {
          System.out.println("La columna " + (column + 1) + " y la columna " + (auxColumn + 1) + " son iguales, ¡has perdido!");
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

  // TODO: Preguntar sobre el uso de este método
  public static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");

      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        Runtime.getRuntime().exec("clear");
      }
    } catch (final Exception e) {
      // Manejo de excepciones
    }
  }
}
