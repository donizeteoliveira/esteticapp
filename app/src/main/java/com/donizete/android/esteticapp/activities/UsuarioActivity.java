package com.donizete.android.esteticapp.activities;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donizete.android.esteticapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UsuarioActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private Button vbtnSalvar;
    private Button vbtnCancel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);


        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();

        //btnSignIn = (Button) findViewById(R.id.sign_in_button);
        vbtnSalvar = (Button) findViewById(R.id.btnSalvar);
        vbtnCancel = (Button) findViewById(R.id.btnCancel);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        vbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UsuarioActivity.this, LoginActivity.class));
                finish();
            }
        });

        vbtnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Entre com seu email!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Entre com sua Senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Senha muito curta, insira mínimo de 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }


                //Criando usuário
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(UsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(UsuarioActivity.this, "Usuário Criado com sucesso" + task.isSuccessful(), Toast.LENGTH_LONG).show();
                                // progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(UsuarioActivity.this, "Falha na Autenticação. Entre em contato com o Administrador." + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(UsuarioActivity.this,MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

}

