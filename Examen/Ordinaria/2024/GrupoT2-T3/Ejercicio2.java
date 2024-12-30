/*
[4 ptos] El método de cifrado César consiste en sustituir cada letra del abecedario por una letra desplazada un número
determinado de posiciones (clave). Por ejemplo, al cifrar la cadena IBM con la clave -1 se obtiene la cadena HAL.
Denominaremos método de cifrado CesarASCII a la aplicación de esta forma de encriptado sobre cualquier carácter
de la tabla ASCII (no solo las letras). Con este método, al cifrar la cadena examen con la clave 4 se obtiene la cadena i|eqir.

Elaborar un programa Java que lea palabras del teclado, formadas únicamente por letras minúsculas
del alfabeto latino (sin tildes, ni diéresis ni eñes). Para cada palabra correcta leída,
el programa debe obtener del usuario un valor entero no nulo en el intervalo [-65, 65] para garantizar que la
codificación funciona sin salirse de rango. Ese valor será la clave con la que el programa cifrará esa cadena
siguiendo el método CesarASCII.

Como resultado, el programa debe escribir en un fichero de texto, de nombre cifrado.txt,
cada clave y la cadena cifrada obtenida (separados por un espacio en blanco) a razón de una pareja (clave – cadena cifrada) por línea.
El programa debe terminar cuando el usuario introduzca una cadena no vacía que no esté formada exclusivamente por
letras minúsculas del alfabeto latino.

Suponga importadas las clases necesarias.

RECORDATORIO:

Para declarar y abrir en modo lectura un fichero de texto:

Scanner <id_fich> = new Scanner (new File(<nombre_fich>));
Al abrir el fichero se puede producir la excepción FileNotFoundException.


Para declarar y abrir en modo escritura un fichero de texto:

PrintWriter <id_fich> = new PrintWriter (new FileWriter (<nombre_fich>));
Al abrir el fichero se puede producir la excepción IOException.
*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ejercicio2 {
  public static String CesarASCII(String cadena, int clave) {
    // PRE: que la cadena esta en minusculas sin dieresis, eñes ni tildes y que la clave es un valor en [-65, 65]

    String cadenaEncriptada = "";

    for (int i = 0; i < cadena.length(); i++) {
      cadenaEncriptada += (char) (cadena.charAt(i) + clave);
    }

    return cadenaEncriptada;
  }

  public static boolean sonMinusculas(String cadena) {
    boolean sonMinusculas = true;

    for (int i = 0; i < cadena.length(); i++) {
      if (cadena.charAt(i) < 'a' || cadena.charAt(i) > 'z') {
        sonMinusculas = false;
      }
    }

    return sonMinusculas;
  }

  public static boolean estaEnRango65(int clave) {
    return clave >= -65 && clave <= 65;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String palabra;
    PrintWriter fichero;
    int clave;
    boolean seguir = true;

    try {
      fichero = new PrintWriter(new FileWriter("cifrado.txt"));
    } catch (IOException e) {
      System.out.println("Error, no ha sido posible abrir / crear el fichero.");
      in.close();
      return;
    }

    while (seguir) {
      do {
        palabra = in.next();
      } while (palabra == "");
  
      if (sonMinusculas(palabra)) {
        do {
          clave = in.nextInt();
        } while (!estaEnRango65(clave) || clave == 0);
    
        String cadenaCifrada = CesarASCII(palabra, clave);
        
        fichero.println(clave + " - " + cadenaCifrada);
      } else {
        seguir = false; 
      }
    }
    
    fichero.close();
    in.close();
  }
}
