package clasefpro;

/*
En la secuencia de Conway cada número se obtiene a partir del anterior (n) aplicando las
siguientes reglas:
 Si n es par, el siguiente número es n/2
 Si n es impar, el siguiente es 3n+1
 Pero si n=1, no hay siguiente, la secuencia termina.
Por ejemplo el número 13 produce la cadena: 13 – 40 – 20 – 10 – 5 – 16 – 8 – 4 – 2 – 1, que
consta de 10 elementos, por lo que se dice que tiene longitud 10.
… CALCULAR EL NÚMERO ENTRE 1 Y 1000000 QUE GENERA LA CADENA DE
CONWAY MÁS LARGA
*/

// Ante la duda, usar el tipo de dato más grande posible (long)
public class Conway {
  public static void main(String[] args) {

    int longitudMaxima = 1;
    int numeroLogitudMaxima = 1;

    for (int i = 1; i <= 1000000; i++) {
      int longitud = 1;
      long numero = i;

      while (numero != 1) {
        if (numero % 2 == 0) {
          numero = numero / 2;
        } else {
          numero = 3 * numero + 1;
        }

        longitud++;
      }
      
      if (longitud > longitudMaxima) {
        System.out.println("Nueva logitud máxima: " + longitud + " para el número " + i);
        
        longitudMaxima = longitud;
        numeroLogitudMaxima = i;
      }
    }
    System.out.println("El número " + numeroLogitudMaxima + " genera la cadena de Conway más larga con longitud " + longitudMaxima);
  }
}
