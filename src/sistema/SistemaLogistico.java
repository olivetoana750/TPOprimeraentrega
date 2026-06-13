package sistema;

import modelos.MovimientoInventario;
import modelos.Pedido;
import modelos.Producto;
import tda.ColaCircular;
import tda.Pila;

public class SistemaLogistico {

    private Pila historialMovimientos;
    private ColaCircular colaExpedicion;

    // Usamos un arreglo simple para guardar los productos registrados
    // (el ABB va en la proxima entrega, por ahora alcanza para demostrar)
    private Producto[] productos;
    private int cantProductos;
    static final int MAX_PRODUCTOS = 50;

    public SistemaLogistico() {
        historialMovimientos = new Pila();
        historialMovimientos.crearPila();
        colaExpedicion = new ColaCircular();
        colaExpedicion.inicializarCola();
        productos = new Producto[MAX_PRODUCTOS];
        cantProductos = 0;
    }

    // =============================================
    // GESTION DE PRODUCTOS
    // =============================================

    public boolean agregarProducto(String codigo, String nombre, String ubicacion, int stockInicial) {
        if (buscarProducto(codigo) != null) {
            System.out.println("Error: ya existe un producto con el codigo " + codigo + ".");
            return false;
        }
        if (cantProductos >= MAX_PRODUCTOS) {
            System.out.println("Error: capacidad maxima de productos alcanzada.");
            return false;
        }
        productos[cantProductos] = new Producto(codigo, nombre, ubicacion, stockInicial);
        cantProductos++;
        System.out.println("Producto agregado correctamente: " + nombre + " (" + codigo + ")");
        return true;
    }

    public Producto buscarProducto(String codigo) {
        for (int i = 0; i < cantProductos; i++) {
            if (productos[i].getCodigoUniversal().equalsIgnoreCase(codigo)) {
                return productos[i];
            }
        }
        return null;
    }

    public void mostrarProductos() {
        if (cantProductos == 0) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("=== INVENTARIO DE PRODUCTOS ===");
        for (int i = 0; i < cantProductos; i++) {
            System.out.println("  " + (i + 1) + ". " + productos[i]);
        }
    }

    // =============================================
    // TRAZABILIDAD DE LOTES - PILA LIFO
    // =============================================

    public void registrarIngreso(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            System.out.println("Error: no existe un producto con el codigo " + codigo + ".");
            return;
        }
        if (cantidad <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a 0.");
            return;
        }

        int stockAntes = p.getStockActual();
        p.setStockActual(stockAntes + cantidad);

        MovimientoInventario movimiento = new MovimientoInventario(
                "INGRESO",
                p.getCodigoUniversal(),
                p.getNombre(),
                stockAntes,
                p.getStockActual(),
                "Ingreso de " + cantidad + " unidades"
        );
        historialMovimientos.apilar(movimiento);

        System.out.println("Ingreso registrado: " + cantidad + " unidades de " + p.getNombre()
                + ". Stock actual: " + p.getStockActual());
    }

    public void registrarEgreso(String codigo, int cantidad) {
        Producto p = buscarProducto(codigo);
        if (p == null) {
            System.out.println("Error: no existe un producto con el codigo " + codigo + ".");
            return;
        }
        if (cantidad <= 0) {
            System.out.println("Error: la cantidad debe ser mayor a 0.");
            return;
        }
        if (p.getStockActual() < cantidad) {
            System.out.println("Error: stock insuficiente. Stock actual: " + p.getStockActual()
                    + ", cantidad solicitada: " + cantidad + ".");
            return;
        }

        int stockAntes = p.getStockActual();
        p.setStockActual(stockAntes - cantidad);

        MovimientoInventario movimiento = new MovimientoInventario(
                "EGRESO",
                p.getCodigoUniversal(),
                p.getNombre(),
                stockAntes,
                p.getStockActual(),
                "Egreso de " + cantidad + " unidades"
        );
        historialMovimientos.apilar(movimiento);

        System.out.println("Egreso registrado: " + cantidad + " unidades de " + p.getNombre()
                + ". Stock actual: " + p.getStockActual());
    }

    public void deshacerUltimoMovimiento() {
        if (historialMovimientos.estaVacia()) {
            System.out.println("No hay movimientos para deshacer.");
            return;
        }

        MovimientoInventario ultimo = historialMovimientos.desapilar();
        Producto p = buscarProducto(ultimo.getCodigoProducto());

        if (p == null) {
            System.out.println("Error: no se encontro el producto del movimiento a deshacer.");
            return;
        }

        p.setStockActual(ultimo.getStockAntes());
        System.out.println("Movimiento deshecho: " + ultimo.getDescripcion()
                + " sobre " + ultimo.getNombreProducto()
                + ". Stock restaurado a: " + ultimo.getStockAntes() + " unidades.");
    }

    public void mostrarHistorial() {
        historialMovimientos.mostrar();
    }

    // =============================================
    // LINEA DE EXPEDICION - COLA CIRCULAR FIFO
    // =============================================

    public void agregarPedido(String numero, String destinatario, String destino, int bultos) {
        if (buscarPedidoEnCola(numero)) {
            System.out.println("Error: ya existe un pedido con el numero " + numero + " en la cola.");
            return;
        }
        Pedido pedido = new Pedido(numero, destinatario, destino, bultos);
        boolean encolado = colaExpedicion.encolar(pedido);
        if (encolado) {
            System.out.println("Pedido agregado a la cola de expedicion: " + pedido);
        }
    }

    public void despacharPedido() {
        Pedido pedido = colaExpedicion.desencolar();
        if (pedido != null) {
            System.out.println("Pedido despachado: " + pedido);
            System.out.println("Pedidos restantes en cola: " + colaExpedicion.tamanio());
        }
    }

    public void mostrarColaPedidos() {
        colaExpedicion.mostrar();
    }

    public Pedido verProximoPedido() {
        return colaExpedicion.recuperarFrente();
    }

    // Metodo auxiliar para evitar duplicados en la cola
    private boolean buscarPedidoEnCola(String numero) {
        return colaExpedicion.contienePedido(numero);
    }
}
