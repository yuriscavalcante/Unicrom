package com.example.unicrom.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicrom.R;
import com.example.unicrom.model.modelUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class DadosPessoais extends AppCompatActivity {
    TextView mat;
    EditText nome, email, sexo, dataNasc;
    Button edit, saveEdit;
    DatabaseReference userdb;
    String userId, nomeLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pessoais);
        inicialize();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userdb = FirebaseDatabase.getInstance().getReference().child("alunos").child(userId);


    }

    @Override
    protected void onStart() {
        super.onStart();
        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelUser modelUser = snapshot.getValue(modelUser.class);
                mat.setText("MATRICULA:\n"+modelUser.getMat());
                nome.setText(modelUser.getNome());
                email.setText(modelUser.getEmail());
                sexo.setText(modelUser.getSexo());
                dataNasc.setText(modelUser.getDataNasc());
                nomeLoc = modelUser.getEmail().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openUser(View view) {
        Intent i = new Intent(DadosPessoais.this, User.class);
        startActivity(i);
    }
    public void updateData(View view)
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.updatepopup);
        TextView emailUp = dialog.findViewById(R.id.emailUpdate);
        emailUp.setText(email.getText().toString());
        Button reAuth = dialog.findViewById(R.id.reAuth);
        EditText password = dialog.findViewById(R.id.passwordUp);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        reAuth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AuthCredential credential = EmailAuthProvider
                        .getCredential(nomeLoc,password.getText().toString());

                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    user.updateEmail(email.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if(task.isSuccessful()){
                                                        Map<String, Object> map = new HashMap<>();
                                                        map.put("nome", nome.getText().toString());
                                                        map.put("dataNasc",dataNasc.getText().toString());
                                                        map.put("sexo", sexo.getText().toString());
                                                        map.put("email", email.getText().toString());
                                                        userdb.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                dialog.dismiss();
                                                                Toast.makeText(DadosPessoais.this,"Mudanças aplicadas com sucesso!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                Toast.makeText(DadosPessoais.this,"Erro!", Toast.LENGTH_SHORT).show();
                                                            }
                                                        });
                                                        Toast.makeText(DadosPessoais.this,"Mudanças aplicadas com sucesso!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(DadosPessoais.this,"Erro!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                }


        });

        dialog.show();

        /*
        Map<String, Object> map = new HashMap<>();
        map.put("nome", nome.getText().toString());
        map.put("dataNasc",dataNasc.getText().toString());
        map.put("sexo", sexo.getText().toString());
        userdb.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(DadosPessoais.this,"Mudanças aplicadas com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DadosPessoais.this,"Erro!", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void setEdit(View view){
        nome.setEnabled(true);
        email.setEnabled(true);
        sexo.setEnabled(true);
        dataNasc.setEnabled(true);
        saveEdit.setVisibility(View.VISIBLE);
        edit.setVisibility(View.GONE);
    }

    private void inicialize(){
        mat = findViewById(R.id.matUser);
        nome = findViewById(R.id.nomeEdit);
        nome.setEnabled(false);
        email = findViewById(R.id.emailEdit);
        email.setEnabled(false);
        sexo = findViewById(R.id.sexoEdit);
        sexo.setEnabled(false);
        dataNasc = findViewById(R.id.nascEdit);
        dataNasc.setEnabled(false);
        edit = findViewById(R.id.editButton);
        saveEdit = findViewById(R.id.saveEdit);
        saveEdit.setVisibility(View.GONE);


    }

}