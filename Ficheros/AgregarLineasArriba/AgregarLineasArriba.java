package AgregarLineasArriba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AgregarLineasArriba {

  final static String RUTA_FICHERO = "Ficheros/AgregarLineasArriba/ficheroTest.txt";

  public static void main(String[] args) {
    // Esta es la forma más sencilla de añadir líneas al principio de un archivo que tiene contenido
    
    // 1. Leer el archivo y guardar todas las líneas existentes en un String
    // 2. Añadir las líneas que queremos al fichero
    // 3. Añadir las líneas que guardamos en el paso 1 al final del fichero

    // (1)
    String contenido = "";

    try {
      Scanner fichero = new Scanner(new File(RUTA_FICHERO));

      while (fichero.hasNextLine()) {
        contenido += fichero.nextLine() + "\n"; // Hace falta añadir el salto de línea porque nextLine() no lo incluye (nextLine() lee hasta el salto de línea)
      }

      System.out.println("Contenido del fichero guardado en la variable 'contenido'.");

      fichero.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error al leer el archivo");
    }

    // En este momento ya tenemos en la variable "contenido" todas las líneas del archivo

    // (2)
    String lineaOLineasNuevas = "Línea nueva";

    try {
      FileWriter fichero = new FileWriter(RUTA_FICHERO);

      // ------------------------------
      fichero.write(lineaOLineasNuevas);
      fichero.write("\n");

      // (3)
      fichero.write(contenido);
      // ------------------------------
      
      // Se puede hacer en una linea asi:
      // fichero.write(lineaOLineasNuevas + "\n" + contenido);

      System.out.println("Línea nueva añadida al principio del fichero y el contenido anterior añadido al final.");

      fichero.close();
    } catch (IOException e) {
      System.out.println("Error al abrir el archivo");
    }
  }
}
