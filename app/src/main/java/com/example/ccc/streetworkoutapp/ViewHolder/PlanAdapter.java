package com.example.ccc.streetworkoutapp.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.ccc.streetworkoutapp.Common.Common;
import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.Model.Plan;
import com.example.ccc.streetworkoutapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by ccc on 19.03.2018.
 */


public class PlanAdapter extends RecyclerView.Adapter<PlanViewHolder> {
    private List<Plan> listData = new ArrayList<>();
    private Context context;

    public PlanAdapter(List<Plan> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public PlanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.adding_to_plan, parent, false);
        return new PlanViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlanViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound("" + listData.get(position).getSeries(), Color.RED);



        String reps = (Integer.parseInt(listData.get(position).getSeries())) + " X " + (Integer.parseInt(listData.get(position).getReps()));
        holder.txt_price.setText(reps);
        holder.txt_plan_name.setText(listData.get(position).getExerciseName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}

