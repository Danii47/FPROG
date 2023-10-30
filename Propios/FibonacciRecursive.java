import java.util.Scanner;

public class FibonacciRecursive {
  public static void main(String[] args) {
    System.out.println("Que posición de la serie de fibonacci quieres obtener:");
    Scanner in = new Scanner(System.in);
    int number = in.nextInt();
    in.close();
    long fibNumber = getFibNumber(number);
    System.out.println("Fibonacci en la posición " + number + " esa posición es: " + fibNumber);

  }

  public static long getFibNumber(int fibonacciPos) {
    if (fibonacciPos <= 2) return 1;

    return getFibNumber(fibonacciPos - 2) + getFibNumber(fibonacciPos - 1);
  }
}
