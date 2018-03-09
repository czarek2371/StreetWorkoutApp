package com.example.ccc.streetworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.Model.Set;
import com.example.ccc.streetworkoutapp.ViewHolder.SetViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class SetOfExercises extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference excercisesList;

    String Training_PlansId = "";

    FirebaseRecyclerAdapter<Set, SetViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_of_exercises);

        //Firebase
        database = FirebaseDatabase.getInstance();
        excercisesList = database.getReference("Set_Of_Exercises");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_excercises);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if (getIntent() != null)
            Training_PlansId = getIntent().getStringExtra("Training_PlansId");
        if (!Training_PlansId.isEmpty() && Training_PlansId != null) {
            loadSetOfExcercises(Training_PlansId);
        }
    }

    private void loadSetOfExcercises(String Training_PlansId) {
        Query listExcervisesByTraining_PlansId = excercisesList.orderByChild("menuId").equalTo(Training_PlansId);
        FirebaseRecyclerOptions<Set> options = new FirebaseRecyclerOptions.Builder<Set>()
                .setQuery(listExcervisesByTraining_PlansId, Set.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Set, SetViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SetViewHolder viewHolder, int position, @NonNull Set model) {

                viewHolder.txtSetOfExercises.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageSetOfExercises);

                final Set local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //start new activity
                        Intent detailsOfExcercise = new Intent(SetOfExercises.this, DetailsOfExercise.class);
                        detailsOfExcercise.putExtra("ExerciseId", adapter.getRef(position).getKey()); //send exercise id to new activity
                        startActivity(detailsOfExcercise);

                    }
                });
            }

            @Override
            public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.set_of_exercises_view, parent, false);
                return new SetViewHolder(itemView);
            }

        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }
}
