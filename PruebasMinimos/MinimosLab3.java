import java.util.Scanner;

public class MinimosLab3 {
  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    System.out.println("Introduce la cadena de caracteres:");
    String word = in.nextLine();
    in.close();

    int numbersSum = 0;
    int numbers = 0;
    int vocals = 0;
    int consonants;
    char character;

    for (int i = 0; i < word.length(); i++) {
      character = word.charAt(i);
      if (character >= '0' && character <= '9') {
        numbersSum += (character - '0');
        numbers++;
      } else if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u') {
        vocals++;
      }
    }
    consonants = word.length() - vocals - numbers;

    int multiplication = numbersSum * consonants * vocals;

    System.out.println(multiplication);
  }
}