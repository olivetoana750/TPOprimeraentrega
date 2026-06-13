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

### Gestión de inventario base
- Agregar productos con código universal, nombre, ubicación y stock
- Buscar producto por código
- Ver todos los productos registrados

---

## Estructura del proyecto

```
src/
├── modelos/
│   ├── Producto.java              → entidad producto del depósito
│   ├── MovimientoInventario.java  → registro de cada operación de stock
│   └── Pedido.java                → pedido listo para despachar
├── tda/
│   ├── Pila.java                  → TDA Pila adaptado para MovimientoInventario
│   └── ColaCircular.java          → TDA Cola Circular adaptado para Pedido
├── sistema/
│   └── SistemaLogistico.java      → integra los TDAs y expone operaciones de negocio
└── Main.java                      → menú interactivo demostrable
```

---

## Cómo ejecutar el proyecto

### Desde la terminal (sin IDE)
```bash
# Compilar (desde la raíz del proyecto)
javac -d out -sourcepath src src/modelos/Producto.java src/modelos/MovimientoInventario.java src/modelos/Pedido.java src/tda/Pila.java src/tda/ColaCircular.java src/sistema/SistemaLogistico.java src/Main.java

# Ejecutar
java -cp out Main
```

### Desde IntelliJ IDEA
1. Abrir el proyecto como proyecto Java
2. Marcar la carpeta `src` como Sources Root
3. Ejecutar `Main.java`

El sistema carga datos de ejemplo automáticamente al iniciar (3 productos y 2 pedidos).

---

## Link del repositorio
[https://github.com/usuario/centro-logistico](https://github.com/usuario/centro-logistico)
*(actualizar con el link real una vez creado el repositorio)*

---

## Actividades realizadas por cada integrante

| Integrante | Actividades |
|---|---|
| Francesca Dascanio | TDA Pila, modelo MovimientoInventario, funcionalidades de trazabilidad de lotes en SistemaLogistico |
| Ana Oliveto | TDA ColaCircular, modelo Pedido, funcionalidades de expedición en SistemaLogistico, Main y README |

---

## Próxima entrega (funcionalidades pendientes)
- **Localización de Stock:** Árbol Binario de Búsqueda (ABB) para búsqueda en O(log n) por código
- **Control de Inventario Crítico:** Cola con Prioridad para gestionar productos con stock bajo
- **Optimización de Recolección:** Grafo con algoritmo de Dijkstra para rutas mínimas entre pasillos
