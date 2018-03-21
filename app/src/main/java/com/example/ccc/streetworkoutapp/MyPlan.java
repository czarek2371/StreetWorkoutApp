package com.example.ccc.streetworkoutapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccc.streetworkoutapp.Database.Database;
import com.example.ccc.streetworkoutapp.Model.Plan;
import com.example.ccc.streetworkoutapp.Model.SavePlan;
import com.example.ccc.streetworkoutapp.ViewHolder.PlanAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyPlan extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference savePlans;

    Button btnPlace;

    List<Plan> myplan = new ArrayList<>();

    PlanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_plan);
        database = FirebaseDatabase.getInstance();


        savePlans = database.getReference("SavePlan");


        recyclerView = (RecyclerView) findViewById(R.id.listMyPlan);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        btnPlace = (Button) findViewById(R.id.btnPlaceOrder);

        btnPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialog();
            }

        });

        loadPlanList();


    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyPlan.this);
        alertDialog.setTitle("Utwórz własny Plan!");
        alertDialog.setMessage("xd: ");

        final EditText edtPlanName = new EditText(MyPlan.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtPlanName.setLayoutParams(lp);
        alertDialog.setView(edtPlanName); //add edit text to alert dialog
        alertDialog.setIcon(R.drawable.ic_favorite_border_black_24dp);


        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //create new saveplan
                SavePlan request = new SavePlan(
                        edtPlanName.getText().toString(),
                        myplan
                );
                //sumbit to firebase
                savePlans.child(String.valueOf(System.currentTimeMillis()))
                        .setValue(request);
                //delete Plan lsit
                new Database(getBaseContext()).cleanFromMyPlan();
                Toast.makeText(MyPlan.this, "Plan został zapisany", Toast.LENGTH_SHORT).show();
                finish();


            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }

                private void loadPlanList () {
                    myplan = new Database(this).getMyPlanList();
                    adapter = new PlanAdapter(myplan, this);
                    recyclerView.setAdapter(adapter);

                }


            }
