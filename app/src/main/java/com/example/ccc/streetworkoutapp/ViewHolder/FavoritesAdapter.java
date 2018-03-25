package com.example.ccc.streetworkoutapp.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ccc.streetworkoutapp.Common.Common;
import com.example.ccc.streetworkoutapp.DetailsOfExercise;
import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.Model.Favorites;
import com.example.ccc.streetworkoutapp.Model.SavePlan;
import com.example.ccc.streetworkoutapp.Model.Set;
import com.example.ccc.streetworkoutapp.R;
import com.example.ccc.streetworkoutapp.SetOfExercises;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ccc on 24.03.2018.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {

    private Context context;
    private List<Favorites> favoritesList;

    public FavoritesAdapter(Context context, List<Favorites> favoritesList) {
        this.context = context;
        this.favoritesList = favoritesList;
    }

    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.favorites_item,parent,false);
        return new FavoritesViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(FavoritesViewHolder viewHolder, int position) {
        viewHolder.txtItemName.setText(favoritesList.get(position).getExerciseName().toString());
        Picasso.with(context).load(favoritesList.get(position).getExerciseImage())
                .into(viewHolder.imageOfExercise);

        //add FAvorites


        final Favorites local = favoritesList.get(position);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //start new activity
                Intent detailsOfExcercise = new Intent(context, DetailsOfExercise.class);
                detailsOfExcercise.putExtra("ExerciseId", favoritesList.get(position).getExerciseId());
                context.startActivity(detailsOfExcercise);

            }
        });

    }

    @Override
    public int getItemCount() {
       return favoritesList.size();
    }

    public void removeItem(int position)
    {
        favoritesList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem (Favorites item, int position)
    {
        favoritesList.add(position,item);
        notifyItemRemoved(position);
    }
    public Favorites getItem(int position)
    {
        return favoritesList.get(position);
    }
}
