package com.example.rosan.trivia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements  View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        // Find views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        findViewById(R.id.signup).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void registerUser(){
        String email_str = email.getText().toString().trim();
        String password_str = password.getText().toString().trim();

        if(email_str.isEmpty()){
            email.setError("username is required");
            email.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_str).matches()){
            email.setError("please enter valid email");
            email.requestFocus();
        }

        if (password_str.isEmpty()){
            password.setError("password is required");
            password.requestFocus();
        }

        if (password.length()<6){
            password.setError("password should be at least 6");
            password.requestFocus();
        }

        mAuth.createUserWithEmailAndPassword(email_str, password_str)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(SignUpActivity.this, "Authentication succes.",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(SignUpActivity.this, HomescreenActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {

                            // Check if e-mail already in use
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(SignUpActivity.this, "This e-mail is already registered.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.signup:
                //Toast.makeText(SignUpActivity.this, "signup clicked", Toast.LENGTH_SHORT).show();
                registerUser();
                break;

            // Intent to login
            case R.id.login:
                startActivity(new Intent(this, LoginActivity.class));
                break;

        }

    }
}
