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

public class SkillsMainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtSkillsMainName;
    public ImageView imageSkillsMain;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SkillsMainViewHolder(View itemView) {
        super(itemView);

        txtSkillsMainName = (TextView) itemView.findViewById(R.id.skills_main_name);
        imageSkillsMain = (ImageView) itemView.findViewById(R.id.skills_main_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}

