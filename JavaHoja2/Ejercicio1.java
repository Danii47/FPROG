/*
  Escribir un programa Java que muestre en pantalla la hora actual en el
  formato horas:minutos:segundos sabiendo que han transcurrido 75936
  segundos desde las doce de la noche de ayer.
*/

public class Ejercicio1 {
  public static void main(String[] args) {
    int totalSeconds = 75936;
    int hours, minutes, seconds;
    String hoursString, minutesString, secondsString;

    // Obtengo los valores númericos de cada variable
    hours = totalSeconds / 3600;
    minutes = (totalSeconds - (hours * 3600)) / 60;
    seconds = (totalSeconds - ((hours * 3600) + (minutes * 60)));

    // Convierto los valores a String para comprobar si es necesario añadirles un 0
    // delante cuando tengan un solo dígito.
    hoursString = hours < 10 ? ("0" + hours) : String.valueOf(hours);
    minutesString = minutes < 10 ? ("0" + minutes) : String.valueOf(minutes);
    secondsString = seconds < 10 ? ("0" + seconds) : String.valueOf(seconds);

    System.out.println("Son las: " + hoursString + ":" + minutesString + ":" + secondsString + ".");
  }
}
