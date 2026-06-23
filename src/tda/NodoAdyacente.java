package tda;

public class NodoAdyacente {
    String destino;
    int distancia;
    NodoAdyacente siguiente;

    public NodoAdyacente(String destino, int distancia) {
        this.destino = destino;
        this.distancia = distancia;
        this.siguiente = null;
    }
}