package com.donizete.android.esteticapp.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Donizete on 28/10/2016.
 */
@IgnoreExtraProperties
public class Funcionario {

    private String id;
    private String matricula;
    private String nome;
    private String dtAdmissao;
    private String cpf;
    private String rg;
    private String email;
    private String telefone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDtAdmissao() {
        return dtAdmissao;
    }

    public void setDtAdmissao(String dtAdmissao) {
        this.dtAdmissao = dtAdmissao;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result= new HashMap<>();
        result.put("id", id);
        result.put("matricula", matricula);
        result.put("nome", nome);
        result.put("dtAdmissao", dtAdmissao);
        result.put("cpf", cpf);
        result.put("rg", rg);
        result.put("email", email);
        result.put("telefone", telefone);

        return result;
    }

}
