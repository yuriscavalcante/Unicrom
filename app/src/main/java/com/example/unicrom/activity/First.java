package com.example.unicrom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicrom.R;
import com.example.unicrom.adapter.HomeAdapter;

public class First extends AppCompatActivity {
    Button loginPage;
    public TextView tv;
    public String a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        inicializeComponent();
        Toast.makeText(this, ""+a, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //tv.setText(""+home.curso.get(0));
    }

    private void openLogin(View view) {
        Intent i = new Intent(First.this, Login.class);
        startActivity(i);
    }

    public void openHome(View view) {
        Intent i = new Intent(First.this, Home.class);
        startActivity(i);
    }

    private void inicializeComponent(){
        loginPage = findViewById(R.id.souAluno);
        tv = findViewById(R.id.testeNovo);
    }
}