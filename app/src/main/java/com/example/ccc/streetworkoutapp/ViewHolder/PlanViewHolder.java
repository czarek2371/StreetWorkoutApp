package com.example.ccc.streetworkoutapp.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.R;

/**
 * Created by ccc on 25.03.2018.
 */

public class PlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_plan_name,txt_price;

    public RelativeLayout view_background;
    public LinearLayout view_foreground;

    private ItemClickListener itemClickListener;

    public void setTxt_plan_name(TextView txt_plan_name) {
        this.txt_plan_name = txt_plan_name;
    }

    public PlanViewHolder(View itemView) {
        super(itemView);
        txt_plan_name = (TextView)itemView.findViewById(R.id.plan_item_name);
        txt_price = (TextView)itemView.findViewById(R.id.plan_item_price);
        view_background = (RelativeLayout)itemView.findViewById(R.id.view_background);
        view_foreground = (LinearLayout)itemView.findViewById(R.id.view_foreground);
    }

    @Override
    public void onClick(View v) {

    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        menu.setHeaderTitle("Select action");
//        menu.add(0,0,getAdapterPosition(), Common.DELETE);
//    }
}
