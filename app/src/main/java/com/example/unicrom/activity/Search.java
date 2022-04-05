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
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
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
import java.util.Locale;

public class Search extends AppCompatActivity {
    RecyclerView rcView;
    HomeAdapter ha;
    SearchView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        sv = findViewById(R.id.searchBar);

        rcView = (RecyclerView) findViewById(R.id.searchCurso);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<modelCurso> options =
                new FirebaseRecyclerOptions.Builder<modelCurso>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cursos"), modelCurso.class)
                        .build();

        ha = new HomeAdapter(options);
        rcView.setAdapter(ha);
    }


    public void openHome(View view) {
        Intent i = new Intent(Search.this, Home.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ha.startListening();

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchTxt(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchTxt(s);
                return false;
            }
        });
    }

    public void openUser(View view) {
        Intent i = new Intent(Search.this, User.class);
        startActivity(i);
    }

    private void searchTxt(String str){
        String teste;
        teste = str.toUpperCase(Locale.ROOT);
        FirebaseRecyclerOptions<modelCurso> options =
                new FirebaseRecyclerOptions.Builder<modelCurso>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("cursos").orderByChild("curso").startAt(teste).endAt(teste+"~"), modelCurso.class)
                        .build();

        ha = new HomeAdapter(options);
        ha.startListening();
        rcView.setAdapter(ha);

    }

    @Override
    protected void onStop() {
        super.onStop();
        ha.stopListening();
    }
}
