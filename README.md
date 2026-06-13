# Centro Logístico de Distribución Avanzada

## Nombre del proyecto
Sistema de Gestión Logística - Centro de Distribución Avanzada

## Integrantes del grupo
- Francesca Dascanio
- Ana Oliveto

## Alternativa elegida
**Alternativa C:** Centro Logístico de Distribución Avanzada

## Descripción
Sistema que permite gestionar el almacenamiento, stock y despacho de un centro de distribución logístico.
Cada funcionalidad está resuelta con la estructura de datos más adecuada para el problema.

---

## Estructuras de datos utilizadas

### Pila (LIFO) — Trazabilidad de Lotes
Cada movimiento de inventario (ingreso o egreso) se apila. Como es LIFO, el último movimiento
siempre está en el tope y puede revertirse en O(1) ante un error operativo.

### Cola Circular (FIFO) — Línea de Expedición
Los pedidos listos para despachar se encolan en orden de llegada. La variante circular reutiliza
el espacio liberado por pedidos ya despachados sin mover elementos en memoria.

### Cola con Prioridad — Control de Inventario Crítico
Los productos con stock bajo se insertan ordenados por urgencia. A menor stock,
mayor prioridad. El frente de la cola siempre tiene el producto más urgente para reponer.

---

## Funcionalidades implementadas en esta segunda etapa

### 1. Trazabilidad de Lotes (Pila LIFO)
- Registrar ingreso de stock a un producto existente
- Registrar egreso de stock con validación de stock suficiente
- Deshacer el último movimiento registrado, restaurando el stock anterior
- Ver el historial completo de movimientos (del más reciente al más antiguo)

### 2. Línea de Expedición (Cola Circular FIFO)
- Agregar pedidos a la cola de expedición
- Despachar el siguiente pedido (el primero en llegar)
- Ver todos los pedidos en espera
- Consultar el próximo pedido a despachar sin eliminarlo

### 3. Control de Inventario Crítico (Cola con Prioridad)
- Detectar automáticamente productos que caen por debajo del umbral de stock crítico (50 unidades)
- Ordenar productos críticos por urgencia: menor stock = mayor prioridad
- Atender el producto más urgente primero
- Ver el próximo producto crítico sin atenderlo
- Marcar productos como críticos manualmente

### Gestión de inventario base
- Agregar productos con código universal, nombre, ubicación y stock
- Buscar producto por código
- Ver todos los productos registrados

## Estructura del proyecto
<!-- src/
    modelos/
       Producto.java -->  entidad producto del depósito
       MovimientoInventario.java --> registro de cada operación de stock
        Pedido.java→ pedido listo para despachar
    tda/
        Pila.java→ TDA Pila adaptado para MovimientoInventario
        ColaCircular.java --> TDA Cola Circular adaptado para Pedido
        ColaPrioridad.java --> TDA Cola con Prioridad adaptado para Producto
    sistema/
        SistemaLogistico.java→ integra los TDAs y expone operaciones de negocio
    Main.java --> menú
## Cómo ejecutar el proyecto

### Desde IntelliJ IDEA
1. Abrir el proyecto como proyecto Java
2. Marcar la carpeta `src` como Sources Root
3. Ejecutar `Main.java`

El sistema carga datos de ejemplo automáticamente al iniciar (5 productos y 2 pedidos).

## Link del repositorio
https://github.com/olivetoana750/TPOprimeraentrega

## Actividades realizadas por cada integrante
Francesca Dascanio -->TDA Pila, modelo MovimientoInventario, funcionalidades de trazabilidad de lotes en SistemaLogistico |
Ana Oliveto-->TDA ColaCircular, TDA ColaPrioridad, modelos Pedido y Producto, funcionalidades de expedición e inventario crítico en SistemaLogistico, Main y README |

## Próxima entrega (funcionalidades pendientes)
- **Localización de Stock:** Árbol Binario de Búsqueda (ABB) para búsqueda en O(log n) por código
- **Optimización de Recolección:** Grafo con algoritmo de Dijkstra para rutas mínimas entre pasillos