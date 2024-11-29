/*
CALCULAR LA POTENCIA DE BASE LA SUMA DE LOS NÚMEROS PRIMOS
MENORES QUE 100 (CONSIDERANDO QUE EL 1 ES PRIMO) Y EXPONENTE EL
MÁXIMO COMÚN DIVISOR DE 1322382 Y 739878 

(sumaPrimosMenoresQue100)^(mcd(1322382, 739878))
*/

package clasefpro;

public class ProblemaYincanaBase {
  public static void main(String[] args) {
    int sumaPrimosMenoresQue100 = 1;

    for (int numeroComprobando = 2; numeroComprobando < 100; numeroComprobando++) {
      int posibleDivisor = 2;
      boolean primo = true;

      while (primo && (posibleDivisor < numeroComprobando)) {
        if (numeroComprobando % posibleDivisor == 0) {
          primo = false;
        } else {
          posibleDivisor++;
        }
      }

      if (primo) {
        sumaPrimosMenoresQue100 += numeroComprobando;
      }
    }

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
    

    int potencia = (int) Math.pow(sumaPrimosMenoresQue100, mcd);

    System.out.println(
        "La potencia de base la suma de los números primos menores que 100 y exponente el mcd de 1322382 y 739878 es: "
            + potencia);
  }
  
}
