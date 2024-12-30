# OBJETOS EN JAVA

## 1. ¿Qué es una clase?
Una **clase** es un **modelo** que define un **conjunto** de **atributos** (**variables**) y **métodos** (**funciones** y **procedimientos**) que tendrán los objetos que se creen a partir de ella. Es decir, una clase es una <u>**plantilla**</u> que define el **comportamiento** y las **características** de los **objetos** que se creen a partir de **ella**.

```java
// Definición de una clase llamada Persona con atributos y métodos
// Esto sería la plantilla
public class Persona {

  // Atributos
  String nombre;
  int edad;
  String sexo;
  // Fin de los atributos

  // Métodos
  public void caminar() {
    System.out.println("Estoy caminando");
  }

  public void hablar() {
    System.out.println("Estoy hablando");
  }
  // Fin de los métodos
}
```

> [!TIP]
> En Java, una clase es un tipo de dato que se define con la palabra reservada `class` seguida del nombre de la clase. En el ejemplo anterior, la clase se llama `Persona`. Dentro de las llaves `{}` se definen los atributos y métodos de la clase.


## 2. ¿Qué es un objeto / instancia?
Un objeto es una instancia de una clase, digamos la materialización de una clase. Es una entidad que tiene un estado que se compone de atributos y un comportamiento que se compone de métodos (funciones y procedimientos).

```java
// Creación de un objeto de la clase Persona
// Esto sería la materialización de la plantilla
Persona alumno = new Persona();

// Asignación de valores a los atributos del objeto
alumno.nombre = "Juan";
alumno.edad = 25;
alumno.sexo = "Masculino";

// Llamada a los métodos del objeto
alumno.caminar(); // Imprime: Estoy caminando
alumno.hablar(); // Imprime: Estoy hablando
```

> [!TIP]
> Para entender más facilmente la diferencia entre una clase y un objeto, piensa en una clase como un plano de una casa, un esqueleto, una plantilla. Y piensa en un objeto como la casa construida a partir de ese plano, con todas sus características y funcionalidades.

### Otros ejemplos de clase y objeto
Otro ejemplo tipico que se usa es el de la clase `Coche` y el objeto `deportivo`:

```java
// Definición de una clase llamada Coche con atributos y métodos
// La plantilla
public class Coche {

  // Atributos
  String marca;
  String modelo;
  int anio;
  // Fin de los atributos

  // Métodos
  public void acelerar() {
    System.out.println("Estoy acelerando");
  }

  public void frenar() {
    System.out.println("Estoy frenando");
  }
  // Fin de los métodos
}

// Creación de un objeto de la clase Coche
// La materialización de la plantilla
Coche deportivo = new Coche();

// Asignación de valores a los atributos del objeto
deportivo.marca = "Ferrari";
deportivo.modelo = "F8 Tributo";
deportivo.anio = 2020;

// Llamada a los métodos del objeto
deportivo.acelerar(); // Imprime: Estoy acelerando
deportivo.frenar(); // Imprime: Estoy frenando
```

## 3. ¿Qué es un método constructor?
Un **constructor** es un **método especial** que se llama **automáticamente** cuando se crea un **objeto** de una **clase**. Su función principal es **inicializar** los **atributos** del **objeto**, justo lo que haciamos arriba uno a uno una vez creado el objeto. En **Java**, el nombre del **constructor** es el **mismo** que el **nombre** de la **clase**.

```java
// Definición de una clase llamada Coche con atributos y métodos
// La plantilla
public class Coche {

  // Atributos
  String marca;
  String modelo;
  int anio;
  // Fin de los atributos

  // Constructor
  // Método constructor que se llama automáticamente al crear un objeto
  public Coche(String marca, String modelo, int anio) {
    this.marca = marca; /* <- Esto del this lo explicaré en el siguiente punto */
    this.modelo = modelo; /* <- */
    this.anio = anio; /* <- */
  }
  // Fin del constructor

  // Métodos
  public void acelerar() {
    System.out.println("Estoy acelerando");
  }

  public void frenar() {
    System.out.println("Estoy frenando");
  }

  public void decirMarca() {
    System.out.println("La marca del coche es: " + marca);
  }
  // Fin de los métodos
}

// Creación de un objeto de la clase Coche
// La materialización de la plantilla
// ESTOS VALORES SON LOS QUE RECIBE EL CONSTRUCTOR, ES COMO PASARLE LOS ARGUMENTOS A UNA FUNCIÓN NORMAL
Coche deportivo = new Coche("Ferrari", "F8 Tributo", 2020);

// Gracias a haber creado un constructor, ya no es necesario asignar los valores uno a uno, el objeto ya tiene esos valores

// Llamada a los métodos del objeto
deportivo.acelerar(); // Imprime: Estoy acelerando
deportivo.frenar(); // Imprime: Estoy frenando
deportivo.decirMarca(); // Imprime: La marca del coche es: Ferrari
```

