package clasefpro;

public class Yincana500DivisoresNTriangulares {
  public static void main(String[] args) {
    long numeroTriangular;
    long divisores = 0;
    long n = 0;
    while (divisores < 500) {
      n += 1;
      numeroTriangular = n * (n + 1) / 2;
      divisores = 0;

      // for (long i = 1; i <= numeroTriangular; i++) {
      //   if (numeroTriangular % i == 0) {
      //     divisores++;
      //   }
      // }
      
      for (long i = 1; i <= Math.sqrt(numeroTriangular); i++) {
        if (numeroTriangular % i == 0) {
          divisores++; // i es un divisor
          if (i != numeroTriangular / i) {
            divisores++; // num/i es otro divisor
          }
        }
      }
    }
    System.out.println(n);
  }
}
