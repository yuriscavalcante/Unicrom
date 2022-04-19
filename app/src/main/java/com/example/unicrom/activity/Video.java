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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.unicrom.R;
import com.example.unicrom.adapter.HomeAdapter;
import com.example.unicrom.model.modelCurso;
import com.example.unicrom.model.modelVideo;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

public class Video extends YouTubeBaseActivity {
    DatabaseReference dataBase = FirebaseDatabase.getInstance().getReference();
    List<String> urls = new ArrayList<String>();

    TextView testeId;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youTubePlayerView = findViewById(R.id.ytPlayer);
        testeId = findViewById(R.id.testeId);

        DatabaseReference reference = dataBase.child("url");








    }

    public void openHome(View view) {
        Intent i = new Intent(Video.this, Home.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference reference = dataBase.child("cursos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data: dataSnapshot.getChildren()){
                    String curso = data.child("url").getValue(String.class);

                    urls.add(curso);
                }

                //url = urls.get(0);
                testeId.setText(urls.get(0));
                //Reprodutor de video
                YouTubePlayer.OnInitializedListener listener = new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        //carrega o video pelo id no youtube
                        youTubePlayer.loadVideo(""+urls.get(0));
                        //Começa o video
                        youTubePlayer.play();
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Toast.makeText(getApplicationContext(),"Deu merda!", Toast.LENGTH_SHORT).show();
                    }
                    //inicializa o player

                };
                //inicializa o player
                youTubePlayerView.initialize("AIzaSyBJ3lPqgYwDAqPmYzs6YHdt5oC2dToO2UY", listener);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}