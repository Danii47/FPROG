package Otros;
public class EjercicioExamen {

  public static void main(String[] args) {
    int[][] matriz = {
        { 1, 1, 1, 1, 1 },
        { 1, 2, 2, 2, 1 },
        { 1, 2, 3, 2, 1 },
        { 1, 2, 2, 2, 1 },
        { 1, 1, 1, 1, 1 }
    };
    Concentrica resultado = new Concentrica(matriz);

    System.out.println(resultado.getConcetrica());

    int[] vector = resultado.getVector();

    for (int i = 0; i < vector.length; i++) {
      System.out.print(vector[i] + " ");
    }

  }
}

class Concentrica {

  private boolean conc;
  private int[] v;

  public Concentrica(int[][] matriz) {

    this.conc = esConcentrica(matriz);
    this.v = new int[(matriz.length + 1) / 2];

    if (this.conc) {
      for (int i = 0; i < this.v.length; i++) {
        this.v[i] = matriz[i][i];
      }
    }
  }

  public boolean getConcetrica() {
    return this.conc;
  }

  public int[] getVector() {
    return this.v;
  }

  public static boolean esConcentrica(int[][] matriz) {

    int capa = 0;
    boolean concentrica = true;

    while (capa < (matriz.length + 1) / 2) {

      int valorCapa = matriz[capa][capa];

      for (int i = capa; i < matriz.length - capa && concentrica; i++) {
        if (matriz[capa][i] != valorCapa ||
            matriz[i][capa] != valorCapa ||
            matriz[matriz.length - (capa + 1)][i] != valorCapa ||
            matriz[i][matriz.length - (capa + 1)] != valorCapa) {

          concentrica = false;
        }
      }

      capa++;
    }

    return concentrica;
  }
}