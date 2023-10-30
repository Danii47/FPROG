/*
  Escribir un método Java que tome como parámetros una cadena de caracteres
  para el día de la semana, y 3 números enteros, para día, mes y año
  (supuestamente correctos), y escriba en pantalla la fecha en formato largo,
  como por ejemplo "Domingo, 31 del 12 de 2023". 
*/

import java.util.Scanner;

public class H3Ejercicio04 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce el día de la semana: ");
    String day = in.nextLine();
    System.out.println("Introduce 3 números enteros que correspondan con dia/mes/año: ");
    int dayNumber = in.nextInt();
    int month = in.nextInt();
    int year = in.nextInt();
    in.close();
    String longDate = getLongDate(day, dayNumber, month, year);
    System.out.println("Fecha en formato largo: " + longDate);
  }

  public static String getLongDate(String day, int dayNumber, int month, int year) {
    return (day + ", " + dayNumber + " del " + month + " de " + year);
  }
}
