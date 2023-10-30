import java.util.Scanner;

public class GetFactorialRecursive {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Escribe un número para hallar su factorial:");
    int number = in.nextInt();
    in.close();

    long factorial = getFactorial(number);
    System.out.println("El factorial de " + number + " es " + factorial);
  }

  public static long getFactorial(int number) {

    if (number <= 1) return 1;
    return number * getFactorial(number - 1);

  }
}
