# Centro Logístico de Distribución Avanzada

## Nombre del proyecto
Sistema de Gestión Logística - Centro de Distribución Avanzada

## Integrantes del grupo
- Francesca Dascanio
- Ana Oliveto

## Alternativa elegida
**Alternativa C:** Centro Logístico de Distribución Avanzada

## Descripción
Sistema que permite gestionar el almacenamiento, stock y despacho de un centro de distribución logístico. Cada funcionalidad está resuelta con la estructura de datos más adecuada para el problema.

---

## Estructuras de datos utilizadas

### ABB (Árbol Binario de Búsqueda) — Localización de Stock
Los productos se organizan por código universal en un árbol binario. Permite buscar cualquier producto en O(log n) sin recorrer todo el inventario.

### Grafo con Dijkstra — Optimización de Recolección
Los pasillos del almacén se modelan como vértices y las conexiones entre ellos como aristas con peso (distancia en metros). Dijkstra calcula la ruta de menor distancia total para el operario.

### Cola con Prioridad — Control de Inventario Crítico
Los productos con stock bajo se insertan ordenados por urgencia. A menor stock, mayor prioridad. El frente de la cola siempre tiene el producto más urgente para reponer.

### Cola Circular (FIFO) — Línea de Expedición
Los pedidos listos para despachar se encolan en orden de llegada. La variante circular reutiliza el espacio liberado por pedidos ya despachados sin mover elementos en memoria.

### Pila (LIFO) — Trazabilidad de Lotes
Cada movimiento de inventario (ingreso o egreso) se apila. Como es LIFO, el último movimiento siempre está en el tope y puede revertirse en O(1) ante un error operativo.

---

## Funcionalidades implementadas

### 1. Localización de Stock (ABB)
- Agregar productos al inventario por código universal
- Buscar producto por código en O(log n)
- Eliminar producto del inventario
- Listar inventario completo ordenado por código (inorden)

### 2. Optimización de Recolección (Grafo + Dijkstra)
- Registrar pasillos del almacén
- Registrar conexiones entre pasillos con distancia en metros
- Calcular ruta óptima entre dos pasillos
- Calcular ruta óptima hacia el pasillo donde está un producto

### 3. Control de Inventario Crítico (Cola con Prioridad)
- Detectar automáticamente productos que caen por debajo del umbral crítico (50 unidades)
- Ordenar productos críticos por urgencia: menor stock = mayor prioridad
- Atender el producto más urgente primero
- Ver el próximo producto crítico sin atenderlo
- Marcar productos como críticos manualmente

### 4. Línea de Expedición (Cola Circular FIFO)
- Agregar pedidos a la cola de expedición
- Despachar el siguiente pedido (el primero en llegar)
- Ver todos los pedidos en espera
- Consultar el próximo pedido sin eliminarlo

### 5. Trazabilidad de Lotes (Pila LIFO)
- Registrar ingreso de stock con validación
- Registrar egreso de stock con validación de stock suficiente
- Deshacer el último movimiento registrado, restaurando el stock anterior
- Ver el historial completo de movimientos

---

## Estructura del proyecto
src/

├── model/

│   ├── Producto.java              → entidad producto del depósito

│   ├── MovimientoInventario.java  → registro de cada operación de stock

│   └── Pedido.java                → pedido listo para despachar

├── tda/

│   ├── ABB.java                   → Árbol Binario de Búsqueda

│   ├── NodoABB.java               → nodo del ABB

│   ├── Grafo.java                 → grafo con lista de adyacencia y Dijkstra

│   ├── NodoPasillo.java           → vértice del grafo

│   ├── NodoAdyacente.java         → arista con peso del grafo

│   ├── ColaPrioridad.java         → cola con prioridad para inventario crítico

│   ├── ColaCircular.java          → cola circular FIFO para expedición

│   └── Pila.java                  → pila LIFO para trazabilidad

├── sistema/

│   └── SistemaLogistico.java      → integra los TDAs y expone operaciones

└── Main.java                      → menú interactivo demostrable

---

## Cómo ejecutar el proyecto

### Desde IntelliJ IDEA
1. Abrir el proyecto
2. Marcar la carpeta `src` como Sources Root
3. Ejecutar `Main.java`

El sistema carga datos de ejemplo automáticamente al iniciar.

### Desde la terminal
```bash
javac -d out -sourcepath src src/model/Producto.java src/model/MovimientoInventario.java src/model/Pedido.java src/tda/NodoABB.java src/tda/ABB.java src/tda/NodoAdyacente.java src/tda/NodoPasillo.java src/tda/Grafo.java src/tda/Pila.java src/tda/ColaCircular.java src/tda/ColaPrioridad.java src/sistema/SistemaLogistico.java src/Main.java

java -cp out Main
```

---

## Link del repositorio
https://github.com/olivetoana750/TPOprimeraentrega

---

## Actividades realizadas por cada integrante

| Integrante | Actividades |
|---|---|
| Francesca Dascanio | TDA Pila, TDA ColaCircular, modelo MovimientoInventario, trazabilidad de lotes y línea de expedición |
| Ana Oliveto | TDA ABB, TDA Grafo con Dijkstra, TDA ColaPrioridad, modelos Producto y Pedido, SistemaLogistico, Main y README |
