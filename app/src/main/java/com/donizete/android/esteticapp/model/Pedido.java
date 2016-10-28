package com.donizete.android.esteticapp.model;

/**
 * Created by Donizete on 16/10/2016.
 */

public class Pedido {
    private int id;
    private String pedidoCliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPedidoCliente() {
        return pedidoCliente;
    }

    public void setPedidoCliente(String pedidoCliente) {
        this.pedidoCliente = pedidoCliente;
    }
}
