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
import com.example.ccc.streetworkoutapp.Model.Details_Of_Examples;
import com.example.ccc.streetworkoutapp.ViewHolder.DetailsExampleViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ExampleDetails extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference details_of_examples;
    private FirebaseAuth auth;

    String DetailsExamplesId = "";
    Database localDB;


    FirebaseRecyclerAdapter<Details_Of_Examples, DetailsExampleViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_details);

        //Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        details_of_examples = database.getReference("Details_Of_Examples");

        //localDB
        localDB = new Database(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_example_details);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        //get intent here
        if (getIntent() != null)
            DetailsExamplesId = getIntent().getStringExtra("NumberOfExampleId");
        if (!DetailsExamplesId.isEmpty() && DetailsExamplesId != null) {
            loadSetOfExampleDetails(DetailsExamplesId);
        }
    }

    private void loadSetOfExampleDetails(String DetailsExamplesId) {
        Query listExcercisesByExamplesId = details_of_examples.orderByChild("exampleId").equalTo(DetailsExamplesId);
        FirebaseRecyclerOptions<Details_Of_Examples> options = new FirebaseRecyclerOptions.Builder<Details_Of_Examples>()
                .setQuery(listExcercisesByExamplesId, Details_Of_Examples.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Details_Of_Examples, DetailsExampleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final DetailsExampleViewHolder viewHolder, final int position, @NonNull final Details_Of_Examples model) {

                viewHolder.txtExampleDetail.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageExampledetail);



                final Details_Of_Examples local = model;
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
            public DetailsExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.example_details_view, parent, false);
                return new DetailsExampleViewHolder(itemView);
            }

        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }
}
