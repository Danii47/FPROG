public class RedSocial {
  

  public static void main(String[] args) {
    
  }

  public static int edadMasRepetida(TPersona[] personas) {

    int contadorRepetida = 1;
    int vecesMasRepetida = 0;
    int edadMasRepetida = personas[0].edad;

    for (int i = 1; i < personas.length; i++) {

      if (personas[i].edad == personas[i - 1].edad) {
        contadorRepetida++;

        if (contadorRepetida > vecesMasRepetida) {
          vecesMasRepetida = contadorRepetida;
          edadMasRepetida = personas[i].edad;
        }
      } else {
        contadorRepetida = 1;
      }

    }
    return edadMasRepetida;
  }
}

class TPersona {
  int edad;
  String nombre;
  String apellidos;

  public TPersona(int edad, String nombre, String apellidos) {
    this.edad = edad;
    this.nombre = nombre;
    this.apellidos = apellidos;
  }
}