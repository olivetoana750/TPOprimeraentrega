package tda;

public class Grafo {

    private NodoPasillo primero;
    private int cantidadPasillos;
    static final int INFINITO = Integer.MAX_VALUE / 2;

    public Grafo() {
        this.primero = null;
        this.cantidadPasillos = 0;
    }

    // =============================================
    // GESTION DE PASILLOS (VERTICES)
    // =============================================

    public void agregarPasillo(String nombre) {
        if (buscarNodoPasillo(nombre) != null) {
            System.out.println("Error: el pasillo " + nombre + " ya existe.");
            return;
        }
        NodoPasillo nuevo = new NodoPasillo(nombre);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoPasillo aux = primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        cantidadPasillos++;
        System.out.println("Pasillo agregado: " + nombre);
    }

    public boolean existePasillo(String nombre) {
        return buscarNodoPasillo(nombre) != null;
    }

    private NodoPasillo buscarNodoPasillo(String nombre) {
        NodoPasillo aux = primero;
        while (aux != null) {
            if (aux.nombre.equalsIgnoreCase(nombre)) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    // =============================================
    // GESTION DE CONEXIONES (ARISTAS CON PESO)
    // =============================================

    public void agregarConexion(String origen, String destino, int distancia) {
        NodoPasillo nodoOrigen = buscarNodoPasillo(origen);
        NodoPasillo nodoDestino = buscarNodoPasillo(destino);

        if (nodoOrigen == null) {
            System.out.println("Error: el pasillo " + origen + " no existe.");
            return;
        }
        if (nodoDestino == null) {
            System.out.println("Error: el pasillo " + destino + " no existe.");
            return;
        }
        if (distancia <= 0) {
            System.out.println("Error: la distancia debe ser mayor a 0.");
            return;
        }

        // Grafo no dirigido: se agrega en ambas direcciones
        agregarAdyacente(nodoOrigen, destino, distancia);
        agregarAdyacente(nodoDestino, origen, distancia);
        System.out.println("Conexion agregada: " + origen + " <-> " + destino
                + " (" + distancia + " metros)");
    }

    private void agregarAdyacente(NodoPasillo nodo, String destino, int distancia) {
        NodoAdyacente nuevo = new NodoAdyacente(destino, distancia);
        if (nodo.adyacentes == null) {
            nodo.adyacentes = nuevo;
        } else {
            NodoAdyacente aux = nodo.adyacentes;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    // =============================================
    // MOSTRAR ALMACEN
    // =============================================

    public void mostrarAlmacen() {
        if (primero == null) {
            System.out.println("El almacen no tiene pasillos registrados.");
            return;
        }
        System.out.println("=== MAPA DEL ALMACEN ===");
        NodoPasillo aux = primero;
        while (aux != null) {
            System.out.print("  " + aux.nombre + " -> ");
            NodoAdyacente ady = aux.adyacentes;
            if (ady == null) {
                System.out.print("(sin conexiones)");
            }
            while (ady != null) {
                System.out.print(ady.destino + "(" + ady.distancia + "m)");
                if (ady.siguiente != null) System.out.print(", ");
                ady = ady.siguiente;
            }
            System.out.println();
            aux = aux.siguiente;
        }
    }

    // =============================================
    // DIJKSTRA - RUTA OPTIMA
    // =============================================

    public void calcularRuta(String origen, String destino) {
        if (buscarNodoPasillo(origen) == null) {
            System.out.println("Error: el pasillo de origen " + origen + " no existe.");
            return;
        }
        if (buscarNodoPasillo(destino) == null) {
            System.out.println("Error: el pasillo de destino " + destino + " no existe.");
            return;
        }
        if (origen.equalsIgnoreCase(destino)) {
            System.out.println("Ya estas en el pasillo " + origen + ". Distancia: 0 metros.");
            return;
        }

        // Armar arreglos con los nombres de los pasillos
        String[] nombres = new String[cantidadPasillos];
        int[] distancias = new int[cantidadPasillos];
        boolean[] visitado = new boolean[cantidadPasillos];
        int[] anterior = new int[cantidadPasillos];

        NodoPasillo aux = primero;
        int idx = 0;
        while (aux != null) {
            nombres[idx] = aux.nombre;
            distancias[idx] = INFINITO;
            visitado[idx] = false;
            anterior[idx] = -1;
            idx++;
            aux = aux.siguiente;
        }

        // Indice del origen
        int idxOrigen = obtenerIndice(nombres, origen);
        distancias[idxOrigen] = 0;

        // Algoritmo de Dijkstra
        for (int i = 0; i < cantidadPasillos; i++) {
            // Encontrar el nodo no visitado con menor distancia
            int u = -1;
            for (int j = 0; j < cantidadPasillos; j++) {
                if (!visitado[j] && (u == -1 || distancias[j] < distancias[u])) {
                    u = j;
                }
            }

            if (distancias[u] == INFINITO) break;
            visitado[u] = true;

            // Actualizar distancias de los vecinos
            NodoPasillo nodoPasillo = buscarNodoPasillo(nombres[u]);
            NodoAdyacente ady = nodoPasillo.adyacentes;
            while (ady != null) {
                int v = obtenerIndice(nombres, ady.destino);
                if (v != -1 && !visitado[v]) {
                    int nuevaDist = distancias[u] + ady.distancia;
                    if (nuevaDist < distancias[v]) {
                        distancias[v] = nuevaDist;
                        anterior[v] = u;
                    }
                }
                ady = ady.siguiente;
            }
        }

        // Reconstruir la ruta
        int idxDestino = obtenerIndice(nombres, destino);
        if (distancias[idxDestino] == INFINITO) {
            System.out.println("No existe ruta entre " + origen + " y " + destino + ".");
            return;
        }

        // Armar el camino recorriendo los anteriores
        String[] camino = new String[cantidadPasillos];
        int tamCamino = 0;
        int actual = idxDestino;
        while (actual != -1) {
            camino[tamCamino] = nombres[actual];
            tamCamino++;
            actual = anterior[actual];
        }

        // Mostrar resultado
        System.out.println("=== RUTA OPTIMA ===");
        System.out.print("  Recorrido: ");
        for (int i = tamCamino - 1; i >= 0; i--) {
            System.out.print(camino[i]);
            if (i > 0) System.out.print(" -> ");
        }
        System.out.println();
        System.out.println("  Distancia total: " + distancias[idxDestino] + " metros.");
    }

    private int obtenerIndice(String[] nombres, String nombre) {
        for (int i = 0; i < cantidadPasillos; i++) {
            if (nombres[i] != null && nombres[i].equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public boolean estaVacio() {
        return primero == null;
    }

    public int getCantidadPasillos() {
        return cantidadPasillos;
    }
}