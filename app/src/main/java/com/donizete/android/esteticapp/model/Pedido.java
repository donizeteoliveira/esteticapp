package com.donizete.android.esteticapp.model;

import android.provider.ContactsContract;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Donizete on 16/10/2016.
 */

@IgnoreExtraProperties
public class Pedido {
    private int codPedido;
    private int qtdPedido;
    private String dataPedido;


    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getQtdPedido() {
        return qtdPedido;
    }

    public void setQtdPedido(int qtdPedido) {
        this.qtdPedido = qtdPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codPedido", codPedido);
        result.put("qtdPedido", qtdPedido);
        result.put("dataPedido", dataPedido);
        return result;
    }
}
