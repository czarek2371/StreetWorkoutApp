package com.example.ccc.streetworkoutapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.Model.Stretch;
import com.example.ccc.streetworkoutapp.ViewHolder.StretchingViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Stretching extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference stretching;
    private FirebaseAuth auth;


    RecyclerView recycler_stretching;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Stretch, StretchingViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretching);

        //init firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        stretching = database.getReference("Stretching");

        //Load menu

        recycler_stretching = (RecyclerView) findViewById(R.id.recycler_stretching);
        recycler_stretching.setHasFixedSize(true);
        recycler_stretching.setLayoutManager(new LinearLayoutManager(this));

        if (auth.getCurrentUser() != null)
            loadStretchingFromFireBase();


    }

    private void loadStretchingFromFireBase() {
        FirebaseRecyclerOptions<Stretch> options = new FirebaseRecyclerOptions.Builder<Stretch>()
                .setQuery(stretching, Stretch.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Stretch, StretchingViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull StretchingViewHolder viewHolder, int position, @NonNull Stretch model) {

                viewHolder.txtStretchingItemsName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageStretching);
                final Stretch clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                      /*  Intent stretchingExercises= new Intent(Stretching.this, StretchingDetails.class);
                        //because setOfExercisesId is key we get key of this item
                        stretchingExercises.putExtra("StretchingId", adapter.getRef(position).getKey());
                        startActivity(stretchingExercises);*/

                    }
                });
            }


            @Override
            public StretchingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.stretching_layout, parent, false);
                return new StretchingViewHolder(itemView);
            }
        };
        adapter.startListening();
        recycler_stretching.setAdapter(adapter);
    }


}

