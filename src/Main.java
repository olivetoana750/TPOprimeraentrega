import model.Producto;
import sistema.SistemaLogistico;

import java.util.Scanner;

public class Main {

    static SistemaLogistico sistema = new SistemaLogistico();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Cargamos algunos productos de ejemplo para facilitar las pruebas
        cargarDatosEjemplo();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese una opcion: ");
            System.out.println();
            procesarOpcion(opcion);
            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    static void mostrarMenu() {
        System.out.println("========================================");
        System.out.println("     SISTEMA LOGISTICO DE DISTRIBUCION ");
        System.out.println("========================================");
        System.out.println("--- INVENTARIO ---");
        System.out.println("  1. Ver productos registrados");
        System.out.println("  2. Agregar nuevo producto");
        System.out.println("  3. Buscar producto por codigo");
        System.out.println("--- TRAZABILIDAD DE LOTES (PILA) ---");
        System.out.println("  4. Registrar ingreso de stock");
        System.out.println("  5. Registrar egreso de stock");
        System.out.println("  6. Deshacer ultimo movimiento");
        System.out.println("  7. Ver historial de movimientos");
        System.out.println("--- LINEA DE EXPEDICION (COLA CIRCULAR) ---");
        System.out.println("  8. Agregar pedido a expedicion");
        System.out.println("  9. Despachar siguiente pedido");
        System.out.println(" 10. Ver cola de pedidos");
        System.out.println(" 11. Ver proximo pedido a despachar");
        System.out.println("--- INVENTARIO CRITICO (COLA CON PRIORIDAD) ---");
        System.out.println(" 12. Ver productos criticos");
        System.out.println(" 13. Atender producto mas urgente");
        System.out.println(" 14. Ver proximo producto critico");
        System.out.println(" 15. Marcar producto como critico manualmente");
        System.out.println("--- OPTIMIZACION DE RECOLECCION (GRAFO + DIJKSTRA) ---");
        System.out.println(" 16. Ver mapa del almacen");
        System.out.println(" 17. Calcular ruta optima entre pasillos");
        System.out.println(" 18. Calcular ruta hacia un producto");
        System.out.println("----------------------------------------");
        System.out.println("  0. Salir");
        System.out.println("========================================");
    }

