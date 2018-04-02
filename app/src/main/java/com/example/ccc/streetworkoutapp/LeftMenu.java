package com.example.ccc.streetworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccc.streetworkoutapp.Interface.ItemClickListener;
import com.example.ccc.streetworkoutapp.Model.Training_Plans;
import com.example.ccc.streetworkoutapp.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class LeftMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    DatabaseReference training_Plans;
    private FirebaseAuth auth;

    TextView txtNameOfUser;

    RecyclerView mRecyclerMenu;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Training_Plans, MenuViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        //init firebase
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        training_Plans = database.getReference("Training_Plans");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set name for user
        View headerView = navigationView.getHeaderView(0);
        txtNameOfUser = (TextView) headerView.findViewById(R.id.txtNameOfUser);
        txtNameOfUser.setText(auth.getCurrentUser().getEmail());

        //Load menu

        mRecyclerMenu = (RecyclerView) findViewById(R.id.recycler_menu);
        mRecyclerMenu.setHasFixedSize(true);
        mRecyclerMenu.setLayoutManager(new LinearLayoutManager(this));

        if (auth.getCurrentUser() != null)
            loadMenuFromFireBase();

    }

    private void loadMenuFromFireBase() {
        FirebaseRecyclerOptions<Training_Plans> options = new FirebaseRecyclerOptions.Builder<Training_Plans>()
                .setQuery(training_Plans, Training_Plans.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Training_Plans, MenuViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder viewHolder, int position, @NonNull Training_Plans model) {

                viewHolder.txtTrainingPlansName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imageTrainingPlans);
                final Training_Plans clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //get trainings_plans id and send to new activity
                        Intent setOfExcercises = new Intent(LeftMenu.this, SetOfExercises.class);
                        //because setOfExercisesId is key we get key of this item
                        setOfExcercises.putExtra("Training_PlansId", adapter.getRef(position).getKey());
                        startActivity(setOfExcercises);

                    }
                });
            }

            @Override
            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.training_plans_view, parent, false);
                return new MenuViewHolder(itemView);
            }
        };
        adapter.startListening();
        mRecyclerMenu.setAdapter(adapter);
    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (exit){
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.left_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_example_plans) {
            startActivity(new Intent(LeftMenu.this, ExamplesOfTrainingPlans.class));

        } else if (id == R.id.nav_daily_nutritional) {
            startActivity(new Intent(LeftMenu.this, BMI.class));

        } else if (id == R.id.nav_skills) {
            startActivity(new Intent(LeftMenu.this, SkillsMain.class));

        } else if (id == R.id.nav_stretching) {
            startActivity(new Intent(LeftMenu.this, Stretching.class));

        } else if (id == R.id.nav_my_plan) {
            startActivity(new Intent(LeftMenu.this, FavoritesActivity.class));


        } else if (id == R.id.nav_sign_out) {
            auth.signOut();
            startActivity(new Intent(LeftMenu.this, MainActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
