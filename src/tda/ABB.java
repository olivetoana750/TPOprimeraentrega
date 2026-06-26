package tda;

import model.Producto;

public class ABB {

    private NodoABB raiz;
    private int cant;

    public ABB() {
        raiz = null;
        cant = 0;
    }
    // INSERTAR
    public void insertar(Producto producto) {
        if (raiz == null) {
            raiz = new NodoABB(producto);
            cant++;
        } else {
            NodoABB aux = raiz;
            boolean seguir = true;
            while (seguir) {
                int comparacion = producto.getCodigoUniversal()
                        .compareToIgnoreCase(aux.dato.getCodigoUniversal());
                if (comparacion < 0) {
                    if (aux.izq != null) {
                        aux = aux.izq;
                    } else {
                        aux.izq = new NodoABB(producto, aux);
                        cant++;
                        seguir = false;
                    }
                } else if (comparacion > 0) {
                    if (aux.der != null) {
                        aux = aux.der;
                    } else {
                        aux.der = new NodoABB(producto, aux);
                        cant++;
                        seguir = false;
                    }
                } else {
                    System.out.println("Error: ya existe un producto con el codigo "
                            + producto.getCodigoUniversal() + ".");
                    seguir = false;
                }
            }
        }
    }

    // BUSCAR
    public Producto buscar(String codigo) {
        NodoABB aux = raiz;
        while (aux != null) {
            int comparacion = codigo.compareToIgnoreCase(aux.dato.getCodigoUniversal());
            if (comparacion == 0) {
                return aux.dato;
            } else if (comparacion < 0) {
                aux = aux.izq;
            } else {
                aux = aux.der;
            }
        }
        return null;
    }
    // ELIMINAR
    public boolean eliminar(String codigo) {
        NodoABB nodo = buscarNodo(codigo);
        if (nodo == null) {
            System.out.println("Error: no existe un producto con el codigo " + codigo + ".");
            return false;
        }

        eliminarNodo(nodo);
        cant--;
        return true;
    }

    private NodoABB buscarNodo(String codigo) {
        NodoABB aux = raiz;
        while (aux != null) {
            int comparacion = codigo.compareToIgnoreCase(aux.dato.getCodigoUniversal());
            if (comparacion == 0) return aux;
            else if (comparacion < 0) aux = aux.izq;
            else aux = aux.der;
        }
        return null;
    }

    private void eliminarNodo(NodoABB nodo) {
        // Caso 1: nodo hoja
        if (nodo.izq == null && nodo.der == null) {
            reemplazarEnPadre(nodo, null);
        }
        // Caso 2: tiene solo hijo derecho
        else if (nodo.izq == null) {
            reemplazarEnPadre(nodo, nodo.der);
        }
        // Caso 3: tiene solo hijo izquierdo
        else if (nodo.der == null) {
            reemplazarEnPadre(nodo, nodo.izq);
        }
        // Caso 4: tiene dos hijos → reemplazar con sucesor inorden
        else {
            NodoABB sucesor = minimoDeSubarbol(nodo.der);
            nodo.dato = sucesor.dato;
            eliminarNodo(sucesor);
            cant++; // se decrementa dos veces si no compensamos
        }
    }

    private void reemplazarEnPadre(NodoABB nodo, NodoABB reemplazo) {
        if (nodo.padre == null) {
            raiz = reemplazo;
        } else if (nodo.padre.izq == nodo) {
            nodo.padre.izq = reemplazo;
        } else {
            nodo.padre.der = reemplazo;
        }
        if (reemplazo != null) {
            reemplazo.padre = nodo.padre;
        }
    }

    private NodoABB minimoDeSubarbol(NodoABB nodo) {
        while (nodo.izq != null) {
            nodo = nodo.izq;
        }
        return nodo;
    }

    // RECORRIDOS
    public void mostrarInorden() {
        if (raiz == null) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("=== INVENTARIO ORDENADO POR CODIGO ===");
        inorden(raiz);
        System.out.println();
    }

    private void inorden(NodoABB nodo) {
        if (nodo == null) return;
        inorden(nodo.izq);
        System.out.println("  " + nodo.dato);
        inorden(nodo.der);
    }

    public void mostrarPreorden() {
        if (raiz == null) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("=== PREORDEN ===");
        preorden(raiz);
        System.out.println();
    }

    private void preorden(NodoABB nodo) {
        if (nodo == null) return;
        System.out.println("  " + nodo.dato);
        preorden(nodo.izq);
        preorden(nodo.der);
    }

    public void mostrarPostorden() {
        if (raiz == null) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        System.out.println("=== POSTORDEN ===");
        postorden(raiz);
        System.out.println();
    }

    private void postorden(NodoABB nodo) {
        if (nodo == null) return;
        postorden(nodo.izq);
        postorden(nodo.der);
        System.out.println("  " + nodo.dato);
    }

    // OTRAS FUNCONES
    public int contarNodos() {
        return cant;
    }

    public boolean estaVacio() {
        return raiz == null;
    }
}