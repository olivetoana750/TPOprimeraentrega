package model;

public class MovimientoInventario {
    private String tipoOperacion;   // "INGRESO", "EGRESO" o "MODIFICACION"
    private String codigoProducto;
    private String nombreProducto;
    private int stockAntes;
    private int stockDespues;
    private String descripcion;

    public MovimientoInventario(String tipoOperacion, String codigoProducto, String nombreProducto,
        int stockAntes, int stockDespues, String descripcion) {
        this.tipoOperacion = tipoOperacion;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.stockAntes = stockAntes;
        this.stockDespues = stockDespues;
        this.descripcion = descripcion;
    }

    public String getTipoOperacion() { return tipoOperacion; }
    public String getCodigoProducto() { return codigoProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public int getStockAntes() { return stockAntes; }
    public int getStockDespues() { return stockDespues; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        return tipoOperacion + " | Producto: " + nombreProducto + " (" + codigoProducto + ")"
                + " | Stock antes: " + stockAntes + " -> despues: " + stockDespues
                + " | " + descripcion;
    }
}
