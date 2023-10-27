/*
  Escribir un método Java que tome como parámetros 3 números enteros, para
  día, mes y año (supuestamente correctos), y escriba en pantalla la fecha en
  formato dia/mes/año. Escriba otro método que lo escriba en forma mes año, dia
  (al estilo EEUU y algunos otros países). Utilice estos métodos para escribir un
  programa que pida al usuario 3 números enteros para una fecha, y escriba en
  pantalla la fecha en los dos formatos.
*/

import java.util.Scanner;

public class Ejercicio3 {
  
  public static void main(String[] args) {
    System.out.println("Introduce 3 números enteros que correspondan a dia/mes/año: ");
    Scanner in = new Scanner(System.in);
    int day = in.nextInt();
    int month = in.nextInt();
    int year = in.nextInt();
    in.close();
    String EUDate = getEUDate(day, month, year);
    String USDate = getUSDate(day, month, year);

    System.out.println("Fecha en formato europeo: " + EUDate + "\nFecha en formato estadounidense: " + USDate);
  }

  public static String getEUDate(int day, int month, int year) {
    return (day + "/" + month + "/" + year);
  }

  public static String getUSDate(int day, int month, int year) {
    return (month + "/" + day + "/" + year);
  }
}
