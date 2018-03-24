package com.example.rosan.trivia;

/* Created by rosan on 16-3-2018. */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HighscoresHelper implements Response.Listener<ArrayList<Highscore>>, Response.ErrorListener {

    public interface Callback{
        void gotHighscores(ArrayList<Highscore> highscores);
        void gotError(String message);
    }

    private Context  context;
    private Callback cb;

    DatabaseReference names;
    FirebaseUser user;
    String finalScore;
    ArrayList<Highscore> scores;

    // Constructor
    HighscoresHelper(Context c){
        context = c;
    }

    public void getHighScores(Callback activity){

        // Retrieve the scores from DB and save them
        user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Create ArrayList with Highscore(s)
        scores = new ArrayList<>();

        database.getReference().child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snap : dataSnapshot.getChildren()){

                            // Extract necessary data: name, allScore, correctScore
                            String name = (String) snap.child("name").getValue();
                            String allScore = (String) snap.child("allScore").getValue();
                            String correctScore = (String) snap.child("correctScore").getValue();

                            // Add to scores if user has score(s)
                            if (allScore!=null){
                                Float all_int = Float.parseFloat(allScore);
                                Float corr_int = Float.parseFloat(correctScore);
                                finalScore = String.format("%.2f",corr_int/all_int);

                                // Check if user has score values
                                assert name != null;
                                assert correctScore != null;

                                // Create new highscore and add it to Highscores Arraylist
                                Highscore hs = new Highscore(name, allScore, correctScore, finalScore);
                                scores.add(hs);
                            }
                        }

                        // Sort scores (ascending)
                        Collections.sort(scores,new scoreComparator());
                        onResponse(scores);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("onCancelled", databaseError.toString());
                    }
                });

        // Notify activity
        cb = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        cb.gotError("something went wrong");
    }

    @Override
    public void onResponse(ArrayList<Highscore> response) {

        // Callback to HighscoreActivity with details
        if (response.isEmpty()){
            cb.gotError("something went wrong");
        } else {
            cb.gotHighscores(response);
        }
    }

    public class scoreComparator implements Comparator<Highscore>{

        @Override
        public int compare(Highscore score1, Highscore score2) {
            float fScore1 = Float.parseFloat(score1.getFinalScore());
            float fScore2 = Float.parseFloat(score2.getFinalScore());

            if(fScore1 > fScore2){
                return -1;
            } else if (fScore1 < fScore2){
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}
