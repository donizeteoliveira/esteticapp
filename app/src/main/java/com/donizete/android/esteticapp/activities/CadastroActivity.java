package com.donizete.android.esteticapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Produto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


    }
}
 /*FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference().child("produtos");

        DatabaseReference ref2 = database.getReference().child("cargos");
        Map<String, Object> c = new HashMap<>();
        c.put("id", 123456);
        c.put("nome", "Gerente");
        ref2.child("cargo1").updateChildren(c);

        Produto p = new Produto();
        p.setId(3);
        p.setTxtNome("Produto de beleza 123");
        p.setTxtCategoria("Mulher");

        DatabaseReference novoProdFirebase = ref.push(); //child("produto2");
        novoProdFirebase.updateChildren(p.toMap());*/