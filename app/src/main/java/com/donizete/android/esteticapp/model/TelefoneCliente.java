package com.donizete.android.esteticapp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Donizete on 03/11/2016.
 */

public class TelefoneCliente {
    private int codTel;
    private String tipo;
    private String numero;

    public int getCodTel() { return codTel;    }
    public void setCodTel(int codTel) { this.codTel = codTel;    }

    public String getTipo() {  return tipo;    }
    public void setTipo(String tipo) {  this.tipo = tipo;   }

    public String getNumero() {  return numero;    }
    public void setNumero(String numero) { this.numero = numero;   }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codTel", codTel);
        result.put("tipo", tipo);
        result.put("numero", numero);
        return result;
    }
    // olhar nas apostilas do black o item 16.1 eo 20.2
}
