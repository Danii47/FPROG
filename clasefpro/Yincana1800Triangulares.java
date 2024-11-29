package clasefpro;

public class Yincana1800Triangulares {
  public static void main(String[] args) {
    long totalNumeroTriangulares = 0;

    for (int i = 1; i <= 1800; i++) {
      for (int j = 1; j <= i; j++) {
        totalNumeroTriangulares += j;
      }
    }

    System.out.println(totalNumeroTriangulares);
  }
}
