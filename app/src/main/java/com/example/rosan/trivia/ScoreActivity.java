package com.example.rosan.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    public ArrayList<Highscore> scores;

    DatabaseReference cScore;
    DatabaseReference aScore;
    String a_str;
    String b_str;
    Integer a_int;
    Integer b_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        final Integer correctAnswers = intent.getIntExtra("correct",0);
        final Integer allAnswers = intent.getIntExtra("all",0);

        // Find Views
        TextView cA = findViewById(R.id.correct);
        TextView aA = findViewById(R.id.all);

        // Set values
        cA.setText(String.valueOf(correctAnswers));
        aA.setText(String.valueOf(allAnswers));

        // Get CurrentUser and change scores in DB
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        assert user != null;
        cScore = database.getReference(user.getUid() + "/correctScore");
        aScore = database.getReference(user.getUid() + "/allScore");

        // Read data from DB
        cScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                a_str = dataSnapshot.getValue(String.class);
                if(a_str==null){

                    // First score: write to DB
                    cScore.setValue(String.valueOf(correctAnswers));
                } else {

                    // Add current (new) score to saved score
                    a_int = Integer.parseInt(a_str);
                    cScore.setValue(String.valueOf(correctAnswers + a_int));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error", "onCancelled");
            }
        });

        aScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b_str = dataSnapshot.getValue(String.class);
                if(b_str==null){

                    // First score: write to DB
                    aScore.setValue(String.valueOf(allAnswers));
                } else {

                    // Add current (new) score to saved score
                    b_int = Integer.parseInt(b_str);
                    aScore.setValue(String.valueOf(allAnswers + b_int));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error", "onCancelled");
            }
        });

        // Create (new) high score and add it to the ArrayList<Highscore>
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Go back to HomescreenActivity when back button is pressed
        Intent intent = new Intent(this, HomescreenActivity.class);
        startActivity(intent);
    }

    public void toHomescreenActivity(View view) {

        // Go back to HomeScreenActivity
        Intent intent = new Intent(this, HomescreenActivity.class);
        startActivity(intent);
    }
}
