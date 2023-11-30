import java.util.Scanner;

public class MinimosLab4 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Introduce el extremo inferior del intervalo:");
    int lowerExtreme = in.nextInt();
    System.out.println("Introduce el extremo superior del intervalo:");
    int upperExtreme = in.nextInt();

    // Valor absoluto de la diferencia
    int intervalLength = (upperExtreme - lowerExtreme + 1 >= 0) ? upperExtreme - lowerExtreme + 1 : -(upperExtreme - lowerExtreme + 1);

    int[] interval = new int[intervalLength];

    for (int i = 0; i < interval.length; i++) {
      interval[i] = 0;
    }

    int value;

    do {
      System.out.println("Introduce valor dentro del intervalo [" + lowerExtreme + ", " + upperExtreme + "]");
      value = in.nextInt();
      if (value != 0 && (value >= lowerExtreme && value <= upperExtreme))
        interval[value - lowerExtreme]++;
    } while (value != 0);

    int lowestValue = lowerExtreme;
    int frequencyValue = 0;

    in.close();

    for (int i = 0; i < interval.length; i++) {
      if (interval[i] != 0 && (interval[i] < frequencyValue || frequencyValue == 0)){
        frequencyValue = interval[i];
        lowestValue = i + lowerExtreme;
      }
    }

    System.out.println(lowestValue);
  }
}