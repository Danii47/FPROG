/*
1. Elaborar los métodos Java siguientes, prestando especial atención a la documentación y en particular a sus precondiciones.
Para cualquier apartado puede usar un apartado anterior, aunque no lo tenga resuelto.


a) [1 pto] Dado un vector de caracteres, cuyos valores solamente pueden ser 'o', 'x' o '-',
devuelve un valor cierto o falso según el vector contenga o no tres 'o's o tres 'x's consecutivas.

b) [0,5 ptos] Dada una matriz bidimensional de caracteres, con las mismas características del vector anterior, y un entero n,
devuelve un valor cierto o falso según la fila n contenga o no tres 'o's o tres 'x's consecutivas.

c) [0,5 ptos] Dada una matriz bidimensional de caracteres, con las mismas características del vector anterior, y un entero n,
devuelve un valor cierto o falso según la columna n contenga o no tres 'o's o tres 'x's consecutivas.

d) [1 pto] Dada una matriz bidimensional de elementos de tipo double y un entero n, suma todos los elementos de la línea inclinada a -45º
que comienza en el elemento de la posición (0, n) de la matriz.
*/


public class Ejercicio1 {

  // a)
  public static boolean tresConsecutivas(char[] vector) {
    // PRE: que el vector este relleno de 'o', 'x' o '-'
    boolean tresConsecutivas = false;
    int contador = 1;
    int i = 1;
    while (i < vector.length && !tresConsecutivas) {
      if (vector[i] == vector[i - 1] && vector[i] != '-') {
        contador++;
        if (contador == 3) {
          tresConsecutivas = true;
        }
      } else {
        contador = 1;
      }
      i++;
    }

    // OTRA FORMA
    // for (int j = 0; j < vector.length - 2; j++) {
    //   if ((vector[j] == 'o' && vector[j + 1] == 'o' && vector[j + 2] == 'o') ||
    //       vector[j] == 'x' && vector[j + 1] == 'x' && vector[j + 2] == 'x') {
    //     tresConsecutivas = true;
    //   }
    // }

    return tresConsecutivas;
  }

  // b)
  public static boolean tresConsecutivasFila(char[][] matriz, int n) {
    // PRE: que la matriz este rellena de 'o', 'x' o '-' y que el n sea un índice válido
    return tresConsecutivas(matriz[n]);
  }

  // c)
  public static boolean tresConsecutivasColumna(char[][] matriz, int n) {
    // PRE: que la matriz este rellena de 'o', 'x' o '-' y que el n sea un índice válido
    char[] columnaConvertidaAFila = new char[matriz.length];

    for (int i = 0; i < columnaConvertidaAFila.length; i++) {
      columnaConvertidaAFila[i] = matriz[i][n];
    }

    return tresConsecutivas(columnaConvertidaAFila);
  }

  // d)
  public static double sumaDiagonalInclinada(double[][] matriz, int n) {
    // PRE: que la matriz este rellena de elementos de tipo double y que el n sea un índice válido
    double suma = 0;
    int i = 0;
 
    while (i < matriz.length && n < matriz[i].length) {
      suma += matriz[i][n];
      i++;
      n++;
    }
    return suma;
  }
}
