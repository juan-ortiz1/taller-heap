package co.edu.unipiloto.estdatos.tallerheap.mundo;

public class Pedido implements Comparable<Pedido> {

    // ----------------------------------
    // Atributos
    // ----------------------------------
    /**
     * Comparar precio para determinar maxHeap
     */
    public static boolean comparePorPrecio;
    /**
     * Precio del pedido
     */
    private double precio;

    /**
     * Autor del pedido
     */
    private String autorPedido;

    /**
     * Cercania del pedido
     */
    private int cercania;

    // ----------------------------------
    // Constructor
    // ----------------------------------
    /**
     * Constructor del pedido
     */
    public Pedido(double precio, String autorPedido, int cercania) {
        this.precio = precio;
        this.autorPedido = autorPedido;
        this.cercania = cercania;
    }

    // ----------------------------------
    // Métodos
    // ----------------------------------
    /**
     * Getter del precio del pedido
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Getter del autor del pedido
     */
    public String getAutorPedido() {
        return autorPedido;
    }

    /**
     * Getter de la cercania del pedido
     */
    public int getCercania() {
        return cercania;
    }

    // TODO 
    @Override
    public int compareTo(Pedido o) {
        if (comparePorPrecio) {
            return Double.compare(this.precio, o.precio);
        } else {
            return Integer.compare(o.cercania, this.cercania);
        }
    }

    @Override
    public String toString() {
        return "Pedido{" + "precio=" + precio + ", autorPedido=" + autorPedido + ", cercania=" + cercania + '}';
    }

}
