package com.donizete.android.esteticapp.model;

/**
 * Created by Donizete on 16/10/2016.
 */

public class ItemPedido {
    private int id;
    private String itemPedidoCodPedido;
    private int itemPedidoQuantidade;
    private String nomeCliente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemPedidoCodPedido() {
        return itemPedidoCodPedido;
    }

    public void setItemPedidoCodPedido(String itemPedidoCodPedido) {
        this.itemPedidoCodPedido = itemPedidoCodPedido;
    }

    public int getItemPedidoQuantidade() {
        return itemPedidoQuantidade;
    }

    public void setItemPedidoQuantidade(int itemPedidoQuantidade) {
        this.itemPedidoQuantidade = itemPedidoQuantidade;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
