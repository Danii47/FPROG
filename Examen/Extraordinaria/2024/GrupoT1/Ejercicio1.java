
public class Ejercicio1 {
  // a)
  public static boolean vectoresIguales(int[] vector1, int[] vector2) {
    // PRE: que los vectores tengan la misma longitud
    boolean iguales = true;

    for (int i = 0; i < vector1.length && iguales; i++) {
      if (vector1[i] != vector2[i]) {
        iguales = false;
      }
    }

    return iguales;
  }

  // b)
  public static boolean filasIguales(int[][] matriz, int n, int m) {
    // PRE: que la matriz tenga las mismas columnas en todas las filas
    return vectoresIguales(matriz[n], matriz[m]);
  }

  // c)
  public static boolean columnasIguales(int[][] matriz, int n, int m) {
    // PRE: que la matriz tenga las mismas filas en todas las columnas
    int[] columnaConvertidaAFila1 = new int[matriz.length];
    int[] columnaConvertidaAFila2 = new int[matriz.length];

    for (int i = 0; i < columnaConvertidaAFila1.length; i++) {
      columnaConvertidaAFila1[i] = matriz[i][n];
      columnaConvertidaAFila2[i] = matriz[i][m];
    }

    return vectoresIguales(columnaConvertidaAFila1, columnaConvertidaAFila2);
  }
}
