import java.util.Scanner;

public class H4Ejercicio06Propio {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce un número para factorizar:");
    long numberToFactorice = in.nextLong();
    in.close();

    System.out.print("El número " + numberToFactorice + " factorizado en primos es: ");
    for (long i = 2; i <= numberToFactorice; i++) {
      while (numberToFactorice % i == 0) {
        System.out.print(i + " ");
        numberToFactorice /= i;
      }
    }
  }
}