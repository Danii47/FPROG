
/*
  Escribir un programa Java que muestre en pantalla el número mínimo de
  billetes y monedas en que podrían descomponerse una cantidad entera de
  euros.
*/

import java.util.Scanner;

public class H2Ejercicio04 {
  public static void main(String[] args) {
    System.out.println("Introduce una cantidad de dinero en euros para separarlo en billetes y monedas: ");
    Scanner in = new Scanner(System.in);
    int money = in.nextInt();
    in.close();

    int[] billsValues = { 500, 200, 100, 50, 20, 10, 5 };
    int[] coinsValues = { 2, 1 };

    System.out.println("7532€ divididos en billetes son:");
    for (int i = 0; i < billsValues.length; i++) {
      int bills = money / billsValues[i];
      System.out.println("\t" + bills + " billete(s) de " + billsValues[i] + "€");
      money = money - (billsValues[i] * bills);
    }

    for (int i = 0; i < coinsValues.length; i++) {
      int coins = money / coinsValues[i];
      System.out.println("\t" + coins + " moneda(s) de " + coinsValues[i] + "€");
      money = money - (coinsValues[i] * coins);
    }
  }
}
