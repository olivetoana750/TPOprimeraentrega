package tda;
//cada lugar, vertice
public class NodoPasillo {
    String nombre;
    NodoPasillo siguiente;
    NodoAdyacente adyacentes;

    public NodoPasillo(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
        this.adyacentes = null;
    }
}