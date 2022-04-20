package com.example.unicrom.adapter;



import android.content.Intent;
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
import com.example.unicrom.activity.First;
import com.example.unicrom.activity.Home;
import com.example.unicrom.model.modelCurso;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends FirebaseRecyclerAdapter<modelCurso, HomeAdapter.myViewHolder> {

    Home home = new Home();
    First first = new First();

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public HomeAdapter(@NonNull FirebaseRecyclerOptions<modelCurso> options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull modelCurso model) {
        holder.curso.setText(model.getCurso());
        holder.prof.setText(model.getProf());
        holder.id.setText(model.getId());
        //holder.tv.setText(model.getCurso());

        //holder.cursoPopUp.setText(model.getCurso());



        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);



        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TextView tv = view.findViewById(R.id.testeNovo);
                home.curso = model.getCurso();

                if (home.curso != null && home.curso != "") {
                    view.getContext().startActivity(new Intent(view.getContext(), First.class));
                }




                //home.curso = model.getCurso().toString();

                //first.tv.setText(model.getCurso());



                //home.openCurso(view);
            /*
            //Abre o popUp

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cursopopup))
                        .setExpanded(true,2200)
                        .create();

                //dialogPlus.show();
                View viewer = dialogPlus.getHolderView();

                TextView tvPopUp = viewer.findViewById(R.id.tituloCurso);

                tvPopUp.setText(model.getCurso());


                dialogPlus.show();*/


            }
        });
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_item,parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        myViewHolder rcv = new myViewHolder((view));
        return rcv;
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView curso, prof, id;
        CircleImageView img;
        RelativeLayout rl;





        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            curso = (TextView)itemView.findViewById(R.id.nomeCurso);
            prof = (TextView)itemView.findViewById(R.id.cursoProf);
            id = (TextView)itemView.findViewById(R.id.cursoId);
            img = (CircleImageView)itemView.findViewById(R.id.cursoAvatar);
            rl = (RelativeLayout)itemView.findViewById(R.id.cursoCard);
            //


            //cursoPopUp = (TextView)itemView.findViewById(R.id.tituloCurso);
        }
    }

}
