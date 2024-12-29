package Otros;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EjercicioExtraordinariaContraseña {
  public static void main(String[] args) {

    File file = new File("./Examen/FilePassword.txt");
    try {
      Scanner in = new Scanner(file);

      String password = "";
      boolean lineaCorrecta = true;
      int contadorLinea = 0;

      while (in.hasNextLine() && lineaCorrecta) {
        String line = in.nextLine();
        if (comprobarLinea(line, contadorLinea)) {

          char letra = line.charAt(contadorLinea);
          char nuevaLetra = ' ';

          if (letra >= 'a' && letra <= 'z') {
            nuevaLetra = (letra == 'a') ? 'z' : (char) (letra - 1);
          } else if (letra >= 'A' && letra <= 'Z') {
            nuevaLetra = (letra == 'A') ? 'Z' : (char) (letra - 1);
          }

          password += nuevaLetra;
        } else
          lineaCorrecta = false;

        contadorLinea++;
      }
      
      if (lineaCorrecta)
        System.out.println("La contraseña es: " + password);
      else
        System.out.println("Contraseña Errónea");

      in.close();
    } catch (FileNotFoundException e) {
      System.out.println("No se encontró el archivo.");
      return;
    }

  }

  public static boolean comprobarLinea(String linea, int posicionLinea) {
    boolean comprobar = false;
    if (linea.length() >= posicionLinea) {
      if ((linea.charAt(posicionLinea) >= 'a' && linea.charAt(posicionLinea) <= 'z')
          || (linea.charAt(posicionLinea) >= 'A' && linea.charAt(posicionLinea) <= 'Z')) {
        comprobar = true;
      }
    }

    return comprobar;
  }

}
