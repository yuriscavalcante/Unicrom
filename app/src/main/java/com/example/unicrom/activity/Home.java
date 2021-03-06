package com.example.unicrom.activity;

import androidx.annotation.NonNull;
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

import com.example.unicrom.R;
import com.example.unicrom.adapter.HomeAdapter;
import com.example.unicrom.adapter.ModuloAdapter;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Home extends AppCompatActivity {
    private TextView userName, userMat;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userId, nome;
    RecyclerView rcView, modView;
    HomeAdapter ha;
    ModuloAdapter ma;
    public String curso;
    private StorageReference mStorageReference;
    TextView tvPopUp;
    DatabaseReference dataBase, userdb;
    //public List<String> curso = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();//pegar id de usuario
        inicialize();//inicializar os componentes da tela
        //dataBase = FirebaseDatabase.getInstance().getReference().child("alunos").child(userId).child("curso");
        userdb = FirebaseDatabase.getInstance().getReference().child("alunos").child(userId);
        mStorageReference = FirebaseStorage.getInstance().getReference().child("foto/"+userId+"/avatar.png");//imagem do avatar
        //listar os cursos
        rcView = (RecyclerView)findViewById(R.id.listaCurso);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modelCurso> options =
                new FirebaseRecyclerOptions.Builder<modelCurso>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("alunos").child(userId).child("cursos"), modelCurso.class)
                        .build();

        ha = new HomeAdapter(options);

        rcView.setAdapter(ha);







        //Fun????o para puxar e dar "set" na imagem
        try {
            final File localFile = File.createTempFile("avatar", "png");
            mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    //Toast.makeText(Home.this, "Tudo Ok!", Toast.LENGTH_SHORT).show();
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


        userdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelUser user = snapshot.getValue(modelUser.class);
                modelLoginUser modelLoginUser = snapshot.getValue(modelLoginUser.class);
                userName.setText(user.getNome());
                userMat.setText("MATRICULA: "+user.getMat());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //"Setar" inform????es do aluno
        /*
        DocumentReference documentReference = db.collection("aluno").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot!=null){
                    userName.setText("Ol?? "+documentSnapshot.getString("nome"));
                    userMat.setText("ID: "+documentSnapshot.getString("matricula"));

                }

            }

        });*/


        //Come??o da leitura da lista
        ha.startListening();
        //ma.startListening();
        //curso = ha.curso;



       // ma.startListening();
    }

    public void openSearch(View view) {
        Intent i = new Intent(Home.this, Search.class);
        startActivity(i);
    }

    public void openUser(View view) {
        Intent i = new Intent(Home.this, User.class);
        startActivity(i);
    }

    public void openVideo(View view) {
        Intent i = new Intent(Home.this, Video.class);
        startActivity(i);
    }





    @Override
    protected void onStop() {
        super.onStop();
        //Final da leitura da lista
        ha.stopListening();
    }

    private void inicialize(){
       userName = findViewById(R.id.nomeUser);

       userMat = findViewById(R.id.matTesteUser);

    }


}