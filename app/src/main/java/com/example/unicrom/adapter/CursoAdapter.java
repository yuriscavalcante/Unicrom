package com.example.unicrom.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unicrom.model.modelModulo;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CursoAdapter extends FirebaseRecyclerAdapter<modelModulo,ModuloAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CursoAdapter(@NonNull FirebaseRecyclerOptions<modelModulo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ModuloAdapter.myViewHolder holder, int position, @NonNull modelModulo model) {

    }

    @NonNull
    @Override
    public ModuloAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
}
