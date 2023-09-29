package Java.Multiplicar;

import java.util.Scanner;

public class Multiplicar {
  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);
    String cadena = input.nextLine();
    int num = input.nextInt();
    double d = input.nextDouble();
    input.close();
    System.out.println(cadena);
    System.out.println(num);
    System.out.println(d);
  }
}
