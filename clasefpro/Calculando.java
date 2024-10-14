package clasefpro;

public class Calculando {
  // Ejemplo de evaluacion y tipo de datos
  public static void main(String args[]) {
    int n = 2;
    double x = 3;

    // NO SE PUEDE PONER = = EN LUGAR DE ==, ENTIENDO QUE EL ENUNCIADO SE REFIERE A ESO

    // System.out.println("true + true | " + (true + true));   
    System.out.println("\n\u001B[31m1 == 1 | " + "SYNTAX_ERROR");
    System.out.println("\n\u001B[34m29/4.0 | " + (29 / 4.0));
    System.out.println("\n\u001B[31m(float) (29/4) | " + ((float) (29 / 4)));
    System.out.println("\n\u001B[34m29/4 | " + (29 / 4));
    System.out.println("\n\u001B[31m(float) 29/4 | " + ((float) 29 / 4));
    System.out.println("\n\u001B[34mn == 1 | " + (n == 1));
    System.out.println("\n\u001B[31mMath.sqrt(25) | " + Math.sqrt(25));
    System.out.println("\n\u001B[34m4f + x | " + (4f + x));
    System.out.println("\n\u001B[31m10/3 == 10.0/3 | " + (10 / 3 == 10.0 / 3));
    System.out.println("\n\u001B[34m'x' | " + 'x');
    System.out.println("\n\u001B[31m5>=0 && 5<=9 | " + (5 >= 0 && 5 <= 9));
    System.out.println("\n\u001B[34mx == (3+x) | " + (x == (3 + x)));
    System.out.println("\n\u001B[31m4 + x | " + (4 + x));
    System.out.println("\n\u001B[34m20-n*5/8 | " + (20 - n * 5 / 8));
    System.out.println("\n\u001B[31m(int) 7.8 | " + ((int) 7.8) + "\n\u001B[0m");
    
  }
}