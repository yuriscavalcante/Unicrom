package com.example.unicrom.activity;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
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
    TextView nd, mesSea, anoSea;
    ImageView iv;
    Button down, search, bolConfirm;
    String mes, ano;
    RelativeLayout messelect;

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

                downloadFiles(Boleto.this,mes.toString(),".PNG",DIRECTORY_DOWNLOADS,url);
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
        mStorageReference = FirebaseStorage.getInstance().getReference().child("foto/"+userId+"/boleto/"+ano+"/"+mes.toLowerCase()+".PNG");

        try {
            final File localFile = File.createTempFile(mes.toLowerCase(), "png");
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
    public void openSelectMesBol(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an animal");

// add a list
        String[] animals = {"JANEIRO", "FEVEREIRO", "MARÇO", "ABRIL", "MAIO", "JUNHO", "JULHO",
                "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DEZEMBRO"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        mes = "JANEIRO";
                        mesSea.setText(mes);
                        break;
                    case 1:
                        mes = "FEVEREIRO";
                        mesSea.setText(mes);
                        break;
                    case 2:
                        mes = "MARÇO";
                        mesSea.setText(mes);
                        break;
                    case 3:
                        mes = "ABRIL";
                        mesSea.setText(mes);
                        break;
                    case 4:
                        mes = "MAIO";
                        mesSea.setText(mes);
                        break;
                    case 5:
                        mes = "JUNHO";
                        mesSea.setText(mes);
                        break;
                    case 6:
                        mes = "JULHO";
                        mesSea.setText(mes);
                        break;
                    case 7:
                        mes = "AGOSTO";
                        mesSea.setText(mes);
                        break;
                    case 8:
                        mes = "SETEMBRO";
                        mesSea.setText(mes);
                        break;
                    case 9:
                        mes = "OUTUBRO";
                        mesSea.setText(mes);
                        break;
                    case 10:
                        mes = "NOVEMBRO";
                        mesSea.setText(mes);
                        break;
                    case 11:
                        mes = "DEZEMBRO";
                        mesSea.setText(mes);
                        break;
                }
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();

        dialog.show();

    }


    public void openSelectAnoBol(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an animal");

// add a list
        String[] animals = {"2022", "2023", "2024", "2025"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        ano = "2022";
                        anoSea.setText(ano);
                        break;
                    case 1:
                        ano = "2023";
                        anoSea.setText(ano);
                        break;
                    case 2:
                        ano = "2024";
                        anoSea.setText(ano);
                        break;
                    case 3:
                        ano = "2025";
                        anoSea.setText(ano);
                        break;

                }
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();

        dialog.show();

    }




    private void inicialize(){
        nd = findViewById(R.id.nadaTV);
        nd.setVisibility(View.GONE);
        iv = findViewById(R.id.bolView);
        iv.setVisibility(View.GONE);
        mesSea = findViewById(R.id.mesSea);
        anoSea = findViewById(R.id.anoSea);
        down = findViewById(R.id.downBol);
        down.setVisibility(View.GONE);
        search = findViewById(R.id.bolSea);




    }
}