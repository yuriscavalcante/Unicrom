package com.example.unicrom.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unicrom.R;
import com.example.unicrom.adapter.HomeAdapter;
import com.example.unicrom.model.modelCurso;
import com.example.unicrom.model.modelLoginUser;
import com.example.unicrom.model.modelUser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

public class User extends AppCompatActivity {
    private TextView userName, userEmail;
    private StorageReference mStorageReference;
    String userId;
    DatabaseReference userdb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();//pegar id de usuario
        userdb = FirebaseDatabase.getInstance().getReference().child("alunos").child(userId);

        inicialize();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("foto/"+userId+"/avatar.png");
        try {
            final File localFile = File.createTempFile("avatar", "png");
            mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(User.this, "Tudo Ok!", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ((ImageView)findViewById(R.id.avatarcircle)).setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(User.this, "Erro!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelLoginUser modelLoginUser = snapshot.getValue(modelLoginUser.class);
                userName.setText(modelLoginUser.getNome());
                userEmail.setText(modelLoginUser.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void openSearch(View view) {
        Intent i = new Intent(User.this, Search.class);
        startActivity(i);
    }

    public void openHome(View view) {
        Intent i = new Intent(User.this, Home.class);
        startActivity(i);
    }

    public void openVideo(View view) {
        Intent i = new Intent(User.this, Video.class);
        startActivity(i);
    }

    private void inicialize(){
        userName = findViewById(R.id.nomeUsu);
        userEmail = findViewById(R.id.emailUsu);
    }


}