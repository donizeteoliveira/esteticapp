package com.donizete.android.esteticapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.donizete.android.esteticapp.Adapters.ProdutosAdapter;
import com.donizete.android.esteticapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Produto> produtos = new ArrayList<>();
    private DatabaseHelper dbHelper;
    ProdutosAdapter adaptador;
    public static final int CADASTRO_REQUEST=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lista = (ListView) findViewById(R.id.lstProducts);
        adaptador = new ProdutosAdapter(this, 0, produtos);
        lista.setAdapter(adaptador);

        addFixedProducts();

        findViewById(R.id.lstProducts);
        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroIntent = new Intent(MainActivity.this, CadastroActivity.class);

                startActivityForResult(cadastroIntent, CADASTRO_REQUEST);
            }
        })

    }
}
