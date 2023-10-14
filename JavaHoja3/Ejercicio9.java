
/*
  Escribir un programa Java que pida al usuario 3 números enteros, para día, mes
  y año (supuestamente correctos), y un número de opción, 1 ó 0. El programa
  debe entonces escribir en pantalla la fecha en formato dia/mes/año ó mes dia,
  año, según la opción hay sido 1 ó 0, y usando los métodos del ejercicio 3.
*/

import java.util.Scanner;

public class Ejercicio9 {
  public static void main(String[] args) {

    System.out.println("Introduce 3 números enteros que correspondan a dia/mes/año: ");

    Scanner in = new Scanner(System.in);
    int day = in.nextInt();
    int month = in.nextInt();
    int year = in.nextInt();

    System.out.println("Introduce 1 para formato europeo y 0 para formato estadounidense: ");

    int option = in.nextInt();
    in.close();
    
    String date;

    if (option == 1) {
      date = getEUDate(day, month, year);
    } else if (option == 0) {
      date = getUSDate(day, month, year);
    } else {
      date = "Opción no válida";
    }

    System.out.println("Fecha en formato elegido: " + date);
  }

  public static String getEUDate(int day, int month, int year) {
    return (day + "/" + month + "/" + year);
  }

  public static String getUSDate(int day, int month, int year) {
    return (month + "/" + day + "/" + year);
  }
}
