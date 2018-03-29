package com.example.ccc.streetworkoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtLogin, edtPassword;
    Button btnLogin, btnNoAccount, btnIdkPassword;
    RelativeLayout activity_sign_in;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnNoAccount = (Button) findViewById(R.id.btnNoAccount);
        btnIdkPassword = (Button) findViewById(R.id.btnIdkPassword);
        activity_sign_in = (RelativeLayout) findViewById(R.id.activity_sign_in);

        btnLogin.setOnClickListener(this);
        btnNoAccount.setOnClickListener(this);
        btnIdkPassword.setOnClickListener(this);


        //Firebase auth
        auth = FirebaseAuth.getInstance();
// see if the user is currently signed in


        //check already session
        if (auth.getCurrentUser() != null)
            startActivity(new Intent(MainActivity.this, LeftMenu.class));


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnIdkPassword) {
            startActivity(new Intent(MainActivity.this, ForgotPassword.class));
            finish();
        } else if (v.getId() == R.id.btnNoAccount) {
            startActivity(new Intent(MainActivity.this, SignUp.class));
            finish();
        } else if (v.getId() == R.id.btnLogin) {
            String login = edtLogin.getText().toString();
            String password = edtPassword.getText().toString();
            if (TextUtils.isEmpty(login)) {
                Toast.makeText(MainActivity.this, "Wprowadź login", Toast.LENGTH_SHORT).show();

            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Wprowadź hasło", Toast.LENGTH_SHORT).show();

            } else {
                loginUser(login, password);
            }


        }

    }

    private void loginUser(final String login, final String password) {
        auth.signInWithEmailAndPassword(login, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(MainActivity.this, "Hasło musi mieć przynajmniej niż 6 znaków", Toast.LENGTH_SHORT).show();

                            } else if (password.length() > 5) {
                                Toast.makeText(MainActivity.this, "Błędne dane logowania", Toast.LENGTH_SHORT).show();


                            }
                        } else {
                            startActivity(new Intent(MainActivity.this, LeftMenu.class));


                        }

                    }

                });
    }
}