package EscogerLineaAleatoria;
import java.io.File;

// import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EscogerLineaAleatoria {

  final static String RUTA_FICHERO = "Ficheros/EscogerLineaAleatoria/ficheroTest.txt";

  public static void main(String[] args) {
    // E:\Programación\FPROG\Ficheros\test.txt
    // Ficheros -> "./test.txt"

    // in.hasNextLine() -> Devuelve true si hay una línea más en el archivo

    // System.out.println(archivo.exists());

    try {
      // Contar líneas del archivo
      Scanner readFile = new Scanner(new File(RUTA_FICHERO));
      
      int lineas = 0;

      while (readFile.hasNextLine()) {
        readFile.nextLine();
        lineas++;
      }

      System.out.println("El archivo tiene " + lineas + " líneas 🚀\n");

      readFile.close();


      // Leer archivo de nuevo y guardar líneas en un array, cada linea en una posición
      readFile = new Scanner(new File(RUTA_FICHERO));

      String[] lineasArray = new String[lineas];

      for (int i = 0; i < lineas; i++) {
        lineasArray[i] = readFile.nextLine();
      }

      readFile.close();


      // Mostrar todas las líneas del archivo guardadas en el array
      for (int i = 0; i < lineas; i++) {
        System.out.println("Posición " + i + ": " + lineasArray[i]);
      }

      // Linea aleatoria del archivo (número aleatorio entre 0 y lineas - 1)
      int random = (int) (Math.random() * lineas);

      System.out.println("\nLinea aleatoria (" + (random) + "): " + lineasArray[random]);

      // Escribir en el archivo con FileWriter
      FileWriter writer = new FileWriter(new File(RUTA_FICHERO), true);

      writer.write("\nEsta es una línea añadida " + (lineas + 1));

      writer.close();

    } catch (FileNotFoundException e) {
      System.out.println("El archivo no fue encontrado");
      e.printStackTrace(); // Imprime el error
    } catch (IOException e) {
      System.out.println("Error de entrada/salida");
      e.printStackTrace(); // Imprime el error
    }

    System.out.println("\nFin del programa");
  }
}
