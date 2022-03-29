package com.example.unicrom.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unicrom.R;
import com.example.unicrom.adapter.HomeAdapter;
import com.example.unicrom.model.modelCurso;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    private TextView userName, userMat;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;
    RecyclerView rcView;
    HomeAdapter ha;
    private StorageReference mStorageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        inicialize();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("foto/"+userId+"/avatar.png");
        rcView = (RecyclerView)findViewById(R.id.listaCurso);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modelCurso> options =
                new FirebaseRecyclerOptions.Builder<modelCurso>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("alunos/"+userId+"/cursos"), modelCurso.class)
                        .build();

        ha = new HomeAdapter(options);
        rcView.setAdapter(ha);

        try {
            final File localFile = File.createTempFile("avatar", "png");
            mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Home.this, "Tudo Ok!", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ((ImageView)findViewById(R.id.avatarcircle)).setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Home.this, "Erro!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        DocumentReference documentReference = db.collection("aluno").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot!=null){
                    userName.setText("Olá "+documentSnapshot.getString("nome"));
                    userMat.setText("ID: "+documentSnapshot.getString("matricula"));
                }

            }
        });

        ha.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ha.stopListening();
    }

    private void inicialize(){
       userName = findViewById(R.id.nomeUser);
       userMat = findViewById(R.id.matUser);



}

}