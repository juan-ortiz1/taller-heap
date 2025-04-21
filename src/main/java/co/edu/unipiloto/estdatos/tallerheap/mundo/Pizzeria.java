package co.edu.unipiloto.estdatos.tallerheap.mundo;

import co.edu.unipiloto.estdatos.tallerheap.estructuras.Heap;
import java.util.ArrayList;

public class Pizzeria {
    // ----------------------------------
    // Constantes
    // ----------------------------------

    /**
     * Constante que define la accion de recibir un pedido
     */
    public final static String RECIBIR_PEDIDO = "RECIBIR";
    /**
     * Constante que define la accion de atender un pedido
     */
    public final static String ATENDER_PEDIDO = "ATENDER";
    /**
     * Constante que define la accion de despachar un pedido
     */
    public final static String DESPACHAR_PEDIDO = "DESPACHAR";
    /**
     * Constante que define la accion de finalizar la secuencia de acciones
     */
    public final static String FIN = "FIN";

    // ----------------------------------
    // Atributos
    // ----------------------------------
    /**
     * Heap que almacena los pedidos recibidos
     */
    private Heap<Pedido> pedidosRecibidos;

    /**
     * Getter de pedidos recibidos
     */
    public Heap getPedidosRecibidos() {
        return pedidosRecibidos;
    }

    /**
     * Cola de elementos por despachar
     */
    private Heap<Pedido> colaDespachos;

    /**
     * Getter de elementos por despachar
     */
    public Heap getColaDespachos() {
        return colaDespachos;
    }

    // ----------------------------------
    // Constructor
    // ----------------------------------
    /**
     * Constructor de la case Pizzeria
     */
    public Pizzeria() {
        pedidosRecibidos = new Heap();
        colaDespachos = new Heap();
    }

    // ----------------------------------
    // Métodos
    // ----------------------------------
    /**
     * Agrega un pedido a la cola de prioridad de pedidos recibidos
     *
     * @param nombreAutor nombre del autor del pedido
     * @param precio precio del pedido
     * @param cercania cercania del autor del pedido
     */
    public void agregarPedido(String nombreAutor, double precio, int cercania) {
        Pedido nuevoPedido = new Pedido(precio, nombreAutor, cercania);
        Pedido.comparePorPrecio = true;
        pedidosRecibidos.add(nuevoPedido);
        System.out.println("PEDIDOS RECIBIDOS: " + pedidosRecibidos.toString());
    }

    // Atender al pedido más importante de la cola
    /**
     * Retorna el proximo pedido en la cola de prioridad o null si no existe.
     *
     * @return p El pedido proximo en la cola de prioridad
     */
    public Pedido atenderPedido() {
        if (pedidosRecibidos.isEmpty() == false) {
            Pedido.comparePorPrecio = true;
            Pedido pedidoAtendido = pedidosRecibidos.poll();
            Pedido.comparePorPrecio = false;
            colaDespachos.add(pedidoAtendido);
            System.out.println("PEDIDOS RECIBIDOS: " + pedidosRecibidos.toString());
            System.out.println("COLA DE DESPACHOS: " + colaDespachos.toString());
            return pedidoAtendido;
        }
        return null;
    }

    /**
     * Despacha al pedido proximo a ser despachado.
     *
     * @return Pedido proximo pedido a despachar
     */
    public Pedido despacharPedido() {
        if (colaDespachos.isEmpty() == false) {
            Pedido.comparePorPrecio = false;
            Pedido pedidoDespachado = colaDespachos.poll();
            System.out.println("COLA DE DESPACHOS: " + colaDespachos.toString());
            return pedidoDespachado;
        }
        return null;
    }

    /**
     * Retorna la cola de prioridad de pedidos recibidos como una lista.
     *
     * @return list lista de pedidos recibidos.
     */
    public ArrayList<Pedido> pedidosRecibidosList() {
        ArrayList<Pedido> listaPedidosRecibidos = new ArrayList<>();
        for (int i = 1; i <= pedidosRecibidos.size(); i++) {
            listaPedidosRecibidos.add(pedidosRecibidos.getNodeKey(i));
        }
        return listaPedidosRecibidos;
    }

    /**
     * Retorna la cola de prioridad de despachos como una lista.
     *
     * @return list cola de prioridad de despachos.
     */
    public ArrayList<Pedido> colaDespachosList() {
        ArrayList<Pedido> listaDespachos = new ArrayList<>();
        for (int i = 1; i <= colaDespachos.size(); i++) {
            listaDespachos.add(colaDespachos.getNodeKey(i));
        }
        return listaDespachos;
    }
}
