public class H3Ejercicio06 {

  public static void main(String[] args) {
    System.out.print("No, I ");
    zoop();
    System.out.print("I ");
    baffle();
  }

  /*
   * Los métodos no están documentados. Complete la siguiente "documentación":

   * efecto del método ping: escribe un punto
   * efecto del método baffle: escribe "You wug."
   * efecto del método zoop: escribe "You wug.You wugga You wug."
   * efecto del programa: escribe "No, I You wug.You wugga You wug.I You wug."
   * 
  */
  public static void zoop() {
    baffle();
    System.out.print("You wugga ");
    baffle();
  }

  public static void baffle() {
    System.out.print("You wug ");
    ping();
  }

  public static void ping() {
    System.out.println(".");
  }
}

/*
 * main ->  zoop  -> baffle -> ping
 *      -> baffle -> ping
*/
