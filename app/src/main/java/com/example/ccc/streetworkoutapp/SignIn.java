package com.example.ccc.streetworkoutapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ccc.streetworkoutapp.Common.Common;
import com.example.ccc.streetworkoutapp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtLogin, edtPassword;
    Button btnLogin, btnNoAccount,btnIdkPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnNoAccount = (Button) findViewById(R.id.btnNoAccount);
        btnIdkPassword = (Button) findViewById(R.id.btnIdkPassword);

//Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mProgressDialog = new ProgressDialog(SignIn.this);
                mProgressDialog.setMessage("Please wait...");
                mProgressDialog.show();

                // add Call Back for reponse from FireBase
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mProgressDialog.dismiss();
                        //check if user not exist in databes
                        if (dataSnapshot.child(edtLogin.getText().toString()).exists()) {

                            // Get User Information
                            mProgressDialog.dismiss();
                            User user = dataSnapshot.child(edtLogin.getText().toString()).getValue(User.class);

                            if (user.getPassword()!= null && user.getPassword().equals(edtPassword.getText().toString())){
                                {
                                    Intent leftMenuIntent = new Intent(SignIn.this,LeftMenu.class);
                                    Common.currentUser = user;
                                    startActivity(leftMenuIntent);
                                    finish();
                                }

                            } else {
                                Toast.makeText(SignIn.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignIn.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}