> [!TIP]
> El método `decirMarca()` es un método que muestra el valor del atributo `marca` del objeto `deportivo`, recalco del objeto `deportivo`, unicamente, en caso de crear otro objeto con otros valores en sus atributos el metodo `decirMarca()` diría la marca correspondiente a ese objeto. En este caso, el valor de `marca` es "Ferrari".


## 4. ¿Qué es la palabra reservada `this`?
La palabra reservada `this` es una **referencia** a la **instancia actual** de un **objeto**. Se utiliza para **referenciar** los **atributos** y **métodos** del **objeto** actual. En el ejemplo anterior, el uso de `this` en el constructor de la clase `Coche` es para **diferenciar** entre el **atributo** de la **clase** y el **parámetro** del **constructor** que tienen el **mismo nombre**.

```java
public class Persona {

  // Atributos
  String nombre;
  int edad;
  String sexo;
  // Fin de los atributos

  // Constructor
  public Persona(String nombre, int edad, String sexo) {
    this.nombre = nombre; // this.nombre hace referencia al atributo nombre de la clase
    this.edad = edad; // this.edad hace referencia al atributo edad de la clase
    this.sexo = sexo; // this.sexo hace referencia al atributo sexo de la clase
  }
  // Fin del constructor

  // Métodos
  public void decirNombre() {
    // Este método imprime el nombre del objeto que llama al método
    System.out.println("Mi nombre es: " + nombre);
  }

  public void decirNombreYNombreDeMiAmigo(String nombre) {
    // Este método imprime el nombre del objeto que llama al método y el nombre que se le pasa como argumento
    System.out.println("Mi nombre es: " + this.nombre); // this.nombre hace referencia al atributo nombre de la clase
    System.out.println("El nombre de mi amigo es: " + nombre); // nombre hace referencia al parámetro del método, lo que se le haya pasado

    // En este caso, SÍ es necesario usar this para diferenciar entre el atributo de la clase y el parámetro del método, esa es la principal función de this en Java
  }
}

// Creación de un objeto de la clase Persona
Persona juan = new Persona("Juan", 25, "Masculino");

// Llamada a los métodos del objeto
juan.decirNombre(); // Imprime: Mi nombre es: Juan
juan.decirNombreYNombreDeMiAmigo("Pedro"); // Imprime: Mi nombre es: Juan, El nombre de mi amigo es: Pedro | Revisar el método definido en la clase Persona para ver el uso de this
```

> [!TIP]
> En el método `decirNombreYNombreDeMiAmigo(String nombre)`, el uso de `this` en `this.nombre` es para **diferenciar** entre el **atributo** de la **clase** y el **parámetro** del **método** que tienen el **mismo nombre**. En este caso, `this.nombre` hace referencia al atributo `nombre` de la clase `Persona`, mientras que `nombre` hace referencia al parámetro del método `decirNombreYNombreDeMiAmigo(String nombre)`.

## 5. Un ejemplo completo de exámen

#### Enunciado:

**[2 ptos]** Para almacenar las cadenas cifradas con el método **CesarASCII** se utilizan registros de tipo **regCIFRADO**,
que contienen **tres** campos: uno que almacena la **cadena**, otro que almacena la **clave de cifrado** (entero) y otro que
guarda un **valor** que determina si la cadena almacenada **está cifrada** o **es la original**. Se pide:

**a)** Definir el tipo de datos **regCIFRADO**.

