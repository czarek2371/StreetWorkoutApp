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

public class ExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtExamplesPlansName;
    public ImageView imageExamplesPlans;

    private ItemClickListener itemClickListener;

    public ExampleViewHolder(View itemView) {
        super(itemView);

        txtExamplesPlansName = (TextView) itemView.findViewById(R.id.example_plans_name);
        imageExamplesPlans = (ImageView) itemView.findViewById(R.id.example_plans_image);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }
}
