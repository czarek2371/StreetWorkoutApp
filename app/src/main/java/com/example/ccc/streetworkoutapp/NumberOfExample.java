package com.example.ccc.streetworkoutapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccc.streetworkoutapp.Database.Database;
import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.Model.Number;
import com.example.ccc.streetworkoutapp.ViewHolder.NumberViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class NumberOfExample extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference exampleNumberList;
    FirebaseAuth auth;

    String ExamplesId = "";
    Database localDB;


    FirebaseRecyclerAdapter<Number, NumberViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_example);
        //Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        exampleNumberList = database.getReference("Number_Of_Example");

        //localDB
        localDB = new Database(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_number);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //get intent here
        if (getIntent() != null)
            ExamplesId = getIntent().getStringExtra("ExamplesId");
        if (!ExamplesId.isEmpty() && ExamplesId != null) {
            loadSetOfExcercises(ExamplesId);
        }
    }

    private void loadSetOfExcercises(String ExamplesId) {
        Query listExcervisesByExamplesId = exampleNumberList.orderByChild("numberId").equalTo(ExamplesId);
        FirebaseRecyclerOptions<Number> options = new FirebaseRecyclerOptions.Builder<Number>()
                .setQuery(listExcervisesByExamplesId, Number.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Number, NumberViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final NumberViewHolder viewHolder, final int position, @NonNull final Number model) {

                viewHolder.txtNumberOfPlan.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageNumberOfPlan);



                final Number local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //start new activity
                      /*Intent detailsOfExcercise = new Intent(NumberOfExample.this, DetailsOfExercise.class);
                        detailsOfExcercise.putExtra("ExerciseId", adapter.getRef(position).getKey()); //send exercise id to new activity
                        startActivity(detailsOfExcercise);*/

                    }
                });
            }

            @Override
            public NumberViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.example_number_layout, parent, false);
                return new NumberViewHolder(itemView);
            }

        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }
}
