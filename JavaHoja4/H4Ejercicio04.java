/*
  Escriba un programa que pida enteros al usuario hasta que el número introducido sea un 0,
  y escriba en pantalla su suma, la cantidad de números leídos, su media y el menor y el
  mayor de ellos (todo ello sin considerar el 0)
*/

import java.util.Scanner;

public class H4Ejercicio04 {
  public static void main(String[] args) {

    int sum = 0;
    int countNumbers = 0;
    int number = 0;
    int min = 0;
    int max = 0;

    Scanner in = new Scanner(System.in);

    do {
      System.out.println("Introduce números enteros (0 para parar): ");

      if (number == 0) {
        number = in.nextInt();
        min = number;
        max = number;

      } else {
        number = in.nextInt();
        if (number < min) min = number;
        if (number > max) max = number;
      }
      
      if (number != 0) {
        countNumbers++;
        sum += number;
      }
      
    } while (number != 0);

    in.close();

    int average = sum / countNumbers;

    System.out.println("Números leidos: " + countNumbers + "\nSuma: " + sum + "\nMedia: " + average + "\nMáximo: " + max + "\nMínimo: " + min);
  
  }
}
