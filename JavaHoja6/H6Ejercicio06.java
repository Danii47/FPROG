// CHARAT
// String.charAt(int k) -> Te devuelve la letra que está en esa posición.
// EJ: "Hola".charAt(2) -> "l"
// EJ: "Hola".charAt(0) -> "H"

// INDEXOF
// String.indexOf(String stringToSearch, int firstIndexToSearch (optional)) -> Te devuelve la posición donde encuentra el primer stringToSearch.
// EJ: "Hola mundo".indexOf("mundo") -> 5 (int)
// EJ: "mundo Hola mundo".indexOf("mundo") -> 0 (int)
// EJ: "mundo Hola mundo".indexOf("mundo", 1) -> 11 (int)


import java.util.Scanner;

public class H6Ejercicio06 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce una cadena para comprobar si cumple las condiciones:");
    String str = in.nextLine();

    boolean passConditions = comprobateConditions(str);

    System.out.println("La cadena " + ((passConditions) ? "sí" : "no") + " cumple las condiciones.");

  }

  public static boolean comprobateConditions(String str) {
    return (comprobateFirstCondition(str) && comprobateSecondCondition(str) && comprobateThirdCondition(str));
  }

  public static boolean comprobateFirstCondition(String str) {
    return (
      (str.charAt(0) <= '9' && str.charAt(0) >= '0') &&
      (str.charAt(1) <= '9' && str.charAt(1) >= '0')
    );
  }


  public static boolean comprobateSecondCondition(String str) {
    return (
      str.charAt(2) == 'N' ||
      str.charAt(2) == 'S' ||
      str.charAt(2) == 'E' ||
      str.charAt(2) == 'O'
    );
  }

  public static boolean comprobateThirdCondition(String str) {
    boolean passCondition = false;
    if (str.length() == 4) {
      passCondition = (str.charAt(3) <= '9' && str.charAt(3) >= '0');
    } else if (str.length() == 5) {
      passCondition = (str.charAt(3) == '1' && str.charAt(4) == '0');
    } else {
      System.out.println("El número debe tener como máximo longitud 5");
    }
    return passCondition;
  }
}
