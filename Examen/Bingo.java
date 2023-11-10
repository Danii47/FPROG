import java.util.Scanner;
import java.lang.Math;


public class Bingo {
  public static void main(String[] args) {

    System.out.println("Bienvenid@ al bingo! Mucha suerte en tu juego.\n");
    boolean[] numbersAlreadyUsed = new boolean[90];

    for (int i = 0; i < numbersAlreadyUsed.length; i++) {
      numbersAlreadyUsed[i] = false;
    }

    Scanner in = new Scanner(System.in);
    String answer;
    int countNumbers = 0;
    int number;

    do {

      do {
        number = (int) ((Math.random() * 90) + 1); // [1, 90]
      } while (numbersAlreadyUsed[number - 1]);

      numbersAlreadyUsed[number - 1] = true;
      System.out.println(number);

      countNumbers++;

      answer = in.nextLine();
    } while (!answer.equals("bingo") && countNumbers < 90);

    in.close();

    if (countNumbers < 90) {

      for (int i = 0; i < numbersAlreadyUsed.length; i++) {
        if (numbersAlreadyUsed[i]) {
          System.out.print((i + 1) + " ");
        }
      }
    } else {
      System.out.println("El juego ha terminado por que han sido escritos todos los números.");
    }

  }
}
