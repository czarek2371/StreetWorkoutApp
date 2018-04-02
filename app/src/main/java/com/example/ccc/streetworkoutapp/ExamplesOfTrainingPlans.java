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
import com.example.ccc.streetworkoutapp.Model.Examples;
import com.example.ccc.streetworkoutapp.ViewHolder.ExampleViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ExamplesOfTrainingPlans extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference example_Plans;
    private FirebaseAuth auth;


    RecyclerView recycler_example;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Examples, ExampleViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examples_of_training_plans);

        //init firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        example_Plans = database.getReference("Examples");

        //Load menu

        recycler_example = (RecyclerView) findViewById(R.id.recycler_example);
        recycler_example.setHasFixedSize(true);
        recycler_example.setLayoutManager(new LinearLayoutManager(this));

        if (auth.getCurrentUser() != null)
            loadExamplesFromFireBase();


    }

    private void loadExamplesFromFireBase() {
        FirebaseRecyclerOptions<Examples> options = new FirebaseRecyclerOptions.Builder<Examples>()
                .setQuery(example_Plans, Examples.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Examples, ExampleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ExampleViewHolder viewHolder, int position, @NonNull Examples model) {

                viewHolder.txtExamplesPlansName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageExamplesPlans);
                final Examples clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        Intent numberOfExamples = new Intent(ExamplesOfTrainingPlans.this, NumberOfExample.class);
                        //because setOfExercisesId is key we get key of this item
                        numberOfExamples.putExtra("ExamplesId", adapter.getRef(position).getKey());
                        startActivity(numberOfExamples);

                    }
                });
            }


            @Override
            public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.examples_layout, parent, false);
                return new ExampleViewHolder(itemView);
            }
        };
        adapter.startListening();
        recycler_example.setAdapter(adapter);
    }


    }

