package com.example.rosan.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomescreenActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String email = user.getEmail();
            assert email != null;
            String name = email.substring(0, 4).toUpperCase();

            // Set
            TextView home = findViewById(R.id.home);
            home.setText("Hello, "+name+"!");

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            // Give the user an ID and generated name
            DatabaseReference userID = database.getReference("users");
            DatabaseReference username = database.getReference("users/"+user.getUid()+"/name");
            //userID.setValue(user.getUid());
            username.setValue(name);

            Toast.makeText(this, "Welcome "+name+"!", Toast.LENGTH_SHORT).show();
        }

        /* Wil nog een opties: uitloggen toevoegen */
    }

    public void toGameActivity(View view) {
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    public void toLeaderboardActivity(View view) {
        Intent intent = new Intent(this,HighscoreActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Log out
        mAuth.signOut();

        // Notify user
        Toast.makeText(this, "You are signed out", Toast.LENGTH_SHORT).show();

        // To LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void signOut(View view) {

        // Log out
        mAuth.signOut();

        // Notify user
        Toast.makeText(this, "You are signed out", Toast.LENGTH_SHORT).show();

        // To LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
}