    static void procesarOpcion(int opcion) {
        switch (opcion) {

            case 1:
                sistema.mostrarProductos();
                break;

            case 2:
                System.out.print("Codigo universal: ");
                String codigo = scanner.nextLine().trim();
                System.out.print("Nombre del producto: ");
                String nombre = scanner.nextLine().trim();
                System.out.print("Ubicacion (pasillo): ");
                String ubicacion = scanner.nextLine().trim();
                int stockInicial = leerEntero("Stock inicial: ");
                sistema.agregarProducto(codigo, nombre, ubicacion, stockInicial);
                break;

            case 3:
                System.out.print("Ingrese el codigo del producto: ");
                String cod = scanner.nextLine().trim();
                Producto p = sistema.buscarProducto(cod);
                if (p != null) {
                    System.out.println("Producto encontrado: " + p);
                } else {
                    System.out.println("No se encontro ningun producto con el codigo " + cod + ".");
                }
                break;

            case 4:
                System.out.print("Codigo del producto: ");
                String codIngreso = scanner.nextLine().trim();
                int cantIngreso = leerEntero("Cantidad a ingresar: ");
                sistema.registrarIngreso(codIngreso, cantIngreso);
                break;

            case 5:
                System.out.print("Codigo del producto: ");
                String codEgreso = scanner.nextLine().trim();
                int cantEgreso = leerEntero("Cantidad a egresar: ");
                sistema.registrarEgreso(codEgreso, cantEgreso);
                break;

            case 6:
                sistema.deshacerUltimoMovimiento();
                break;

            case 7:
                sistema.mostrarHistorial();
                break;

            case 8:
                System.out.print("Numero de pedido: ");
                String numPedido = scanner.nextLine().trim();
                System.out.print("Destinatario: ");
                String destinatario = scanner.nextLine().trim();
                System.out.print("Destino: ");
                String destino = scanner.nextLine().trim();
                int bultos = leerEntero("Cantidad de bultos: ");
                sistema.agregarPedido(numPedido, destinatario, destino, bultos);
                break;

            case 9:
                sistema.despacharPedido();
                break;

            case 10:
                sistema.mostrarColaPedidos();
                break;

            case 11:
                model.Pedido proximo = sistema.verProximoPedido();
                if (proximo != null) {
                    System.out.println("Proximo pedido a despachar: " + proximo);
                }
                break;

            case 12:
                sistema.mostrarInventarioCritico();
                break;

            case 13:
                sistema.atenderProductoCritico();
                break;

            case 14:
                sistema.verProximoCritico();
                break;

            case 15:
                System.out.print("Codigo del producto a marcar como critico: ");
                String codCritico = scanner.nextLine().trim();
                sistema.marcarProductoCritico(codCritico);
                break;

            case 16:
                sistema.mostrarMapa();
                break;

            case 17:
                System.out.print("Pasillo de origen: ");
                String pasilloOrigen = scanner.nextLine().trim();
                System.out.print("Pasillo de destino: ");
                String pasilloDestino = scanner.nextLine().trim();
                sistema.calcularRutaOptima(pasilloOrigen, pasilloDestino);
                break;

            case 18:
                System.out.print("Codigo del producto a recolectar: ");
                String codProducto = scanner.nextLine().trim();
                System.out.print("Pasillo donde estas ahora: ");
                String pasilloActual = scanner.nextLine().trim();
                sistema.calcularRutaHaciaProducto(codProducto, pasilloActual);
                break;

            case 0:
                System.out.println("Saliendo del sistema. Hasta luego.");
                break;

            default:
                System.out.println("Opcion invalida. Ingrese un numero del 0 al 18.");
        }
    }

    // Lee un entero y valida que no sea texto
    static int leerEntero(String mensaje) {
        int valor = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                valor = Integer.parseInt(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un numero valido.");
            }
        }
        return valor;
    }

    // Datos de ejemplo para que el sistema arranque con algo cargado
    static void cargarDatosEjemplo() {
        System.out.println("Cargando datos de ejemplo...");
        sistema.agregarProducto("PROD-001", "Aceite de girasol 1L", "Pasillo-A", 200);
        sistema.agregarProducto("PROD-002", "Arroz largo fino 1kg", "Pasillo-B", 150);
        sistema.agregarProducto("PROD-003", "Leche entera 1L", "Pasillo-C", 300);
        sistema.agregarProducto("PROD-004", "Azucar 1kg", "Pasillo-A", 12);
        sistema.agregarProducto("PROD-005", "Harina 000 1kg", "Pasillo-B", 35);
        sistema.marcarProductoCritico("PROD-004");
        sistema.marcarProductoCritico("PROD-005");
        sistema.agregarPedido("PED-001", "Supermercado Garcia", "Rosario, Santa Fe", 12);
        sistema.agregarPedido("PED-002", "Distribuidora Lopez", "Cordoba Capital", 8);

        // Mapa del almacen: pasillos y conexiones con distancias en metros
        sistema.agregarPasillo("Pasillo-A");
        sistema.agregarPasillo("Pasillo-B");
        sistema.agregarPasillo("Pasillo-C");
        sistema.agregarPasillo("Pasillo-D");
        sistema.agregarPasillo("Entrada");
        sistema.agregarConexion("Entrada", "Pasillo-A", 10);
        sistema.agregarConexion("Entrada", "Pasillo-B", 20);
        sistema.agregarConexion("Pasillo-A", "Pasillo-B", 15);
        sistema.agregarConexion("Pasillo-A", "Pasillo-C", 25);
        sistema.agregarConexion("Pasillo-B", "Pasillo-C", 10);
        sistema.agregarConexion("Pasillo-B", "Pasillo-D", 30);
        sistema.agregarConexion("Pasillo-C", "Pasillo-D", 15);
        System.out.println("Datos cargados. Sistema listo.\n");
    }
}