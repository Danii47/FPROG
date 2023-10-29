import java.util.Scanner;

public class Factorizar {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce un número para factorizar:");
    int numberToFactorice = in.nextInt();
    in.close();

    System.out.print("El número " + numberToFactorice + " factorizado en primos es: ");
    for (int i = 2; i <= numberToFactorice; i++) {
      while (numberToFactorice % i == 0) {
        System.out.print(i + " ");
        numberToFactorice /= i;
      }
    }
  }
}