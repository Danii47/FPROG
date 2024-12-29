/*
[2 ptos] Para almacenar las cadenas cifradas con el método CesarASCII se utilizan registros de tipo regCIFRADO,
que contienen tres campos: uno que almacena la cadena, otro que almacena la clave de cifrado (entero) y otro que
guarda un valor que determina si la cadena almacenada está cifrada o es la original. Se pide:

a) Definir el tipo de datos regCIFRADO.

b) Elaborar un método Java que, a partir de un vector de registros de tipo regCIFRADO, y una clave,
localice en el vector todos los elementos que tienen esa clave y, para cada uno de ellos:

  Si la cadena está cifrada, la cambie por la cadena descifrada.
  Si la cadena es la original, la cambie por la cadena cifrada.

En ambos casos, actualice el campo de cifrado.
*/

public class Ejercicio4 {
  
  // a)
  public class regCIFRADO {
    String cadena;
    int clave;
    boolean cifrada;

    public regCIFRADO(String cadena, int clave, boolean cifrada) {
      this.cadena = cadena;
      this.clave = clave;
      this.cifrada = cifrada;
    }

    // b)
    public static void cambiarEstado(regCIFRADO[] vectorRegistros, int clave) {
      for (int i = 0; i < vectorRegistros.length; i++) {
        // Forma 1:
        if (vectorRegistros[i].clave == clave) {
          if (vectorRegistros[i].cifrada) {
            vectorRegistros[i].cadena = CesarASCII(vectorRegistros[i].cadena, -clave);
            vectorRegistros[i].cifrada = false;
          } else {
            vectorRegistros[i].cadena = CesarASCII(vectorRegistros[i].cadena, clave);
            vectorRegistros[i].cifrada = true;
          }
        }
        //
        // Forma 2:
        if (vectorRegistros[i].clave == clave) {
          
          vectorRegistros[i].cadena = CesarASCII(vectorRegistros[i].cadena, ((vectorRegistros[i].cifrada) ? -1 : 1) * clave);
          vectorRegistros[i].cifrada = !vectorRegistros[i].cifrada;

        }
      }
    }

    // DEL EJERCICIO ANTERIOR
    public static String CesarASCII(String cadena, int clave) {
      // PRE: que la cadena esta en minusculas sin dieresis, eñes ni tildes y que la
      // clave es un valor en [-65, 65]

      String cadenaEncriptada = "";

      for (int i = 0; i < cadena.length(); i++) {
        cadenaEncriptada += (char) (cadena.charAt(i) + clave);
      }

      return cadenaEncriptada;
    }
  }
}
