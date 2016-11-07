package com.donizete.android.esteticapp.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.donizete.android.esteticapp.Adapters.ProdutosAdapter;
import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Produto;
import com.donizete.android.esteticapp.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Produto> produtos = new ArrayList<>();
    private DatabaseHelper dbHelper;
    ProdutosAdapter adaptador;
    public static final int CADASTRO_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ListView lista = (ListView) findViewById(R.id.lstProducts);
        adaptador = new ProdutosAdapter(this, 0, produtos);
        lista.setAdapter(adaptador);*/


        ImageButton imgBtnProdM = (ImageButton)findViewById(R.id.imgBtnProdMain);
        imgBtnProdM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroIntent = new Intent(MainActivity.this, ProdutoActivity.class);
                startActivityForResult(cadastroIntent, CADASTRO_REQUEST);
            }
        });

        ImageButton imgBtnProdF = (ImageButton) findViewById(R.id.imgBtnFuncMain);
        imgBtnProdF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent funcionarioIntent = new Intent(MainActivity.this, FuncionarioActivity.class);
                startActivityForResult(funcionarioIntent, CADASTRO_REQUEST);
            }
        });

        ImageButton imgBtnProdS = (ImageButton)findViewById(R.id.imgBtnSairMain);
        imgBtnProdS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /*
    private void addFixedProducts(){

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", "Sapato");
        values.put("categoria", "Vestuario");
        values.put("valor", 123.98);
        long result = db.insert("produto", null, values);

        if(result != -1){
            Toast.makeText(this,getString(R.string.successfully_insert_produto), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.error_insert_produto), Toast.LENGTH_SHORT).show();
        }
        db.execSQL("INSERT INTO produto(nome, categoria, valor) VALUES ('Bolsa', 'Mulher', 4978.45);");
        for (Produto p : listProduto()){
            produtos.add(p);
        }
        adaptador.notifyDataSetChanged();

    }

    // realiza a carga dos produtos no Array
    public List<Produto> listProduto() {
        List<Produto> result = new ArrayList<Produto>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _id, txtNome, txtCategoria, txtFornecedor, vlCliente, vlProfissional", null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Produto p = new Produto();
            p.setId(cursor.getInt(0));
            p.set(cursor.getString(1));
            p.setTxtCategoria(cursor.getString(2));
            p.setTxtFornecedor(cursor.getString(3));
            p.setVlCliente(cursor.getDouble(4));
            p.setVlProfissional(cursor.getDouble(5));

            result.add(p);
            cursor.moveToNext();
        }
        return result;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CADASTRO_REQUEST) {
            if (resultCode == RESULT_OK) {
                Produto p = (Produto) data.getExtras().getSerializable("produto");
                boolean add = produtos.add(p);
                adaptador.notifyDataSetChanged();
            }
        }
    }*/



}
