import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio4 {
  public static int contarMayusculas(String cadena) {
    // PRE: cadena != null
    int contador = 0;

    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) >= 'A' && cadena.charAt(i) <= 'Z') {
        contador++;
      }
    }

    return contador;
  }

  public static boolean contrapalindroma(String cadena) {
    // PRE: cadena != null
    if (cadena.length() <= 1) {
      return true; // Caso base
    } else {
      return (
        cadena.charAt(0) != cadena.charAt(cadena.length() - 1) &&
        contrapalindroma(cadena.substring(1, cadena.length() - 1))
      ); // Caso recursivo
    }
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintWriter fichero;
    String palabra;

    System.out.print("Introduce el nombre del fichero: ");
    String nombreFichero = in.nextLine();

    try {
      fichero = new PrintWriter(new FileWriter(nombreFichero));

      do {
        System.out.print("Introduce una palabra: ");
        palabra = in.nextLine();
        
        fichero.println("La palabra " + palabra + " tiene " + contarMayusculas(palabra) + " mayúsculas");

      } while (contrapalindroma(palabra));

    } catch (IOException e) {
      System.out.println("Error al escribir en el fichero");
      in.close();
      return;
    }

    fichero.close();
    in.close();
  }
}
