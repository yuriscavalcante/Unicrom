package com.example.unicrom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unicrom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UpdateSenha extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_senha);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.emailReset);
    }


    public void openUser(View view) {
        Intent i = new Intent(UpdateSenha.this, Login.class);
        startActivity(i);
    }
    public void reset(View view){
        firebaseAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if( task.isSuccessful() ){
                            Toast.makeText(
                                    UpdateSenha.this,
                                    "Email de recuperação Enviado",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                        else{
                            Toast.makeText(
                                    UpdateSenha.this,
                                    "Erro! \nTente Novamente!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        }

                    }
                });
    }
}