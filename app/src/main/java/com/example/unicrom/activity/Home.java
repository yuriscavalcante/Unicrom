package com.example.unicrom.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.unicrom.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Home extends AppCompatActivity {
    private TextView userName, userMat;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;

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
    }

    private void inicialize(){
        userName = findViewById(R.id.textUserName);
        userMat = findViewById(R.id.textUserMat);
    }
}