package com.example.unicrom.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.unicrom.R;

public class First extends AppCompatActivity {
    Button firstTela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        inicializeComponent();
    }
    public void openLogin(View view) {
        Intent i = new Intent(First.this, Login.class);
        startActivity(i);
    }


    private void inicializeComponent(){

        firstTela = findViewById(R.id.buttonSouAluno);

    }
}