package com.example.rosan.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomescreenActivity extends AppCompatActivity {


    DatabaseReference username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String email = user.getEmail();
            assert email != null;
            String name = email.substring(0, 3).toUpperCase();

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            username = database.getReference(user.getUid() + "/name");
            username.setValue(name);


        }
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

        // Go back to HomescreenActivity when back button is pressed
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
