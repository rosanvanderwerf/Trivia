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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        findViewById(R.id.create_account).setOnClickListener(this);
        findViewById(R.id.signin).setOnClickListener(this);
    }

    private void userLogin(){
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



        // User Log in
        mAuth.signInWithEmailAndPassword(email_str, password_str)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, go to HomescreenActivity
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this, HomescreenActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                        } else {

                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_account:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.signin:
                userLogin();
                break;

        }
    }
}
