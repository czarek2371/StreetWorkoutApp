package com.example.ccc.streetworkoutapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.R;

/**
 * Created by ccc on 02.04.2018.
 */

public class StretchingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtStretchingItemsName;
    public ImageView imageStretching;

    private ItemClickListener itemClickListener;

    public StretchingViewHolder(View itemView) {
        super(itemView);

        txtStretchingItemsName = (TextView) itemView.findViewById(R.id.stretching_name);
        imageStretching = (ImageView) itemView.findViewById(R.id.stretching_image);

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
