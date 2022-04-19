package com.example.unicrom.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.unicrom.R;
import com.example.unicrom.model.modelCurso;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends FirebaseRecyclerAdapter<modelCurso, HomeAdapter.myViewHolder> {
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
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.cursopopup))
                        .setExpanded(true,1800)
                        .create();

                //dialogPlus.show();
                View viewer = dialogPlus.getHolderView();

                TextView tvPopUp = viewer.findViewById(R.id.tituloCurso);

                tvPopUp.setText(model.getCurso());
                dialogPlus.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_item,parent, false);
        return new myViewHolder(view);
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
            //cursoPopUp = (TextView)itemView.findViewById(R.id.tituloCurso);
        }
    }

}
