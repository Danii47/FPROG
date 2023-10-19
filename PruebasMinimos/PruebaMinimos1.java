import java.util.Scanner;

public class PruebaMinimos1 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// Declaro "secondsWithDecimals" como -1 para entrar dentro del bucle while
		float secondsWithDecimals = -1;

		Mientras el número introducido sea menor que 0, se solicita al usuario el número de nuevo
		while (secondsWithDecimals < 0) {
			System.out.println("Introduce un número positivo de segundos:");
			secondsWithDecimals = in.nextFloat();
		}

		// Obtengo el valor entero de los segundos con decimales
		int seconds = (int) secondsWithDecimals;

		// Calculo el número de minutos dividiendo los segundos entre 60, posteriormente resto el número de segundos asignados a los minutos a la variable seconds
		int minutes = seconds / 60;
		seconds -= (minutes * 60);

		// Calculo el número de horas dividiendo los minutos entre 60, posteriormente resto el número de minutos asignados a las horas a la variable hours
		int hours = minutes / 60;
		minutes -= (hours * 60);

		System.out.println(secondsWithDecimals + " (sin decimales) expresado en horas, minutos y segundos es: " + hours + " " + minutes + " " + seconds);
	}

}
