/*
1. Elaborar los métodos Java siguientes, prestando especial atención a la documentación y en particular a sus precondiciones.
Para cualquier apartado puede usar un apartado anterior, aunque no lo tenga resuelto.


a) [1 pto] Dado un vector de caracteres, cuyos valores solamente pueden ser 'o', 'x' o '-',
devuelve un valor cierto o falso según el vector contenga o no tres 'o's o tres 'x's consecutivas.

b) [0,5 ptos] Dada una matriz bidimensional de caracteres, con las mismas características del vector anterior, y un entero n,
devuelve un valor cierto o falso según la fila n contenga o no tres 'o's o tres 'x's consecutivas.

c) [0,5 ptos] Dada una matriz bidimensional de caracteres, con las mismas características del vector anterior, y un entero n,
devuelve un valor cierto o falso según la columna n contenga o no tres 'o's o tres 'x's consecutivas.
*/


public class Ejercicio1 {

  // a)
  public static boolean tresConsecutivas(char[] vector) {
    // PRE: que el vector este relleno de 'o', 'x' o '-'
    boolean tresConsecutivas = false;
    int contador = 1;
    int i = 1;
    while (i < vector.length && !tresConsecutivas) {
      if (vector[i] == vector[i - 1]) {
        contador++;
        if (contador == 3) {
          tresConsecutivas = true;
        }
      } else {
        contador = 1;
      }
      i++;
    }
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
}
