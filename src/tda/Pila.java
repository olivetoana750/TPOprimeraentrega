package tda;

import modelos.MovimientoInventario;

public class Pila {
    static final int MAX = 50;
    MovimientoInventario[] datos = new MovimientoInventario[MAX];
    int tope = -1;

    // Inicializar la pila
    public void crearPila() {
        tope = -1;
    }

    // Apilar un movimiento
    public void apilar(MovimientoInventario movimiento) {
        if (estaLlena()) {
            System.out.println("Error: pila llena, no se puede registrar el movimiento.");
        } else {
            tope++;
            datos[tope] = movimiento;
        }
    }

    // Desapilar el movimiento del tope
    public MovimientoInventario desapilar() {
        if (estaVacia()) {
            System.out.println("Error: no hay movimientos registrados.");
            return null;
        } else {
            MovimientoInventario x = datos[tope];
            tope--;
            return x;
        }
    }

    // Ver el tope sin eliminarlo
    public MovimientoInventario tope() {
        if (estaVacia()) {
            System.out.println("Error: no hay movimientos registrados.");
            return null;
        } else {
            return datos[tope];
        }
    }

    // Verificar si la pila esta vacia
    public boolean estaVacia() {
        return tope == -1;
    }

    // Verificar si la pila esta llena
    public boolean estaLlena() {
        return tope == MAX - 1;
    }

    // Obtener cantidad de movimientos registrados
    public int tamanio() {
        return tope + 1;
    }

    // Mostrar todos los movimientos (del mas reciente al mas antiguo)
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay movimientos registrados en el historial.");
        } else {
            System.out.println("=== HISTORIAL DE MOVIMIENTOS (del mas reciente al mas antiguo) ===");
            for (int i = tope; i >= 0; i--) {
                System.out.println("  " + (tope - i + 1) + ". " + datos[i]);
            }
        }
    }
}
