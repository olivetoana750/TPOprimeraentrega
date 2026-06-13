package modelos;

public class Pedido {
    private String numeroPedido;
    private String destinatario;
    private String destino;
    private int cantidadBultos;

    public Pedido(String numeroPedido, String destinatario, String destino, int cantidadBultos) {
        this.numeroPedido = numeroPedido;
        this.destinatario = destinatario;
        this.destino = destino;
        this.cantidadBultos = cantidadBultos;
    }

    public String getNumeroPedido() { return numeroPedido; }
    public String getDestinatario() { return destinatario; }
    public String getDestino() { return destino; }
    public int getCantidadBultos() { return cantidadBultos; }

    @Override
    public String toString() {
        return "Pedido " + numeroPedido + " | Destinatario: " + destinatario
                + " | Destino: " + destino + " | Bultos: " + cantidadBultos;
    }
}
