package Otros;
public class Constructores {
  public static void main(String[] args) {

    Persona daro = new Persona("Daniel", 18, true, 500);

    System.out.println(daro.getNombre());
    System.out.println(daro.getEdad());
    System.out.println(daro.getSexo());
    System.out.println(daro.getDinero());
    System.out.println(daro.toString());

    daro.setDinero(300);
    System.out.println(daro.getDinero());
    
  }
  
}

class Persona {
  private String nombre;
  private int edad;
  private boolean sexo;
  private int dinero;

  public Persona(String nombre, int edad, boolean sexo, int dinero) {

    this.nombre = nombre;
    this.edad = edad;
    this.sexo = sexo;
    this.dinero = dinero;

  }

  public String getNombre() {
    return this.nombre;
  }

  public int getEdad() {
    return this.edad;
  }

  public boolean getSexo() {
    return this.sexo;
  }

  public int getDinero() {
    return this.dinero;
  }

  public void setDinero(int dinero) {
    this.dinero = dinero;
  }

  public String toString() {
    return "Nombre: " + this.nombre + "\nEdad: " + this.edad + "\nSexo: " + (this.sexo ? "Sí" : "No") + "\nDinero: " + this.dinero;
  }
}
