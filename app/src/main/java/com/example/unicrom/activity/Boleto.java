package com.example.unicrom.activity;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unicrom.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.Locale;


public class Boleto extends AppCompatActivity {
    String userId, thisMes;
    private StorageReference mStorageReference;
    TextView nd;
    ImageView iv;
    EditText mes, ano;
    Button down, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boleto);
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        inicialize();




    }

    public void openUser(View view) {
        Intent i = new Intent(Boleto.this, User.class);
        startActivity(i);
    }

    public void download(View view)
    {
        mStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();

                downloadFiles(Boleto.this,mes.getText().toString(),"PNG",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void downloadFiles(Context context, String fileName, String fileExtension, String destinationDirectory, String url)
    {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

        downloadManager.enqueue(request);
    }


    public void searchBol(View view){
        mStorageReference = FirebaseStorage.getInstance().getReference().child("foto/"+userId+"/boleto/"+ano.getText().toString()+"/"+mes.getText().toString().toLowerCase()+".PNG");

        try {
            final File localFile = File.createTempFile(mes.getText().toString().toLowerCase(), "png");
            mStorageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    //Toast.makeText(Home.this, "Tudo Ok!", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    iv.setVisibility(View.VISIBLE);
                    down.setVisibility(View.VISIBLE);
                    iv.setImageBitmap(bitmap);
                    nd.setVisibility(View.GONE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    iv.setVisibility(View.GONE);
                    nd.setVisibility(View.VISIBLE);
                    //Toast.makeText(Boleto.this, thisMes, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void inicialize(){
        nd = findViewById(R.id.nadaTV);
        nd.setVisibility(View.GONE);
        iv = findViewById(R.id.bolView);
        iv.setVisibility(View.GONE);
        mes = findViewById(R.id.mesSea);
        ano = findViewById(R.id.anoSea);
        down = findViewById(R.id.downBol);
        down.setVisibility(View.GONE);
        search = findViewById(R.id.bolSea);
    }
}