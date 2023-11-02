import java.util.Scanner; 
public class Asterix { 
  public static void main (String[] args){ 
    Scanner in = new Scanner (System.in); 
    int n; 
    do{ 
    System.out.print("Teclee el número de filas (1 a 53): "); 
      n = in.nextInt(); 
    } while (n < 1 || n > 53);
    /* 0 < n <= 53 */
    int ast, fil, spaces;

    for (fil=0; fil < n; fil++){

      // línea fil: número de asteriscos = fil 
      //spaces = n - 1
      //ast = n - spaces
      for (spaces = 0; spaces < n - fil - 1; spaces++) {

        System.out.print(" ");

      }
      for (ast = 0; ast <= fil; ast++) {

        System.out.print("*");

      }
      System.out.println(); 
        
    } 
  } 
}

