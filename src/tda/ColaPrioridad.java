package tda;

import model.Producto;

public class ColaPrioridad {

    public class Elemento {
        public Producto producto;
        public int prioridad;

        public Elemento(Producto producto, int prioridad) {
            this.producto = producto;
            this.prioridad = prioridad;
        }
    }

    static final int MAX = 50;
    Elemento[] cola = new Elemento[MAX];
    int cantidad = 0;

    public void crear() {
        this.cola = new Elemento[MAX];
        this.cantidad = 0;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public boolean estaLlena() {
        return cantidad == MAX;
    }

    public boolean insertar(Producto producto, int prioridad) {
        if (estaLlena()) {
            System.out.println("Error: cola de prioridad llena.");
            return false;
        }
        Elemento nuevo = new Elemento(producto, prioridad);
        int i = cantidad - 1;
        while (i >= 0 && cola[i].prioridad < prioridad) {
            cola[i + 1] = cola[i];
            i--;
        }
        cola[i + 1] = nuevo;
        cantidad++;
        return true;
    }

    public Elemento eliminar() {
        if (estaVacia()) {
            return null;
        }
        Elemento eliminado = cola[0];
        for (int i = 0; i < cantidad - 1; i++) {
            cola[i] = cola[i + 1];
        }
        cantidad--;
        return eliminado;
    }

    public Elemento frente() {
        if (estaVacia()) {
            return null;
        }
        return cola[0];
    }

    public boolean contieneProducto(String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (cola[i].producto.getCodigoUniversal().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay productos en estado critico.");
        } else {
            System.out.println("=== INVENTARIO CRITICO (ordenado por urgencia) ===");
            for (int i = 0; i < cantidad; i++) {
                System.out.println("  " + (i + 1) + ". [Prioridad: " + cola[i].prioridad + "] "
                        + cola[i].producto.getNombre()
                        + " (" + cola[i].producto.getCodigoUniversal() + ")"
                        + " | Stock actual: " + cola[i].producto.getStockActual() + " unidades");
            }
        }
    }

    public int tamanio() {
        return cantidad;
    }
}