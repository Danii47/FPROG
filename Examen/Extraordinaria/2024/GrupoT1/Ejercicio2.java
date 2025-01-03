/*
[4 ptos] Para estudiar ciertas propiedades de un historial de mensajes, que se encuentra en el fichero de texto
entrada.txt, es necesario almacenar las siguientes informaciones para cada longitud posible de los mensajes
(desde 1 hasta 150, que es el máximo permitido):
  - El número de mensajes que tienen esa longitud.
  - El número de orden del primer mensaje que tiene esa longitud (el primer mensaje es el número 1).
  - El primer mensaje de esa longitud.
  - El porcentaje de mensajes de esa longitud en el total del texto (número real).

La estructura idónea para realizar este almacenamiento es un vector de registros, cada uno de los cuales almacena
los cuatro valores citados para cada longitud. Esta estructura debe definirse.
Elaborar un programa Java que, una vez almacenada esta información, permita que el usuario solicite
sucesivamente (hasta que el usuario pulse 0) información sobre longitudes de mensajes (introduciendo un
número entero cuyo valor debe estar en el rango permitido) y el programa le proporcione en pantalla los datos
correspondientes.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ejercicio2 {
  
  public static class MensajeConLongitudN {
    int numeroMensajes;
    int numeroOrdenPrimerMensaje;
    String primerMensaje;
    double porcentajeMensajes;

    public MensajeConLongitudN(int numeroMensajes, int numeroOrdenPrimerMensaje, String primerMensaje,
        double porcentajeMensajes) {
      this.numeroMensajes = numeroMensajes;
      this.numeroOrdenPrimerMensaje = numeroOrdenPrimerMensaje;
      this.primerMensaje = primerMensaje;
      this.porcentajeMensajes = porcentajeMensajes;
    }
  }
  public static void main(String[] args) {
    


    Scanner in = new Scanner(System.in);
    int numeroSolicitado;

    MensajeConLongitudN[] mensajes = new MensajeConLongitudN[150];

    // Leer el fichero y almacenar la información
    try {
      Scanner fichero = new Scanner(new File("entrada.txt"));
      int mensajesTotales = 0;

      while (fichero.hasNextLine()) {
        String mensaje = fichero.nextLine();
        int longitudMensaje = mensaje.length();
        mensajesTotales++;

        if (mensajes[longitudMensaje] == null) {
          mensajes[longitudMensaje] = new MensajeConLongitudN(
            1, // numeroMensajes
            mensajesTotales, // numeroOrdenPrimerMensaje
            mensaje, // primerMensaje
            (1.0 / mensajesTotales) * 100 // porcentajeMensajes
          );
        
        } else {
          mensajes[longitudMensaje].numeroMensajes++;
          mensajes[longitudMensaje].porcentajeMensajes = (((double) mensajes[longitudMensaje].numeroMensajes) / mensajesTotales) * 100;
        }
      }

      fichero.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error al leer el fichero, asegúrate de que existe y se llama entrada.txt");
      in.close();
      return;
    }


    do {
      System.out.print("Introduce la longitud del mensaje (0 para salir): ");
      numeroSolicitado = in.nextInt();

      if (numeroSolicitado > 0 && numeroSolicitado <= 150) {
        System.out.println("Número de mensajes: " + mensajes[numeroSolicitado].numeroMensajes);
        System.out.println("Número de orden del primer mensaje: " + mensajes[numeroSolicitado].numeroOrdenPrimerMensaje);
        System.out.println("Primer mensaje: " + mensajes[numeroSolicitado].primerMensaje);
        System.out.println("Porcentaje de mensajes: " + mensajes[numeroSolicitado].porcentajeMensajes);
      } else {
        System.out.println("Número no válido");
      }
    } while (numeroSolicitado != 0);

    in.close();
  }
}
