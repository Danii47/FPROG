import java.util.Scanner;

public class MinimosLab2 {
  public static void main(String[] args) {

    int firstNumber, secondNumber, thirdNumber, auxSecondNumber;

    System.out.println("Introduce los 2 números que definan el intervalo (es indiferente el orden)");

    Scanner in = new Scanner(System.in);
    
    firstNumber = in.nextInt();
    secondNumber = in.nextInt();

   

    // Solo continua la ejecución del programa en caso de que ambos números sean positivos. (el cero queda incluido)
    if (firstNumber >= 0 && secondNumber >= 0) {

      System.out.println("\nIntroduce otro número para comprobar si se encuentra en el intervalo.");

      thirdNumber = in.nextInt();
      in.close();
      
      // Solo continua la ejecución del programa en caso de que el número a buscar en el intervalo sea positivo. (el cero queda incluido)
      if (thirdNumber >= 0) {

        /* Usando una variable auxiliar (auxSecondNumber) y cambio los valores de las variables firstNumber y secondNumber para que firstNumber sea
         * siempre menor o igual que secondNumber y así facilitarme la condición. */

        if (firstNumber > secondNumber) {
          auxSecondNumber = secondNumber;
          secondNumber = firstNumber;
          firstNumber = auxSecondNumber;
        }
    
        // Compruebo que el tercer número introducido este dentro del intervalo introducido.
        if (thirdNumber <= secondNumber && thirdNumber >= firstNumber) {
          System.out.println("si");
        } else {
          System.out.println("no");
        }

      } else {
        System.out.println("El tercer número debe ser positivo.");
      }

    } else {
      System.out.println("El primer y segundo número deben ser positivos.");
    }
  }
}
