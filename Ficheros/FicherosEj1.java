import java.io.File;

// import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FicherosEj1 {
  public static void main(String[] args) {
    System.out.println("Ejercicio 1 Ficheros\n");
    File archivo = new File("Ficheros/test.txt");
    // E:\Programación\FPROG\Ficheros\test.txt
    // Ficheros -> "./test.txt"

    // in.hasNextLine() -> Devuelve true si hay una línea más en el archivo

    // System.out.println(archivo.exists());

    try {
      Scanner readFile = new Scanner(archivo);
      int lineas = 0;

      while (readFile.hasNextLine()) {
        readFile.nextLine();
        lineas++;
      }
      System.out.println("El archivo tiene " + lineas + " líneas 🚀 á ñ à\n");

      readFile.close();

      readFile = new Scanner(archivo);

      String[] lineasArray = new String[lineas];

      for (int i = 0; i < lineas; i++) {
        lineasArray[i] = readFile.nextLine();
      }

      readFile.close();

      for (int i = 0; i < lineas; i++) {
        System.out.println("Posición " + i + ": " + lineasArray[i]);
      }

      // Linea aleatoria del archivo

      int random = (int) (Math.random() * lineas);

      System.out.println("\nLinea aleatoria (" + (random + 1) + "): " + lineasArray[random]);

      // Escribir en el archivo con printWriter
      // PrintWriter writer = new PrintWriter(archivo);

      // writer.println("PRIMERA LINEA ESCRITA EN EL ARCHIVO");
      // writer.print("\n");
      // writer.print("SEGUNDA LINEA ESCRITA EN EL ARCHIVO");

      // writer.close();

      // Escribir en el archivo con FileWriter
      FileWriter writer = new FileWriter(archivo, true);

      writer.write("\nEsta es una línea añadida" + (lineas + 1));

      writer.close();

    } catch (FileNotFoundException e) {
      System.out.println("El archivo no fue encontrado");
      e.printStackTrace();

    } catch (IOException e) {
      System.out.println("Error de entrada/salida");
      e.printStackTrace();
    }

    System.out.println("\nFin del programa");
  }
}
