package LAB;

/*
Escribe un método que compruebe si una matriz de números enteros (de cualquier tamaño) es una matriz encadenada.
Consideramos que una matriz es encadenada cuando los dos últimos valores de cada fila de la matriz coinciden con los
dos primeros de la fila siguiente. Además, los dos últimos valores de la última fila deben coincidir con los dos primeros
de la primera fila.

El método mostrará por pantalla "Matriz encadenada" o "Una matriz sin encadenar" dependiendo de si se cumplen o no las
condiciones comentadas anteriormente.

Por otro lado si la matriz tiene filas de distinta longitud o de menos de 2 elementos mostrará por pantalla "Matriz errónea".
*/

public class SegundoExamenLabTipoD_2024 {
  public static void main(String[] args) {
    int[][] m1 = {
        { 1, 2, 3, 4 },
        { 1, 2, 3, 4 },
        { 1, 2, 3, 4 }
    };

    int[][] m2 = {
        { 1, 2, 3, 4 },
        { 3, 4, 5, 6 },
        { 5, 6, 7, 8 }
    };

    int[][] m3 = {
        { 1, 2, 3, 4 },
        { 3, 4, 5, 6 },
        { 5, 6, 1, 2 }
    };

    int[][] m4 = {
        { 1, 2, 3, 4 },
        { 3, 4, 5 },
        { 4, 5, 1, 2 }
    };

    int[][] m5 = {
        { 3 },
        { 3 },
        { 3 }
    };

    int[][] m6 = {
        { 3, 4 },
        { 3, 4 },
        { 3, 4 }
    };

    comprobarMatrizEncadenada(m1);
    comprobarMatrizEncadenada(m2);
    comprobarMatrizEncadenada(m3);
    comprobarMatrizEncadenada(m4);
    comprobarMatrizEncadenada(m5);
    comprobarMatrizEncadenada(m6);
  }

  public static void comprobarMatrizEncadenada(int[][] matriz) {
    boolean matrizValida = true;

    if (matriz.length < 2) {
      matrizValida = false;
    } else {
      int tamanoFila = matriz[0].length;

      for (int i = 0; i < matriz.length; i++) {
        if (matriz[i].length < 2 || matriz[i].length != tamanoFila) {
          matrizValida = false;
        }
      }
    }

    if (!matrizValida) {
      System.out.println("Matriz errónea");
    } else {
      boolean matrizEncadenada = true;

      for (int i = 0; i < matriz.length - 1; i++) {
        if (matriz[i][matriz[i].length - 2] != matriz[i + 1][0] ||
            matriz[i][matriz[i].length - 1] != matriz[i + 1][1]) {
          matrizEncadenada = false;
        }
      }

      if (matriz[0][0] != matriz[matriz.length - 1][matriz[matriz.length - 1].length - 2] ||
          matriz[0][1] != matriz[matriz.length - 1][matriz[matriz.length - 1].length - 1]) {
        matrizEncadenada = false;
      }

      if (matrizEncadenada) {
        System.out.println("Matriz encadenada");
      } else {
        System.out.println("Una matriz sin encadenar");
      }
    }
  }
}
