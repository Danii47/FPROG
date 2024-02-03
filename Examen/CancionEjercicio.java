public class CancionEjercicio {
  
  public static void main(String[] args) {
    Cancion cancion1 = new Cancion("Quevedo: Bzrp Music Sessions, Vol. 52", "Bizarrap & Quevedo");
  }

}

class Cancion {
  private String titulo;
  private String autor;

  public Cancion(String titulo, String autor) {
    this.titulo = titulo;
    this.autor = autor;
  }

  public Cancion() {
    this.titulo = "";
    this.autor = "";
  }

  public String getTitulo() {
    return this.titulo;
  }

  public String getAutor() {
    return this.autor;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }
}

class CD {
  private Cancion[] canciones;
  private int contador = canciones.length;

  public CD() {
    this.canciones = new Cancion[0];
  }

  public int numeroCanciones() {
    return this.contador;
  }

  public Cancion getCancion(int posicion) {
    return this.canciones[posicion];
  }

  public void grabaCancion(int posicion, Cancion cancionNueva) {
    this.canciones[posicion] = cancionNueva;
  }

  public void agrega(Cancion cancionNueva) {
    Cancion[] cancionesNuevas = new Cancion[this.contador + 1];

    for (int i = 0; i < this.contador; i++) {
      cancionesNuevas[i] = this.canciones[i];
    }

    cancionesNuevas[this.contador] = cancionNueva;

    this.canciones = cancionesNuevas;
    this.contador = this.canciones.length;
  }

  public void elimina(int posicion) {
    Cancion[] cancionesNuevas = new Cancion[this.contador - 1];
    for (int i = 0; i < posicion; i++) {
      cancionesNuevas[i] = this.canciones[i];
    }
    for (int i = posicion; i < cancionesNuevas.length; i++) {
      cancionesNuevas[i] = this.canciones[i + 1];
    }

    this.canciones = cancionesNuevas;
    this.contador = this.canciones.length;
  }
}