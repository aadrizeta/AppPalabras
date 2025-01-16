package com.aadrizeta.RoomApp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PalabraViewHolder extends RecyclerView.ViewHolder {

    private final TextView palabraItemView;

    private PalabraViewHolder(View itemView){
        super(itemView);
        palabraItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text){
        palabraItemView.setText(text);
    }

    static PalabraViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new PalabraViewHolder(view);
    }

}
