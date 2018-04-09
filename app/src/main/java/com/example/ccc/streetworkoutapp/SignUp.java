package com.example.ccc.streetworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail, edtPassword;
    Button btnSignUp, btnAlreadyHaveAcc;
    RelativeLayout activity_sign_up;

    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
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
            String login = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            if (TextUtils.isEmpty(login)) {
                Toast.makeText(SignUp.this, "Wprowadź login", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignUp.this, "Wprowadź hasło", Toast.LENGTH_SHORT).show();

            } else {
                signUpUser(login, password);
            }


        }
    }

    private void signUpUser(String login, final String password) {
        auth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(SignUp.this, "Hasło musi mieć przynajmniej niż 6 znaków", Toast.LENGTH_SHORT).show();

                            } else if (password.length() > 5) {
                                Toast.makeText(SignUp.this, "Błędne dane.\nPrzykładowy adres email: Kowalski@gmail.com", Toast.LENGTH_LONG).show();


                            }
                        } else {
                            Toast.makeText(SignUp.this, "Konto zostało utworzon", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
