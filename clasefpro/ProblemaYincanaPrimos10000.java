package clasefpro;

public class ProblemaYincanaPrimos10000 {
  public static void main(String[] args) {
    // Comienzo la suma en 1 porque el 1 lo consideramos primo.
    long suma = 1;

    // Comienzo en 2 porque el 1 ya esta sumado.
    for (int numeroComprobando = 2; numeroComprobando <= 10000; numeroComprobando++) {
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
        suma += numeroComprobando;
      }
    }

    System.out.println("La suma de los 10000 primeros primos es: " + suma);
  }
}
