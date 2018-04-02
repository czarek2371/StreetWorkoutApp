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
import com.example.ccc.streetworkoutapp.Model.Skills;
import com.example.ccc.streetworkoutapp.ViewHolder.SkillsMainViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class SkillsMain extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference skillsMainReference;
    FirebaseAuth auth;

    String SkillId = "";
    Database localDB;


    FirebaseRecyclerAdapter<Skills, SkillsMainViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_main);
        //Firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        skillsMainReference = database.getReference("Skill_Select");

        //localDB
        localDB = new Database(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_skills_main);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (auth.getCurrentUser() != null)
            loadSkillsFromFireBase();


    }

    private void loadSkillsFromFireBase() {
        FirebaseRecyclerOptions<Skills> options = new FirebaseRecyclerOptions.Builder<Skills>()
                .setQuery(skillsMainReference, Skills.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Skills, SkillsMainViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SkillsMainViewHolder viewHolder, int position, @NonNull Skills model) {

                viewHolder.txtSkillsMainName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageSkillsMain);
                final Skills clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                 /*       Intent showSkills = new Intent(SkillsMain.this, SkillDetails.class);
                        //because setOfExercisesId is key we get key of this item
                        showSkills.putExtra("SkillId", adapter.getRef(position).getKey());
                        startActivity(showSkills);*/

                    }
                });
            }

            @Override
            public SkillsMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.skills_main_layout, parent, false);
                return new SkillsMainViewHolder(itemView);
            }

        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }
}
