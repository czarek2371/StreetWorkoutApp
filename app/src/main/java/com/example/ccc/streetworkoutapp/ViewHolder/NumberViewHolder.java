package com.example.ccc.streetworkoutapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.R;

/**
 * Created by ccc on 29.03.2018.
 */


public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtNumberOfPlan;
    public ImageView imageNumberOfPlan;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public NumberViewHolder(View itemView) {
        super(itemView);

        txtNumberOfPlan = (TextView) itemView.findViewById(R.id.example_plan_number);
        imageNumberOfPlan = (ImageView) itemView.findViewById(R.id.example_numer_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}


