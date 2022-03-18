package com.example.unicrom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unicrom.R;
import com.example.unicrom.model.User;
import com.example.unicrom.util.conection;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText email, password;
    Button loginButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = conection.authentication();
        inicializeComponent();
    }

    public void authentication(View view){
        String authEmail = email.getText().toString();
        String authPass = password.getText().toString();


        if(!authEmail.isEmpty()){
            if(!authPass.isEmpty()){
                User user = new User();

                user.setEmail(authEmail);
                user.setSenha(authPass);

                login(user);                
            }else{
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void login(User user) {

        auth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    openHome();
                    System.out.println("Tudo ok");
                }else{
                    String exception="";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        exception="USUÁRIO NÃO CADASTRADO!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception="DADOS INVÁLIDOS!";
                    }catch (Exception e){
                        exception="ERRO AO LOGAR!"+e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(Login.this, exception, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void openHome() {
        Intent i = new Intent(Login.this, Home.class);
        startActivity(i);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser authUser = auth.getCurrentUser();
        if(authUser!=null){
            //openHome();
            System.out.println("Tudo Ok");
        }
    }

    private void inicializeComponent(){
        email = findViewById(R.id.editTextEmailLogin);
        password = findViewById(R.id.editTextPasswordLogin);
        loginButton = findViewById(R.id.loginButton);

    }
}