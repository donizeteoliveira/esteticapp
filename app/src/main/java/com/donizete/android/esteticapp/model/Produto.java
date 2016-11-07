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
    private String codProduto;
    private String nomeProd;
    private String categoria;
    private String fornecedor;
    private int qtdEstoque;
    private double vlCusto;
    private Double vlCliente;
    private double vlProfissional;
    private byte[] imgProduto;
    private boolean status;

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
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

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getVlCusto() {
        return vlCusto;
    }

    public void setVlCusto(double vlCusto) {
        this.vlCusto = vlCusto;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codProduto", codProduto);
        result.put("nomeProd", nomeProd);
        result.put("categoria", categoria);
        result.put("fornecedor", fornecedor);
        result.put("qtdEstoque", qtdEstoque);
        result.put("vlCusto", vlCusto);
        result.put("vLCliente", vlCliente);
        result.put("vlProfissional", vlProfissional);
        result.put("imgProduto", imgProduto);
        result.put("estado", status);

        return result;
    }
}
