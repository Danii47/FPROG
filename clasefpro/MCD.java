package clasefpro;

public class MCD {
  /*
   * MÁXIMO COMÚN DIVISOR DE 1322382 Y 739878
   */

  public static void main(String[] args) {
    int resto = -1;
    int dividendo = 1322382;
    int divisor = 739878;

    // Algortimo de Euclides
    // Si a y b son dos números enteros, con a > b, el MCD de a y b es igual al MCD
    // de b y el resto de la división de a entre b (a % b).
    while (resto != 0) {
      resto = dividendo % divisor;

      dividendo = divisor;
      divisor = resto;
    }

    int mcd = dividendo;

    System.out.println(mcd);
  }
}
