# RECURSIVIDAD EN JAVA
## ¿Qué es la recursividad?
La recursividad es una técnica de programación que consiste en que una función se llame a sí misma dentro de su implementación. En otras palabras, una función recursiva es aquella que se llama a sí misma para resolver un problema más pequeño. La recursividad es una técnica muy poderosa, pero también puede ser peligrosa si no se utiliza correctamente, ya que puede llevar a un bucle infinito.

## ¿Cómo plantear un problema recursivo?
Para plantear un problema de forma recursiva, es necesario seguir los siguientes pasos:

**1. Definir el caso base:** Es el caso más simple del problema, que no requiere de llamadas recursivas. Es importante que el caso base sea lo suficientemente simple para que la recursión termine en algún momento.

- Un ejemplo de caso base es el cálculo del factorial de un número, donde el caso base es el factorial de 0, que es 1.
- Otro ejemplo de caso base es el cálculo de la serie de Fibonacci, donde los casos base son las posiciones 0 y 1, cuyos valores son 1 y 1, respectivamente.

**2. Definir el caso recursivo:** Es el caso en el que se llama a la función recursiva para resolver un problema más pequeño. En este caso, se debe plantear el problema de forma que se acerque al caso base en cada llamada recursiva.

## Ejemplos de recursividad en Java

### Ejemplo 1: Cálculo del factorial de un número
El factorial de un número entero positivo n se define como el producto de todos los números enteros positivos desde 1 hasta n. Se denota como n! y se define de la siguiente manera:

n! = n * (n-1) * (n-2) * ... * 1

Además, se define que 0! = 1.

A continuación se muestra un ejemplo de cómo calcular el factorial de un número de forma recursiva en Java:

```java
public class Factorial {
  public static int factorial(int n) {
    if (n == 0) {
      return 1; // Caso base
    } else {
      return n * factorial(n - 1); // Caso recursivo
    }
  }

  public static void main(String[] args) {
    int n = 5;
    int resultado = factorial(n);
    System.out.println("El factorial de " + n + " es: " + resultado); // Imprime: El factorial de 5 es: 120
  }
}
```

En este ejemplo, la función `factorial` calcula el factorial de un número `n` de forma recursiva. En el caso base, si `n` es igual a 0, se devuelve 1. En el caso recursivo, se calcula el factorial de `n` multiplicando `n` por el factorial de `n-1`.

### Ejemplo 2: Cálculo de la serie de Fibonacci
La serie de Fibonacci es una secuencia de números en la que cada número es la suma de los dos anteriores. La secuencia comienza con los números 1 y 1, y continúa de la siguiente manera: 1, 1, 2, 3, 5, 8, 13, 21, ...

A continuación se muestra un ejemplo de cómo calcular un número de la serie de Fibonacci de forma recursiva en Java:

```java
public class Fibonacci {
  public static int fibonacci(int n) {
    if (n == 0 || n == 1) {
      return 1; // Caso base
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2); // Caso recursivo
    }
  }

  public static void main(String[] args) {
    int n = 7;
    int resultado = fibonacci(n);
    System.out.println("El número en la posición " + n + " de la serie de Fibonacci es: " + resultado); // Imprime: El número en la posición 7 de la serie de Fibonacci es: 21
  }
}
```

En este ejemplo, la función `fibonacci` calcula un número de la serie de Fibonacci en la posición `n` de forma recursiva. En los casos base, si `n` es igual a 0 o 1, se devuelve 1. En el caso recursivo, se calcula el número en la posición `n` sumando los números en las posiciones `n-1` y `n-2` de la serie de Fibonacci.

### Ejemplo 3: Secuencia de enteros dinosaurios (EJERCICIO DE EXÁMEN)

#### Enunciado:

**[1 pto]** Una secuencia de enteros **"dinosaurio"** de **nivel 0** es, por definición, la secuencia **vacía**. Y para cualquier **n > 0**,
la secuencia **"dinosaurio"** de nivel **n** es la formada por **dos secuencias** **"dinosaurio"** de nivel **n−1** separadas por el entero **n**.

Por ejemplo, las secuencias de enteros "dinosaurio" de nivel 1, 2 y 4 son, respectivamente:
  
- **Nivel 1: 1**
- **Nivel 2: 1 2 1**
- **Nivel 4: 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1**

Elabore un método **recursivo** en Java que escriba en pantalla la secuencia **"dinosaurio"** del **nivel** de su **argumento**.

> [!TIP]
> Vale, para resolver este problema, lo primero es plantear el caso base y el caso recursivo. En este caso, el caso base es el nivel 0, que corresponde a la secuencia vacía. El caso recursivo es el nivel n, que se forma a partir de dos secuencias dinosaurio de nivel n-1 separadas por el entero n.

```java
public class Dinosaurio {
  public static void dinosaurio(int n) {
    if (n == 0) {
      System.out.print(""); // Caso base
    } else {
      // Caso recursivo
      dinosaurio(n - 1); // <- Llamada recursiva a la primera secuencia dinosaurio de nivel n-1
      System.out.print(n + " "); // Imprime el entero n con un espacio para separar las secuencias
      dinosaurio(n - 1); // <- Llamada recursiva a la segunda secuencia dinosaurio de nivel n-1
    }
  }

  public static void main(String[] args) {
    dinosaurio(1); // Imprime: 1
    System.out.println(); // <- Esto lo pongo simplemente para que haya un salto de línea

    dinosaurio(2); // Imprime: 1 2 1
    System.out.println();

    dinosaurio(4); // Imprime: 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1
    System.out.println();
  }
}
```

En este ejemplo, la función `dinosaurio` es un método recursivo que imprime en pantalla la secuencia dinosaurio del nivel `n`. En el caso base, si `n` es igual a `0`, no se imprime nada. En el caso recursivo, se llama recursivamente a la función `dinosaurio` para los niveles `n-1`, se imprime el entero `n` y se llama recursivamente a la función `dinosaurio` para los niveles `n-1`.