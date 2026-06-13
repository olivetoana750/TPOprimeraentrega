package modelos;

public class Producto {
    private String codigoUniversal;
    private String nombre;
    private String ubicacion;
    private int stockActual;

    public Producto(String codigoUniversal, String nombre, String ubicacion, int stockActual) {
        this.codigoUniversal = codigoUniversal;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.stockActual = stockActual;
    }

    public String getCodigoUniversal() { return codigoUniversal; }
    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    @Override
    public String toString() {
        return "[" + codigoUniversal + "] " + nombre + " | Ubicacion: " + ubicacion + " | Stock: " + stockActual;
    }
}
