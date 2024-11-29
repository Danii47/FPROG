package clasefpro;

public class Base27 {
  public static void main(String[] args) {
    long numero = 13891392744057L;
    String alfabeto = " abcdefghijklmnopqrstuvwxyz";
    String resultado = "";

    while (numero > 0) {
      int valor = (int) (numero % 27);
      resultado += alfabeto.charAt(valor);
      numero /= 27;
    }

    // Invertir el resultado

    String resultadoInvertido = "";
    for (int i = resultado.length() - 1; i >= 0; i--) {
      resultadoInvertido += resultado.charAt(i);
    }

    System.out.println(resultadoInvertido);
    
  } 
}
/*

*/