package com.example.ccc.streetworkoutapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.ccc.streetworkoutapp.Model.Number;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NumberOfExampleDetails extends AppCompatActivity {


    ImageView img_number_plan;



    Number currentNumber;

    String numberId = "";

    FirebaseDatabase database;
    DatabaseReference exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_example_details);

        //firebase
        database = FirebaseDatabase.getInstance();
        exercises = database.getReference("Number_Of_Example");


        //Init view

        img_number_plan = (ImageView) findViewById(R.id.img_number_plan);



        //get food id from intent
        if (getIntent() != null)
            numberId = getIntent().getStringExtra("NumberId");
        if (!numberId.isEmpty()) {
            getDetailsOfNumber(numberId);
        }


    }

    private void getDetailsOfNumber(final String numberId) {
        exercises.child(numberId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentNumber = dataSnapshot.getValue(Number.class);

                //set image
                Picasso.with(getBaseContext()).load(currentNumber.getImage())
                        .into(img_number_plan);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
