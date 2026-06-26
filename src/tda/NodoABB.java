package tda;

import model.Producto;

public class NodoABB {
    Producto dato;
    NodoABB izq, der, padre;

    //constructor 1
    public NodoABB(Producto dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
        this.padre = null;
    }
    //constructor 2
    public NodoABB(Producto dato, NodoABB padre) {
        this.dato = dato;
        this.padre = padre;
        this.izq = null;
        this.der = null;
    }
}