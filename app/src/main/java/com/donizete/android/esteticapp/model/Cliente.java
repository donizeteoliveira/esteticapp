package com.donizete.android.esteticapp.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Donizete on 31/10/2016.
 */
@IgnoreExtraProperties
public class Cliente {
    private String nome;
    private String cpf;
    private String rg;
    private String email;
    private String imgCliente;
    private String tipoPessoa;

    public String getNome() { return nome;    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }
    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgCliente() {
        return imgCliente;
    }
    public void setImgCliente(String imgCliente) {
        this.imgCliente = imgCliente;
    }

    public String getTipoPessoa() { return tipoPessoa;    }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa;    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nome", nome);
        result.put("cpf", cpf);
        result.put("rg", rg);
        result.put("email", email);
        result.put("imgCliente", imgCliente);
        result.put("tipoPessoa", tipoPessoa);
        return result;

    }
}
