/*
[1 pto] Se tiene list (de tipo Nodo), una referencia a una lista dinámica que contiene números enteros en su
campo dato. Crear un método Java que elimine el elemento que ocupa la posición pos en list (el primer nodo
de la lista ocupa la posición 1). Si la lista tiene menos de pos elementos, el método no debe modificarla.

Supóngase dada la siguiente definición:

public class Nodo {
  int dato;
  Nodo sgte;
  // constructores, etc.
}
*/

public class Ejercicio5 {

  // Esto es solo una definición de la clase Nodo, no es parte de la solución, según el enunciado se supone definida
  public static class Nodo {
    int dato;
    Nodo sgte;

    public Nodo(int dato) {
      this.dato = dato;
    }
  }
  // Fin de la definición de la clase Nodo

  public static Nodo eliminarElemento(Nodo list, int pos) {
    // PRE: list es distinto de null y pos es mayor que 0 y un numero dentro del rango de la lista
    if (pos == 1) {
      // En caso de querer el primer nodo de la lista enlazada, simplemente list pasa a ser el segundo nodo, manteniendo el resto de referencias siguientes
      list = list.sgte;
    } else {
      Nodo aux = list; // Nodo auxiliar para recorrer la lista
      int contador = 1;

      // Recorremos la lista hasta llegar al nodo anterior al que queremos eliminar (pos - 1) o hasta que lleguemos al final de la lista
      while (aux.sgte != null && contador < pos - 1) {
        aux = aux.sgte;
        contador++;
      }
      if (aux.sgte != null) {
        // Si el nodo siguiente al nodo auxiliar no es nulo, entonces el nodo siguiente al nodo siguiente del nodo auxiliar pasa a ser el nodo siguiente del nodo auxiliar
        aux.sgte = aux.sgte.sgte;
      }
    }

    return list;
    
  }
}
