package com.donizete.android.esteticapp.activities;


import java.io.Serializable;
import io.realm.RealmObject;

/**
 * Created by Donizete on 14/10/2016.
 */
public class Produto extends RealmObject {
    private int id;
    private String txtNome;
    private String txtCategoria;
    private String txtFornecedor;
    private int vlQuant;
    private Double vlCliente;
    private double vlProfissional;
    private byte[] imgProduto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxtNome() {
        return txtNome;
    }

    public void setTxtNome(String txtNome) {
        this.txtNome = txtNome;
    }

    public String getTxtCategoria() {
        return txtCategoria;
    }

    public void setTxtCategoria(String txtCategoria) {
        this.txtCategoria = txtCategoria;
    }

    public String getTxtFornecedor() {
        return txtFornecedor;
    }

    public void setTxtFornecedor(String txtFornecedor) {
        this.txtFornecedor = txtFornecedor;
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
}
