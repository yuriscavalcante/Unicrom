package com.example.unicrom.adapter;



import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unicrom.R;
import com.example.unicrom.model.modelModulo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ModuloAdapter extends FirebaseRecyclerAdapter<modelModulo,ModuloAdapter.myViewHolder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     **/
    public ModuloAdapter(@NonNull FirebaseRecyclerOptions<modelModulo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull modelModulo model) {
       holder.titulo.setText(model.getTitulo());
       //holder.url.setText(model.getUrl());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl())));
                /*final DialogPlus dialogPlus = DialogPlus.newDialog(holder.rl.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cursopopup))
                        .setExpanded(true,2200)
                        .create();


                View viewer = dialogPlus.getHolderView();
                TextView tvPopUp = viewer.findViewById(R.id.tituloCurso);
                tvPopUp.setText(model.getTitulo());




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
                        //Toast.makeText(getApplicationContext(),"Deu merda!", Toast.LENGTH_SHORT).show();
                    }
                    //inicializa o player

                };
                //inicializa o player


                holder.youTubePlayerView.initialize("AIzaSyBJ3lPqgYwDAqPmYzs6YHdt5oC2dToO2UY", listener);
                dialogPlus.show();*/

            }
        });



    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.modulo_item,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, url;
        RelativeLayout rl;
        YouTubePlayerView youTubePlayerView;
        //YouTubePlayerView videoAula;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            titulo = (TextView)itemView.findViewById(R.id.nome);
            //videoAula = (YouTubePlayerView)itemView.findViewById(R.id.videoAula);
            //url = (TextView)itemView.findViewById(R.id.testeIdVideo);
            rl = (RelativeLayout)itemView.findViewById(R.id.modRL);
            youTubePlayerView = (YouTubePlayerView)itemView.findViewById(R.id.videoAula);

        }
    }

}