b) Elaborar un **método** Java que, a partir de un **vector** de **registros** de tipo **regCIFRADO**, y una **clave**,
localice en el vector **todos** los elementos que tienen esa clave y, para cada uno de ellos:

  Si la **cadena** está **cifrada**, la cambie por la **cadena descifrada**.
  Si la **cadena** es la **original**, la cambie por la **cadena cifrada**.

En **ambos** casos, **actualice** el campo de **cifrado**.

Nota: **CesarASCII** es un método que cifra una cadena de texto utilizando el **cifrado César**. El cifrado César es una técnica de cifrado muy simple que consiste en **desplazar** cada letra de un texto un número fijo de posiciones en el alfabeto. Por ejemplo, si el desplazamiento es 3, la letra A se cifra como D, la B como E, la C como F, etc.

Un ejemplo de uso de **CesarASCII** sería:

```java
// Desplazamiento de 3 posiciones a la derecha cada letra de la cadena
String cadenaCifrada = CesarASCII("Hola", 3);
System.out.println(cadenaCifrada); // Imprime: Krod
```


> [!TIP]
> Vale, este ejercicio de primeras puede parecer un poco lioso, desde que leemos la palabra **registro** ya nos podemos imaginar que nos están hablando de **objetos**. Siempre que veas la palabra **registro** en un ejercicio de **FPRO** (**únicamente** en **FPRO**), piensa en **objetos** y **clases**. En este caso, nos piden que creemos un **registro** llamado **regCIFRADO** (la clase) que tenga **tres campos** (tres **atributos**): uno para la **cadena**, un `String`, otro para la **clave de cifrado**, un `int` y otro para **determinar** si la cadena está **cifrada** o es la **original**, un valor `boolean`.
> Empezaremos por esto, cuando nos dicen que definamos el tipo de datos **regCIFRADO**, debemos crear una **clase** llamada **regCIFRADO** con los tres atributos que nos piden. Además, es recomendable que creeis dos **constructores** para esta clase que inicialice los atributos de la clase, uno pasandoselos por parametro y otro que sean los valores predeterminados en caso de crearlo sin valores pasados por parametro.

```java
// Definición de la clase regCIFRADO
public class regCIFRADO {

  // Atributos
  String cadena;
  int clave;
  boolean cifrado;
  // Fin de los atributos

  // Constructor que inicializa los atributos de la clase con los valores pasados por parámetro
  public regCIFRADO(String cadena, int clave, boolean cifrado) {
    this.cadena = cadena;
    this.clave = clave;
    this.cifrado = cifrado;
  }
  // Fin del constructor

  // Constructor que inicializa los atributos de la clase con valores predeterminados
  public regCIFRADO() {
    // Este método se considera una buena práctica para inicializar los atributos de la clase con valores predeterminados en caso de que no se pasen valores por parámetro
    this.cadena = "";
    this.clave = 0;
    this.cifrado = false;
  }
  // Fin del constructor
}
```

Esto ya sería la **primera parte** del ejercicio, la **definición** de la **clase** **regCIFRADO** con sus **atributos** y **constructores**. Ahora, pasamos a la **segunda parte** del ejercicio.

> [!TIP]
> Luego nos piden que creemos un **método** que, a partir de un **vector** de **registros** de tipo **regCIFRADO** y una **clave**, localice en el vector **todos** los elementos que tengan esa clave y, para cada uno de ellos, si la cadena está cifrada, la cambie por la cadena descifrada y si la cadena es la original, la cambie por la cadena cifrada. En ambos casos, **actualice** el campo de cifrado.

```java
// Método que descifra o cifra las cadenas de un vector de registros de tipo regCIFRADO
public void descifrarCifrar(regCIFRADO[] registros, int clave) {
  for (int i = 0; i < registros.length; i++) {
    if (registros[i].clave == clave) {

      if (registros[i].cifrado) {
        // Si la cadena está cifrada, la desciframos
        registros[i].cadena = CesarASCII(registros[i].cadena, -clave);
        
        // Actualizamos el campo de cifrado para indicar que la cadena ya no está cifrada
        registros[i].cifrado = false;
      } else {
        // Si la cadena es la original, la ciframos
        registros[i].cadena = CesarASCII(registros[i].cadena, clave);

        // Actualizamos el campo de cifrado para indicar que la cadena ahora está cifrada
        registros[i].cifrado = true;
      }
    }
  }
}