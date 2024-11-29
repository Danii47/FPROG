package LAB;

/*
Escribe un programa que simule el juego del bingo con 3 jugadores y unos cartones predefinidos. Para ello, tendremos una matriz, y cada fila representará el cartón de cada jugador. El juego tendrá 3 jugadores, y cada uno de ellos jugará con los siguientes cartones:
Jugador1 -> 1 10 20 30 40 50 60 70 80 90
Jugador2 -> 1 2 3 4 5 6 7 8 9 10
Jugador3 -> 4 8 15 16 23 42 53 64 75 86

El programa sacará nuevos números de forma aleatoria (que podrían repetirse). Si un número coincide con el de un cartón, este se reemplazará por el valor -1 en la matriz. Cuando un cartón tenga todos sus valores a -1 significará que ha ganado, y se mostrará un mensaje por pantalla con el número del ganador. Entonces, el juego terminará.

Ejemplo:
Inicio del juego del bingo con 3 jugadores y 10 números por cartón!
(Números que van saliendo)
Ganador:Jugador#3
*/

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