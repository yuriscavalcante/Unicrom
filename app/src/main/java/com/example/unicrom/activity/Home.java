package com.example.unicrom.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unicrom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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
    private TextView userName, userMat, academico;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId;
    boolean visible = true;
    String url = "https://www.google.com/imgres?imgurl=https%3A%2F%2Flookaside.fbsbx.com%2Flookaside%2Fcrawler%2Fmedia%2F%3Fmedia_id%3D1843890325876754&imgrefurl=https%3A%2F%2Fpt-br.facebook.com%2Ffernandoportelapessoa%2F&tbnid=v8P_Fr4GjYNyUM&vet=12ahUKEwi3zbfKwNf2AhXjHLkGHb85AEYQMygDegUIARDcAQ..i&docid=bqhuRmcCfNwaMM&w=1080&h=1080&q=pessoa&ved=2ahUKEwi3zbfKwNf2AhXjHLkGHb85AEYQMygDegUIARDcAQ";
    RecyclerView recyclerView;
    private StorageReference mStorageReference;
    //private ImageView imageView;
    //List<Opcoes> opcoesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);
        //inicialize();
        //userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mStorageReference = FirebaseStorage.getInstance().getReference().child("/gs://unicrom-f4a54.appspot.com/foto/2NoPxvnjAVapRQkJVO6PMlN3JYN2/avatar.png");
        //recyclerView = findViewById(R.id.recycleView);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try {
            final File localFile = File.createTempFile("avatar","png");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Home.this,"Tudo Certo", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    ((ImageView)findViewById(R.id.logoView)).setImageBitmap(bitmap);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Home.this,"Erro", Toast.LENGTH_SHORT).show();
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
                    userMat.setText("Matricula: "+documentSnapshot.getString("matricula"));
                }

            }
        });

 /*       Glide.with(Home.this)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.square_placeholder).into(imageView);*/
    }

    private void inicialize(){
        userName = findViewById(R.id.textUserName);
        userMat = findViewById(R.id.textUserMat);
        //imageView = findViewById(R.id.avatarView);
    }
/*
    private void initData() {
        opcoesList = new ArrayList<>();

    }*/
}