package LAB;

/*
Escribe un método que compruebe si un vector es un vector de palabras encadenadas en mayusculas. Vamos a considerar
que un vector de palabras encadenadas es aquel en el que cada elemento del vector contiene una palabra y donde los dos
últimos caracteres de cada palabra son los mismos que los dos primeros de la palabra siguiente.

Además, los dos últimos caracteres de la palabra de la última posición en el vector deben coincidir con los dos 
primeros de la palabra de la primera posición.

El método mostrará por pantalla "Vector de Palabras Encadenadas" o "Un vector cualquiera" dependiendo de si se
cumplen o no las condiciones comentadas anteriormente.

El programa mostrará por pantalla "Error en el vector" siempre que las palabras no esten compuestas por letras mayusculas.

Ejemplos:
{"Hola", "ADIOS", "SEP", "POE"} -> Error en el vector
{"HOLA", "ADIOS", "SEP", "POE"} -> Un vector cualquiera
{"AELA", "LAIOS", "OSA", "SAE"} -> Vector de Palabras Encadenadas
*/

public class SegundoExamenLabTipoC_2024 {
  public static void main(String[] args) {
    String[] v1 = { "Hola", "ADIOS", "SEP", "POE" };
    String[] v2 = { "HOLA", "ADIOS", "SEP", "POE" };
    String[] v3 = { "AELA", "LAIOS", "OSA", "SAE" };

    comprobarVectorPalabrasEncadenadas(v1);
    comprobarVectorPalabrasEncadenadas(v2);
    comprobarVectorPalabrasEncadenadas(v3);
  }

  public static void comprobarVectorPalabrasEncadenadas(String[] vector) {
    boolean vectorValido = true;

    for (int i = 0; i < vector.length && vectorValido; i++) {
      for (int j = 0; j < vector[i].length() && vectorValido; j++) {
        if (vector[i].charAt(j) < 'A' || vector[i].charAt(j) > 'Z') {
          System.out.println("Error en el vector");
          vectorValido = false;
        }
      }
    }

    if (vectorValido) {
      boolean vectorPalabrasEncadenadas = true;
      for (int i = 0; i < vector.length - 1; i++) {
        if (vector[i].charAt(vector[i].length() - 2) != vector[i + 1].charAt(0) ||
            vector[i].charAt(vector[i].length() - 1) != vector[i + 1].charAt(1)) {
          vectorPalabrasEncadenadas = false;
        }
      }

      if (vector[0].charAt(0) != vector[vector.length - 1].charAt(vector[vector.length - 1].length() - 2) ||
          vector[0].charAt(1) != vector[vector.length - 1].charAt(vector[vector.length - 1].length() - 1)) {
        vectorPalabrasEncadenadas = false;
      }

      if (vectorPalabrasEncadenadas) {
        System.out.println("Vector de Palabras Encadenadas");
      } else {
        System.out.println("Un vector cualquiera");
      }
    }
  }
}
