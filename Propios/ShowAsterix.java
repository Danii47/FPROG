import java.util.Scanner;

public class ShowAsterix {
  public static void main(String[] args) {


    System.out.println("Introduce el número de filas hasta la mitad que deseas que tenga.");
    Scanner in = new Scanner(System.in);
    int lines = in.nextInt();
    in.close();

    for (int line = 0; line < (lines); line++) {

      for (int space = 0; space < (lines - line - 1); space++) {
        System.out.print(" ");
      }

      for (int asterix = 0; asterix < (line * 2 + 1); asterix++) {
        System.out.print("*");
      }
      
      System.out.println();
    }

    for (int line = 0; line < (lines - 1); line++) {

      for (int space = 0; space < (line + 1); space++) {
        System.out.print(" ");
      }

      for (int asterix = 0; asterix < (lines + (line + 3) - (line * 2)); asterix++) {
        System.out.print("*");
      }
      

      System.out.println();
    }
    // lines + lines / 2
    // lines = 6; line = 0; asterix = 9
    // lines = 5; line = 0; asterix = 7
    // lines = 4; line = 0; asterix = 5
    // lines = 3; line = 0; asterix = 3
    // lines = 2; line = 0; asterix = 1
    // lines = 6; line = 1; asterix = 7


    // lines = 5; line = 1; asterix = 5
    // lines = 5; line = 2; asterix = 3
    // lines = 5; line = 3; asterix = 1

  }
}
