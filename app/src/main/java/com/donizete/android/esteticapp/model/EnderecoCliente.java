package com.donizete.android.esteticapp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Donizete on 03/11/2016.
 */

public class EnderecoCliente {
    private int codEndereco;
    private String nomeEnd;
    private String cep;
    private String tipo;
    private String bairro;
    private String cidade;
    private String uf;

    public int getCodEndereco() {   return codEndereco;    }
    public void setCodEndereco(int codEndereco) { this.codEndereco = codEndereco;    }

    public String getNomeEnd() {  return nomeEnd;    }
    public void setNomeEnd(String nomeEnd) {  this.nomeEnd = nomeEnd;    }

    public String getCep() { return cep;    }
    public void setCep(String cep) { this.cep = cep;    }

    public String getTipo() { return tipo;    }
    public void setTipo(String tipo) {  this.tipo = tipo;    }

    public String getBairro() {  return bairro;    }
    public void setBairro(String bairro) {  this.bairro = bairro;    }

    public String getCidade() {  return cidade;    }
    public void setCidade(String cidade) { this.cidade = cidade;    }

    public String getUf() { return uf;    }
    public void setUf(String uf) { this.uf = uf;    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codEndereco", codEndereco);
        result.put("nomeEnd", nomeEnd);
        result.put("cep", cep);
        result.put("tipo", tipo);
        result.put("bairro", bairro);
        result.put("cidade", cidade);
        result.put("uf", uf);
        return result;
    }
}
