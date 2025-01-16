package com.aadrizeta.RoomApp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class PalabraListAdapter extends ListAdapter<Palabra, PalabraViewHolder> {

    private final PalabraViewModel palabraViewModel;

    public PalabraListAdapter(@NonNull DiffUtil.ItemCallback<Palabra> diffCallback, PalabraViewModel palabraViewModel) {
        super(diffCallback);
        this.palabraViewModel = palabraViewModel;
    }
    @Override
    public PalabraViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return PalabraViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(PalabraViewHolder holder, int position){
        Palabra current = getItem(position);
        holder.bind(current.getPalabra());

        ImageView deleteIcon = holder.itemView.findViewById(R.id.deleteIcon);

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                palabraViewModel.delete(current);
            }
        });
    }

    static class PalabraDiff extends DiffUtil.ItemCallback<Palabra>{
        @Override
        public boolean areItemsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Palabra oldItem, @NonNull Palabra newItem){
            return oldItem.getPalabra().equals(newItem.getPalabra());
        }
    }
}
