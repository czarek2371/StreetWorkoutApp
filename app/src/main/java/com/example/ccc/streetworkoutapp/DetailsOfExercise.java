package com.example.ccc.streetworkoutapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ccc.streetworkoutapp.Model.Set;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailsOfExercise extends AppCompatActivity {


    TextView exercise_name, exercise_description;
    ImageView img_exercise;
    CollapsingToolbarLayout collapsingToolbarLayout;


    Set currentExercise;

    String exerciseId = "";

    FirebaseDatabase database;
    DatabaseReference exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_exercise);

        //firebase
        database = FirebaseDatabase.getInstance();
        exercises = database.getReference("Set_Of_Exercises");


        //Init view
        exercise_name = (TextView) findViewById(R.id.exercise_name);
        exercise_description = (TextView) findViewById(R.id.exercise_description);
        img_exercise = (ImageView) findViewById(R.id.img_exercise);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.Widget_Design_CollapsingToolbar);

        //get food id from intent
        if (getIntent() != null)
            exerciseId = getIntent().getStringExtra("ExerciseId");
        if (!exerciseId.isEmpty()) {
            getDetailsOfExercise(exerciseId);
        }


    }

    private void getDetailsOfExercise(final String exerciseId) {
        exercises.child(exerciseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentExercise = dataSnapshot.getValue(Set.class);

                //set image
                Picasso.with(getBaseContext()).load(currentExercise.getImage())
                        .into(img_exercise);

                collapsingToolbarLayout.setTitle(currentExercise.getName());

               exercise_name.setText(currentExercise.getName());

                exercise_description.setText(currentExercise.getDescription());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
