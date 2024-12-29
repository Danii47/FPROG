package Otros;
import java.util.Scanner;

public class ExamenPapel {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);
    System.out.print("Introduce el código EAN: ");

    String codigo = in.nextLine();
    in.close();

    if (EANValido(codigo))
      System.out.println("El código es válido.");
    else
      System.out.println("El código no es válido.");

  }

  public static boolean sonNumeros(String cadena) {

    boolean esNumero = true;

    for (int i = 0; i < cadena.length() && esNumero; i++) {
      if (cadena.charAt(i) < '0' || cadena.charAt(i) > '9')
        esNumero = false;
    }
    return esNumero;

  }

  public static boolean EANValido(String codigo) {

    boolean esValido = true;

    if (codigo.length() != 13)
      esValido = false;
    if (!sonNumeros(codigo))
      esValido = false;

    int suma = 0;

    for (int i = 11; i >= 0 && esValido; i--) {
      int digito = codigo.charAt(i) - '0';
      if (i % 2 == 0)
        suma += digito;
      else
        suma += (digito * 3);
    }

    if (esValido) {
      if (suma % 10 == 0) {
        if (codigo.charAt(12) != '0')
          esValido = false;
      } else {
        int digito = 10 - (suma % 10);
        if (codigo.charAt(12) - '0' != digito)
          esValido = false;
      }
    }

    return esValido;
  }
}
