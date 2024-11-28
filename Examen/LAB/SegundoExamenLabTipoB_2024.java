package LAB;

public class SegundoExamenLabTipoB_2024 {
  public static void main(String[] args) {
    int[][] cartones = {
        { 1, 10, 20, 30, 40, 50, 60, 70, 80, 90 },
        { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
        { 4, 8, 15, 16, 23, 42, 53, 64, 75, 86 },
    };

    int numeroAleatorio;
    boolean ganado = false;
    int ganador = 0;

    do {
      numeroAleatorio = (int) (Math.random() * 90 + 1); // [0, 1) * 90 = [0, 90) + 1 = [1, 91) = [1, 90]

      System.out.println("Ha salido el número: " + numeroAleatorio);

      for (int i = 0; i < cartones.length; i++) {
        for (int j = 0; j < cartones[i].length; j++) {
          if (cartones[i][j] == numeroAleatorio) {
            cartones[i][j] = -1;
          }
        }
      }

      for (int i = 0; i < cartones.length && !ganado; i++) {
        ganado = true;
        ganador = i;
        for (int j = 0; j < cartones[i].length; j++) {
          if (cartones[i][j] != -1) {
            ganado = false;
          }
        }
      }

    } while (!ganado);

    System.out.println("Ganador: Jugador#" + (ganador + 1));

  }

}