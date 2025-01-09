// Contrapalindroma

public class Ejercicio3 {
  public static boolean contrapalindroma(String cadena) {
    // PRE: cadena != null
    if (cadena.length() <= 1) {
      return true; // Caso base
    } else {
      return (
        cadena.charAt(0) != cadena.charAt(cadena.length() - 1) &&
        contrapalindroma(cadena.substring(1, cadena.length() - 1))
      ); // Caso recursivo
    }
  }
  
  public static void main(String[] args) {
    System.out.println(contrapalindroma("hola")); // true
    System.out.println(contrapalindroma("abba")); // false
    System.out.println(contrapalindroma("abca")); // false
    System.out.println(contrapalindroma("abcba")); // false
    System.out.println(contrapalindroma("abcde")); // true
    System.out.println(contrapalindroma("a")); // true
    System.out.println(contrapalindroma("")); // true
  }
}
