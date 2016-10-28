package com.donizete.android.esteticapp.model;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;

/**
 * Created by Donizete on 14/10/2016.
 */
@IgnoreExtraProperties
public class Produto  {
    private int id;
    private String nome;
    private String categoria;
    private String fornecedor;
    private int vlQuant;
    private Double vlCliente;
    private double vlProfissional;
    private byte[] imgProduto;

    public int getId() { return id;  }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getVlQuant() {
        return vlQuant;
    }

    public void setVlQuant(int vlQuant) {
        this.vlQuant = vlQuant;
    }

    public Double getVlCliente() {
        return vlCliente;
    }

    public void setVlCliente(Double vlCliente) {
        this.vlCliente = vlCliente;
    }

    public double getVlProfissional() {
        return vlProfissional;
    }

    public void setVlProfissional(double vlProfissional) {
        this.vlProfissional = vlProfissional;
    }

    public byte[] getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(byte[] imgProduto) {
        this.imgProduto = imgProduto;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("nome", nome);
        result.put("categoria", categoria);
        result.put("fornecedor", fornecedor);
        result.put("vlQuant", vlQuant);
        result.put("vLCliente", vlCliente);
        result.put("vlProfissional", vlProfissional);
        result.put("imgProduto", imgProduto);

        return result;
    }
}
