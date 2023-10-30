
/*
  Escribir un método Java que tome como parámetros una cadena de caracteres
  para el día de la semana, y 3 números enteros, para día, mes y año
  (supuestamente correctos), y escriba en pantalla la fecha en formato largo,
  con el nombre del mes, como por ejemplo "Domingo, 31 de diciembre de 2023".
*/

import java.util.Scanner;

public class H3Ejercicio13 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce el día de la semana: ");
    String day = in.nextLine();
    System.out.println("Introduce 3 números enteros que correspondan con dia/mes/año: ");
    int dayNumber = in.nextInt();
    int month = in.nextInt();
    int year = in.nextInt();
    in.close();
    
    getLongDate(day, dayNumber, month, year);

  }

  public static void getLongDate(String day, int dayNumber, int month, int year) {

    String monthString = "mes no válido"; 

    if (month == 1) monthString = "Enero";
    else if (month == 2) monthString = "Febrero";
    else if (month == 3) monthString = "Marzo";
    else if (month == 4) monthString = "Abril";
    else if (month == 5) monthString = "Mayo";
    else if (month == 6) monthString = "Junio";
    else if (month == 7) monthString = "Julio";
    else if (month == 8) monthString = "Agosto";
    else if (month == 9) monthString = "Septiembre";
    else if (month == 10) monthString = "Octubre";
    else if (month == 11) monthString = "Noviembre";
    else if (month == 12) monthString = "Diciembre";

    System.out.println(day + ", " + dayNumber + " del " + monthString + " de " + year);
  }
}
