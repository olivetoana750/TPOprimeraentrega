import modelos.Producto;
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
                modelos.Pedido proximo = sistema.verProximoPedido();
                if (proximo != null) {
                    System.out.println("Proximo pedido a despachar: " + proximo);
                }
                break;

            case 0:
                System.out.println("Saliendo del sistema. Hasta luego.");
                break;

            default:
                System.out.println("Opcion invalida. Ingrese un numero del 0 al 11.");
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
        sistema.agregarPedido("PED-001", "Supermercado Garcia", "Rosario, Santa Fe", 12);
        sistema.agregarPedido("PED-002", "Distribuidora Lopez", "Cordoba Capital", 8);
        System.out.println("Datos cargados. Sistema listo.\n");
    }
}
