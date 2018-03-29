package com.example.ccc.streetworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText edtLogin, edtPassword, edtEmail;
    Button btnSignUp, btnAlreadyHaveAcc;
    RelativeLayout activity_sign_up;

    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnAlreadyHaveAcc = (Button) findViewById(R.id.btnAlreadyHaveAcc);
        activity_sign_up = (RelativeLayout) findViewById(R.id.activity_sign_up);


        btnSignUp.setOnClickListener(this);
        btnAlreadyHaveAcc.setOnClickListener(this);


        //Init Firebase
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAlreadyHaveAcc) {
            startActivity(new Intent(SignUp.this, MainActivity.class));
            finish();
        } else if (v.getId() == R.id.btnSignUp) {
            signUpUser(edtLogin.getText().toString(),edtPassword.getText().toString());


        }
    }

    private void signUpUser(String login, String password) {
        auth.createUserWithEmailAndPassword(login,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                        {
                            snackbar = Snackbar.make(activity_sign_up,"Błąd: " +task.getException(),Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else {
                            snackbar = Snackbar.make(activity_sign_up,"Konto zostało utworzone",Snackbar.LENGTH_SHORT);
                            snackbar.show();

                        }
                    }
                });
    }
}
