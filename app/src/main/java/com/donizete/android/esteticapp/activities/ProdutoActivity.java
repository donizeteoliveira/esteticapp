package com.donizete.android.esteticapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Produto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProdutoActivity extends AppCompatActivity {

    private EditText vPesquisaProd;
    private EditText vCodProduto;
    private EditText vNomeProd;
    private EditText vCategoria;
    private EditText vFornecedor;
    private EditText vQtdEstoque;
    private EditText vVlCusto;
    private EditText vVlCliente;
    private EditText vVlProfissional;
    private ImageView vImgProduto;
    private Boolean vStatus;

    private ImageButton vBtnFindProd;
    private Button vBtnSalvarProd;
    private Button vBtnAlterarProd;
    private Button vBtnExcluirProd;
    private Button vbtnSairProd;

    Produto objproduto;

    public static final int CADASTRO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        objproduto = new Produto();
        vPesquisaProd = (EditText) findViewById(R.id.pesquisaProd);
        vCodProduto = (EditText) findViewById(R.id.etCodProduto);
        vNomeProd = (EditText) findViewById(R.id.etNomeProd);
        vCategoria = (EditText) findViewById(R.id.etCategoria);
        vFornecedor = (EditText) findViewById(R.id.etFornecedor);
        vQtdEstoque = (EditText) findViewById(R.id.etqtdEstoque);
        vVlCusto = (EditText) findViewById(R.id.etVlCusto);
        vVlCliente = (EditText) findViewById(R.id.etVlCliente);
        vVlProfissional = (EditText) findViewById(R.id.etVlProfissional);
        //vImgProduto = (ImageView) findViewById(R.id.imgProduct);  verificar com Keniston
        //vStatus = (Boolean) findViewById(R.id.rbStatus);  verificar com Keniston

        vCodProduto.requestFocus();

        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = mDatabase.getReference().child("Produtos");

        ImageButton imgBtnPesq = (ImageButton)findViewById(R.id.btnFindProd);
        imgBtnPesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ProdutoActivity.this, ListProdActivity.class );
                TextView edpesqProd = (TextView)findViewById(R.id.pesquisaProd);

                String txt ="";
                txt = edpesqProd.getText().toString();

                Bundle bundlepar = new Bundle();
                bundlepar.putString("nomeProd", txt);
                intent.putExtras(bundlepar);

                startActivity(intent);

                //startActivityForResult(intent, CADASTRO_REQUEST);
                //finish();
            }
        });

        Button buttonSair = (Button) findViewById(R.id.btnSairProd);
        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*Button buttonPedido = (Button) findViewById(R.id.btnAlterarProd);
        buttonPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listProd = new Intent(ProdutoActivity.this, ListProdActivity.class);
                startActivityForResult(listProd, CADASTRO_REQUEST);
                finish();
            }
        });*/

        Button buttonSalvar = (Button) findViewById(R.id.btnSalvarProd);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String codProduto = vCodProduto.getText().toString().trim();
                final String nomeProd = vNomeProd.getText().toString().trim();
                final String categoria = vCategoria.getText().toString().trim();
                final String fornecedor = vFornecedor.getText().toString().trim();
                final String qtdEstoque = vQtdEstoque.getText().toString().trim();
                final String vlCusto = vVlCusto.getText().toString().trim();
                final String vlCliente = vVlCliente.getText().toString().trim();
                final String vlProfissional = vVlProfissional.getText().toString().trim();
                //final String imgProduto = vImgProduto.getText().toString().trim(); Verificar com Keniston

                if (TextUtils.isEmpty(codProduto)) {
                    Toast.makeText(getApplicationContext(), "Entre com o Código do Produto", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(nomeProd)) {
                    Toast.makeText(getApplicationContext(), "Entre com o Nome do produto", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(categoria)) {
                    Toast.makeText(getApplicationContext(), "Entre com a Categoria", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(fornecedor)) {
                    Toast.makeText(getApplicationContext(), "Entre com o nome do Fornecedor", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(vlCusto)) {
                    Toast.makeText(getApplicationContext(), "Entre com o valor de CUSTO", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(vlProfissional)) {
                    Toast.makeText(getApplicationContext(), "Entre com o Nome do Funcionario", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!codProduto.isEmpty() && !nomeProd.isEmpty() &&!categoria.isEmpty() && !fornecedor.isEmpty() && !vlCusto.isEmpty() && !vlProfissional.isEmpty()) {
                    Produto p = new Produto();
                    p.setCodProduto(codProduto);
                    p.setNomeProd(nomeProd);
                    p.setCategoria(categoria);
                    p.setFornecedor(fornecedor);
                    p.setQtdEstoque(Integer.parseInt(qtdEstoque));
                    p.setVlCusto(Double.parseDouble(vlCusto));
                    p.setVlCliente(Double.parseDouble(vlCliente));
                    p.setVlProfissional(Double.parseDouble(vlProfissional));

                    DatabaseReference novoProdFirebase = mRef.push();
                    novoProdFirebase.updateChildren(p.toMap());
                    Toast.makeText(ProdutoActivity.this, "Produto gravado com Exito", Toast.LENGTH_LONG).show();
                    LimparProduto();
                    vCodProduto.requestFocus();

                } else {
                    Toast.makeText(ProdutoActivity.this, "Erro: Não foi possível SALVAR Produto", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    private void LimparProduto() {

        objproduto.setCodProduto(null);
        objproduto.setNomeProd(null);
        objproduto.setCategoria(null);
        objproduto.setFornecedor(null);
        objproduto.setQtdEstoque(0);
        objproduto.setVlCusto(0);
        objproduto.setVlCliente(null);
        objproduto.setVlProfissional(0);
        objproduto.setImgProduto(null);
        objproduto.setStatus(true);

        vCodProduto.setText("");
        vNomeProd.setText("");
        vCategoria.setText("");
        vFornecedor.setText("");
        vQtdEstoque.setText("");
        vVlCusto.setText("");
        vVlCliente.setText("");
        vVlProfissional.setText("");
        //vImgProduto.setImageDrawable("");
        vStatus.parseBoolean("True");
    }
}
