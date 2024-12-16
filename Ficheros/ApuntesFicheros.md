# APUNTES DE FICHEROS EN JAVA


- [APUNTES DE FICHEROS EN JAVA](#apuntes-de-ficheros-en-java)
  - [Try-Catch](#try-catch)
  - [File](#file)
  - [Scanner](#scanner)
  - [FileWriter](#filewriter)

## Try-Catch

El bloque `try-catch` se utiliza para manejar excepciones (errores) en Java. Un bloque `try-catch` consta de dos partes: el bloque `try` y el bloque `catch`. El bloque `try` contiene el código que puede lanzar una excepción, y el bloque `catch` contiene el código que maneja la excepción. Opcionalmente, se puede añadir un bloque `finally` que se ejecutará siempre, independientemente de si se lanza una excepción o no.

A continuación, se muestra un ejemplo de cómo utilizar un bloque `try-catch` para manejar una excepción de tipo :

```java
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    try {
      int n = scanner.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("Error: No se ha introducido un número entero");
    }
  }
}
```

En el ejemplo anterior, se ha utilizado un bloque `try-catch` para manejar una excepción de tipo `InputMismatchException`. Si el usuario introduce un valor que no es un número entero, se lanzará una excepción de tipo `InputMismatchException`, y el código dentro del bloque `catch` se ejecutará. En este caso, se imprimirá un mensaje de error en la consola pero **NO** se parará el programa por un fallo.


## File

La clase `File` se utiliza para manipular ficheros y directorios en Java. Para utilizar la clase `File`, se debe importar la clase `File` de la librería `java.io.File`. A continuación, se muestra un ejemplo de cómo crear un objeto de la clase `File`:

```java
import java.io.File;

public class Main {
  public static void main(String[] args) {
    File file = new File("nombre_fichero.txt");
  }
}
```

El constructor de la clase `File` recibe como argumento la ruta del fichero o directorio que se quiere manipular. En el ejemplo anterior, se ha creado un objeto de la clase `File` que representa un fichero llamado `nombre_fichero.txt` en el directorio actual. En caso de que el fichero no exista, se creará un objeto de la clase `File` que representa un fichero que no existe.


## Scanner

Para leer un fichero de texto en Java, se puede utilizar la clase `Scanner`. Para ello, se debe importar la clase `Scanner` de la librería `java.util.Scanner`. A continuación, se muestra un ejemplo de cómo leer un fichero de texto con `Scanner`:

```java
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    try {
      File file = new File("nombre_fichero.txt");
      Scanner scanner = new Scanner(file);

      // También: Scanner scanner = new Scanner(new File("nombre_fichero.txt"));

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(line);
      }
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Fichero no encontrado");
    }
  }
}
```

> Cuando ponemos new Scanner(System.in) estamos creando un objeto de la clase Scanner que lee de la entrada estándar (teclado). Si queremos leer de un fichero, debemos pasarle un objeto de la clase File como argumento.

El objeto Scanner tiene varios métodos para leer datos de un fichero, como `nextLine()`, `nextInt()`, `nextDouble()`, etc. En el ejemplo anterior, se ha utilizado el método `hasNextLine()` para comprobar si hay más líneas en el fichero, y el método `nextLine()` para leer la siguiente línea (el método nextLine() devuelve la linea leida y mueve el cursor a la siguiente linea).

También existe el método `hasNextInt()` para comprobar si hay más enteros en el fichero, y el método `nextInt()` para leer el siguiente entero (`hasNextDouble()` para comprobar si hay un decimal en el fichero, etc).

> [!WARNING]
> En caso de que el objeto `File` este representando un fichero que no existe, se lanzará una excepción de tipo `FileNotFoundException` al intentar leer el fichero con `Scanner`. Esta excepción debe ser capturada con un bloque `try-catch` para evitar que el programa se detenga.


## FileWriter

Para escribir en un fichero de texto en Java, se puede utilizar la clase `FileWriter`. Para ello, se debe importar la clase `FileWriter` de la librería `java.io.FileWriter`. A continuación, se muestra un ejemplo de cómo escribir en un fichero de texto con `FileWriter`:

```java
import java.io.FileWriter;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    try {
      FileWriter writer = new FileWriter("nombre_fichero.txt");

      writer.write("Hola, mundo!\n"); // <- El salto de linea debe ser añadido a mano

      writer.close();
    } catch (IOException e) {
      System.out.println("Error al escribir en el fichero");
    }
  }
}
```

En caso de que el fichero no exista, se creará un nuevo fichero con el nombre especificado. Si el fichero ya existe, se sobrescribirá el contenido del fichero con el nuevo contenido.

El objeto `FileWriter` tiene un método `write()` que se utiliza para escribir en el fichero. En el ejemplo anterior, se ha utilizado el método `write()` para escribir la cadena `"Hola, mundo!"` en el fichero `nombre_fichero.txt`.

**El método `write()` no añade el salto de linea, debe ser añadido a mano.**

> [!WARNING]
> En caso de que el fichero no pueda ser creado o escrito, se lanzará una excepción de tipo `IOException` al intentar escribir en el fichero con `FileWriter`. Esta excepción debe ser capturada con un bloque `try-catch` para evitar que el programa se detenga.
