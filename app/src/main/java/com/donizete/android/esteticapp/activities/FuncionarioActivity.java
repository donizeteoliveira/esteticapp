package com.donizete.android.esteticapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Funcionario;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.util.Base64.DEFAULT;

public class FuncionarioActivity extends AppCompatActivity {

    public EditText vFindFunc;
    public EditText vNome;
    public EditText vDtAdmissao;
    public EditText vCpf;
    public EditText vRg;
    public EditText vEmail;
    public EditText vTelefone;
    public Button buttonSalvar;
    public Button buttonPedido;
    public Button buttonSair;
    public ImageButton imgBtnPesq;

    Funcionario objFuncionario;

    public static final int CADASTRO_REQUEST = 1;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        objFuncionario = new Funcionario();
        vFindFunc = (EditText) findViewById(R.id.pesquisaFunc);
        vNome = (EditText) findViewById(R.id.edtNome);
        vDtAdmissao = (EditText) findViewById(R.id.edtDtAdmissao);
        vCpf = (EditText) findViewById(R.id.edtCpf);
        vRg = (EditText) findViewById(R.id.edtRg);
        vEmail = (EditText) findViewById(R.id.edtEmail);
        vTelefone = (EditText) findViewById(R.id.edtTelefone);
        vNome.requestFocus();

        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = mDatabase.getReference().child("Funcionarios");

        ImageButton imgBtnPesq = (ImageButton)findViewById(R.id.imgBtnFindFunc);
        imgBtnPesq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FuncionarioActivity.this, ListFuncActivity.class );
                TextView edpesqFunc = (TextView)findViewById(R.id.pesquisaFunc);

                String txt ="";
                txt = edpesqFunc.getText().toString();

                Bundle bundlepar = new Bundle();
                bundlepar.putString("nome", txt);
                intent.putExtras(bundlepar);

                startActivity(intent);

            }
        });


        Button buttonSair = (Button) findViewById(R.id.btnSair);
        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonPedido = (Button) findViewById(R.id.btnPedido);
        buttonPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent funcIntent = new Intent(FuncionarioActivity.this, ListFuncActivity.class);
                startActivityForResult(funcIntent, CADASTRO_REQUEST);
                finish();
            }
        });

        Button buttonSalvar = (Button) findViewById(R.id.btnSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nome = vNome.getText().toString().trim();
                final String dtAdmissao = vDtAdmissao.getText().toString().trim();
                final String cpf = vCpf.getText().toString().trim();
                final String rg = vRg.getText().toString().trim();
                final String email = vEmail.getText().toString().trim();
                final String telefone = vTelefone.getText().toString().trim();

                if (TextUtils.isEmpty(nome)) {
                    Toast.makeText(getApplicationContext(), "Entre com o Nome do Funcionario", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(dtAdmissao)) {
                    Toast.makeText(getApplicationContext(), "Entre com a Data de Admissão", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Entre com o Email do Funcionário", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!nome.isEmpty() && !dtAdmissao.isEmpty() && !email.isEmpty()) {
                    Funcionario f = new Funcionario();
                    f.setNome(nome);
                    f.setDtAdmissao(dtAdmissao);
                    f.setCpf(cpf);
                    f.setRg(rg);
                    f.setEmail(email);
                    f.setTelefone(telefone);

                    DatabaseReference novoFuncFirebase = mRef.push();
                    novoFuncFirebase.updateChildren(f.toMap());
                    Toast.makeText(FuncionarioActivity.this, "Funcionário gravado com Exito", Toast.LENGTH_LONG).show();
                    limparFuncionario();
                    vNome.requestFocus();

                } else {
                    Toast.makeText(FuncionarioActivity.this, "Erro: Não foi possível SALVAR Funcionario", Toast.LENGTH_LONG).show();
                    finish();
                }



            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void limparFuncionario() {

        objFuncionario.setNome(null);
        objFuncionario.setDtAdmissao(null);
        objFuncionario.setCpf(null);
        objFuncionario.setRg(null);
        objFuncionario.setEmail(null);
        objFuncionario.setTelefone(null);

        vNome.setText("");
        vDtAdmissao.setText("");
        vCpf.setText("");
        vRg.setText("");
        vEmail.setText("");
        vTelefone.setText("");
    }

    private void popularObjeto() {
        objFuncionario.setNome(vNome.getText().toString().trim());
        objFuncionario.setDtAdmissao(vDtAdmissao.getText().toString().trim());
        objFuncionario.setCpf(vCpf.getText().toString().trim());
        objFuncionario.setRg(vRg.getText().toString().trim());
        objFuncionario.setEmail(vEmail.getText().toString().trim());
        objFuncionario.setTelefone(vTelefone.getText().toString().trim());

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Funcionario Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }



    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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

//Gravando dados

// DatabaseReference funcsRef = mRef.child("funcionarios");
//Map<String, Funcionario> funcionarios = new HashMap<String, Funcionario>();
//funcionarios.put(id, new Funcionario( matricula, nome, dtAdmissao, email));
