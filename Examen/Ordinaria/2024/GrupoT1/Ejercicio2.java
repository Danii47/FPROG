/*
[1 pto] Una secuencia de enteros "dinosaurio" de nivel 0 es, por definición, la secuencia vacía. Y para cualquier n > 0,
la secuencia "dinosaurio" de nivel n es la formada por dos secuencias "dinosaurio" de nivel n−1 separadas por el entero nn.

Por ejemplo, las secuencias de enteros "dinosaurio" de nivel 1, 2 y 4 son, respectivamente:
  Nivel 1: 1
  Nivel 2: 1 2 1
  Nivel 4: 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1

Elabore un método recursivo en Java que escriba en pantalla la secuencia "dinosaurio" del nivel de su argumento.
*/
// Nivel 0 -> 
public class Ejercicio2 {
  public static void secuenciaEnterosDinosaurio(int n) {
    if (n != 0) {
      secuenciaEnterosDinosaurio(n - 1);
      System.out.print(" " + n + " ");
      secuenciaEnterosDinosaurio(n - 1);
    }
  }

  public static void main(String[] args) {
    System.out.print("Nivel 1: ");
    secuenciaEnterosDinosaurio(1);

    System.out.print("\nNivel 2: ");
    secuenciaEnterosDinosaurio(2);

    System.out.print("\nNivel 3: ");
    secuenciaEnterosDinosaurio(4);
  }
}
