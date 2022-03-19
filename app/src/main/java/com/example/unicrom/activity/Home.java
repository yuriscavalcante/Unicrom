package com.example.unicrom.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.unicrom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Home extends AppCompatActivity {
    private TextView userName, userMat, academico;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;
    boolean visible = true;
    ConstraintLayout expanded, expandedAdm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        inicialize();
    }

    @Override
    protected void onStart() {
        super.onStart();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("aluno").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot!=null){
                    userName.setText(documentSnapshot.getString("nome"));
                    userMat.setText(documentSnapshot.getString("matricula"));
                }

            }
        });
        expanded.setVisibility(View.GONE);
        expandedAdm.setVisibility(View.GONE);
    }
    public void setVisible(View view){
        //expanded.setVisibility(visible ? View.VISIBLE: View.GONE);
        if(visible == true){
            expanded.setVisibility(View.GONE);
            visible = false;
        }else{
            expanded.setVisibility(View.VISIBLE);
            visible = true;
        }
    }

    public void setVisibleAdm(View view){
        //expanded.setVisibility(visible ? View.VISIBLE: View.GONE);
        if(visible == true){
            expandedAdm.setVisibility(View.GONE);
            visible = false;
        }else{
            expandedAdm.setVisibility(View.VISIBLE);
            visible = true;
        }
    }

    private void inicialize(){
        academico = findViewById(R.id.academicoView);
        userName = findViewById(R.id.textUserName);
        userMat = findViewById(R.id.textUserMat);
        expanded = findViewById(R.id.expandableLayout);
        expandedAdm = findViewById(R.id.expandableLayoutAdm);
    }
}