package com.donizete.android.esteticapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donizete.android.esteticapp.R;
import com.donizete.android.esteticapp.model.Funcionario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FuncionarioActivity extends AppCompatActivity {

    public EditText vMatricula;
    public EditText vNome;
    public EditText vDtAdmissao;
    public EditText vCpf;
    public EditText vRg;
    public EditText vEmail;
    public EditText vTelefone;
    public Button buttonSalvar;
    public Button buttonPedido;
    public Button buttonSair;

    Funcionario objFuncionario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        objFuncionario = new Funcionario();

        vMatricula = (EditText)findViewById(R.id.edtMatricula);
        vNome = (EditText)findViewById(R.id.edtNome);
        vDtAdmissao = (EditText)findViewById(R.id.edtDtAdmissao);
        vCpf = (EditText)findViewById(R.id.edtCpf);
        vRg = (EditText)findViewById(R.id.edtRg);
        vEmail = (EditText)findViewById(R.id.edtEmail);
        vTelefone = (EditText)findViewById(R.id.edtTelefone);
        vMatricula.requestFocus();

        final FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference mRef = mDatabase.getReference().child("Funcionarios");

        Button buttonSair = (Button) findViewById(R.id.btnSair);
        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                finish();
            }
        });

        Button buttonPedido = (Button)findViewById(R.id.btnPedido);
        buttonPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent funcIntent = new Intent(FuncionarioActivity.this, MainActivity.class);
                finish();
            }
        });

        Button buttonSalvar = (Button)findViewById(R.id.btnSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String matricula = vMatricula.getText().toString().trim();
                final String nome = vNome.getText().toString().trim();
                final String dtAdmissao = vDtAdmissao.getText().toString().trim();
                final String cpf = vCpf.getText().toString().trim();
                final String rg = vRg.getText().toString().trim();
                final String email = vEmail.getText().toString().trim();
                final String telefone = vTelefone.getText().toString().trim();

                if (TextUtils.isEmpty(matricula)) {
                    Toast.makeText(getApplicationContext(), "Entre com a Matricula", Toast.LENGTH_SHORT).show();
                    return;
                }
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

                if (!matricula.isEmpty()&& !nome.isEmpty() &&!dtAdmissao.isEmpty() && !email.isEmpty() ){
                    Funcionario f = new Funcionario();
                    f.setId("1");
                    f.setMatricula(matricula);
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
                    vMatricula.requestFocus();

                }else{
                    Toast.makeText(FuncionarioActivity.this, "Erro: Não foi possível SALVAR Funcionario", Toast.LENGTH_LONG).show();
                    finish();
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

            }
        });
    }
    private void limparFuncionario() {

        objFuncionario.setMatricula(null);
        objFuncionario.setNome(null);
        objFuncionario.setDtAdmissao(null);
        objFuncionario.setCpf(null);
        objFuncionario.setRg(null);
        objFuncionario.setEmail(null);
        objFuncionario.setTelefone(null);

        vMatricula.setText("");
        vNome.setText("");
        vDtAdmissao.setText("");
        vCpf.setText("");
        vRg.setText("");
        vEmail.setText("");
        vTelefone.setText("");
    }

    private void popularObjeto(){
        objFuncionario.setMatricula(vMatricula.getText().toString().trim());
        objFuncionario.setNome(vNome.getText().toString().trim());
        objFuncionario.setDtAdmissao(vDtAdmissao.getText().toString().trim());
        objFuncionario.setCpf(vCpf.getText().toString().trim());
        objFuncionario.setRg(vRg.getText().toString().trim());
        objFuncionario.setEmail(vEmail.getText().toString().trim());
        objFuncionario.setTelefone(vTelefone.getText().toString().trim());

    }

}
