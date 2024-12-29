package Otros;
public class Problema1 {

}

class Contenido {
  private String tipo;

  public void Play() {
    System.out.println("Reproduciendo " + this.tipo);
  }

  public String getTipo() {
    return this.tipo;
  }
}

class Coleccion {

  private Contenido[] contenidos;
  private String tipo;
  private final String nombre;
  
  public Coleccion(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return this.nombre;
  }

  public int getNumeroContenidos() {
    return this.contenidos.length;
  }

  public String getTipo() {
    return this.tipo;
  }

  public void insertarContenido(Contenido contenido) {
    if (contenido.getTipo() == this.tipo) {
      
      Contenido[] contenidosNuevos = new Contenido[this.contenidos.length + 1];
  
      for (int i = 0; i < this.contenidos.length; i++) {
        contenidosNuevos[i] = this.contenidos[i];
      }
  
      contenidosNuevos[this.contenidos.length] = contenido;
      this.contenidos = contenidosNuevos;
    }
  }

  public Contenido obtenerContenido(int posicion) {
    return this.contenidos[posicion];
  }

  public void eliminarContenido(int posicion) {
    Contenido[] contenidosNuevos = new Contenido[this.contenidos.length - 1];

    for (int i = 0; i < posicion; i++) {
      contenidosNuevos[i] = this.contenidos[i];
    }

    for (int i = posicion; i < this.contenidos.length; i++) {
      contenidosNuevos[i] = this.contenidos[i + 1];
    }

    this.contenidos = contenidosNuevos;
  }

  public void reproducirColeccion() {
    for (int i = 0; i < this.contenidos.length; i++) {
      this.contenidos[i].Play();
    }
  }

  public void concatenarColecciones(Coleccion coleccion1, Coleccion coleccion2) {
    if (coleccion1.tipo == coleccion2.tipo) {
      Contenido[] contenidosNuevos = new Contenido[coleccion1.contenidos.length + coleccion2.contenidos.length];

      for (int i = 0; i < coleccion1.contenidos.length; i++) {
        contenidosNuevos[i] = coleccion1.contenidos[i];
      }

      for (int i = 0; i < coleccion2.contenidos.length; i++) {
        contenidosNuevos[i + coleccion1.contenidos.length] = coleccion2.contenidos[i];
      }

      this.contenidos = contenidosNuevos;
    }

  }
}
