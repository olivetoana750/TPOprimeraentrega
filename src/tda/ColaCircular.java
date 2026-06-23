package tda;

import model.Pedido;

public class ColaCircular {
    static final int MAX = 20;
    Pedido[] datos = new Pedido[MAX];
    int frente = -1;
    int fin = -1;
    int cantidad = 0;

    // Inicializar la cola
    public void inicializarCola() {
        frente = -1;
        fin = -1;
        cantidad = 0;
    }

    // Verificar si la cola esta vacia
    public boolean estaVacia() {
        return cantidad == 0;
    }

    // Verificar si la cola esta llena
    public boolean estaLlena() {
        return cantidad == MAX;
    }

    // Encolar un pedido
    public boolean encolar(Pedido pedido) {
        if (estaLlena()) {
            System.out.println("Error: la cola de expedicion esta llena.");
            return false;
        } else {
            fin = (fin + 1) % MAX;
            datos[fin] = pedido;
            cantidad++;
            if (frente == -1) {
                frente = 0;
            }
            return true;
        }
    }

    // Desencolar el primer pedido
    public Pedido desencolar() {
        if (estaVacia()) {
            System.out.println("Error: no hay pedidos en la cola de expedicion.");
            return null;
        } else {
            Pedido eliminado = datos[frente];
            if (frente == fin) {
                // Era el ultimo elemento, reiniciar la cola
                frente = -1;
                fin = -1;
                cantidad = 0;
            } else {
                frente = (frente + 1) % MAX;
                cantidad--;
            }
            return eliminado;
        }
    }

    // Ver el primer pedido sin eliminarlo
    public Pedido recuperarFrente() {
        if (estaVacia()) {
            System.out.println("Error: no hay pedidos en la cola de expedicion.");
            return null;
        } else {
            return datos[frente];
        }
    }

    // Obtener cantidad de pedidos en cola
    public int tamanio() {
        return cantidad;
    }

    // Verificar si un pedido con ese numero ya esta en la cola
    public boolean contienePedido(String numeroPedido) {
        if (estaVacia()) return false;
        int i = frente;
        int contador = 0;
        while (contador < cantidad) {
            if (datos[i].getNumeroPedido().equalsIgnoreCase(numeroPedido)) {
                return true;
            }
            i = (i + 1) % MAX;
            contador++;
        }
        return false;
    }

    // Mostrar todos los pedidos en cola
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay pedidos en la cola de expedicion.");
        } else {
            System.out.println("=== COLA DE EXPEDICION (" + cantidad + " pedidos en espera) ===");
            int i = frente;
            int contador = 1;
            while (contador <= cantidad) {
                System.out.println("  " + contador + ". " + datos[i]);
                i = (i + 1) % MAX;
                contador++;
            }
        }
    }
}
