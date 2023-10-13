/*
  Escribir un programa Java que calcule el interés de un depósito de 234.000€
  al 4.5% y lo muestre en pantalla.
*/

public class Ejercicio2 {
  public static void main(String[] args) {
    int deposit = 234_000;
    double interest = 4.5;

    // Hago el el 4.5% de 234000.
    double interestValue = (deposit / 100) * interest;

    System.out.println("El interés de un deposito de " + deposit + "€ al " + interest + "% es de: " + interestValue + "€");
  }
}
