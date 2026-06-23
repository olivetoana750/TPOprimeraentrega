package tda;

import model.Producto;

public class NodoABB {
    Producto dato;
    NodoABB izq, der, padre;

    public NodoABB(Producto dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
        this.padre = null;
    }

    public NodoABB(Producto dato, NodoABB padre) {
        this.dato = dato;
        this.padre = padre;
        this.izq = null;
        this.der = null;
    }
}