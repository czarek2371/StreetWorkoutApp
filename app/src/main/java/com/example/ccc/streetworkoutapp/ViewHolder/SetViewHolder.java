package com.example.ccc.streetworkoutapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.R;

/**
 * Created by ccc on 06.03.2018.
 */

public class SetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtSetOfExercises;
    public ImageView imageSetOfExercises;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SetViewHolder(View itemView) {
        super(itemView);

        txtSetOfExercises = (TextView) itemView.findViewById(R.id.set_of_exercises_name);
        imageSetOfExercises = (ImageView) itemView.findViewById(R.id.set_of_exercises_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}
