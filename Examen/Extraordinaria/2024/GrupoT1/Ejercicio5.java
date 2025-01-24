/*
[1 pto] Crear un método Java que, dada una lista de tipo Nodo, le añada como último nodo uno que contenga 
como dato la suma de todos los valores de la lista que son múltiplos del primero. Por ejemplo, la lista 2,3,6 debe 
convertirse en la lista 2,3,6,8. Supóngase dada la siguiente definición:
*/

public class Ejercicio5 {

  // Esto es solo una definición de la clase Nodo, no es parte de la solución,
  // según el enunciado se supone definida
  public static class Nodo {
    int dato;
    Nodo sgte;

    public Nodo(int dato) {
      this.dato = dato;
    }
  }

  public static Nodo anadirElemento(Nodo list, int pos) {
    int datoInicial = list.dato;
    int dato = 0;
    Nodo nodoAuxiliar = list;

    while (nodoAuxiliar.sgte != null) {
      if (nodoAuxiliar.dato % datoInicial == 0) {
        dato += nodoAuxiliar.dato;
      }
      nodoAuxiliar = nodoAuxiliar.sgte;
    }
    
    nodoAuxiliar = new Nodo(dato);

    return list;
  }
}