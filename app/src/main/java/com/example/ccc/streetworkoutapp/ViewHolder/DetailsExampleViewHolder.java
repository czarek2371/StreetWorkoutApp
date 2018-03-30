package com.example.ccc.streetworkoutapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.R;

/**
 * Created by ccc on 30.03.2018.
 */

public class DetailsExampleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtExampleDetail;
    public ImageView imageExampledetail;

    private ItemClickListener itemClickListener;

    public DetailsExampleViewHolder(View itemView) {
        super(itemView);

        txtExampleDetail = (TextView) itemView.findViewById(R.id.example_details_item_name);
        imageExampledetail = (ImageView) itemView.findViewById(R.id.example_details_image);

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
