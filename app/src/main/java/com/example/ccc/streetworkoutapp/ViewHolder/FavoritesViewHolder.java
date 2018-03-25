package com.example.ccc.streetworkoutapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.R;

/**
 * Created by ccc on 24.03.2018.
 */

public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtItemName;
    public ImageView imageOfExercise;

    private ItemClickListener itemClickListener;

    public RelativeLayout view_background;
    public LinearLayout view_foreground;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FavoritesViewHolder(View itemView) {
        super(itemView);


        txtItemName = (TextView) itemView.findViewById(R.id.plan_item_name);
        imageOfExercise = (ImageView) itemView.findViewById(R.id.training_plans_image);

        view_background = (RelativeLayout)itemView.findViewById(R.id.view_background);
        view_foreground = (LinearLayout) itemView.findViewById(R.id.view_foreground);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}
