package com.example.rosan.trivia;

/* Created by rosan on 16-3-2018. */

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

public class HighscoresHelper implements Response.Listener<JSONArray>, Response.ErrorListener {
    private Context context;
    private Callback cb;

    DatabaseReference names;

    public interface Callback{
        void getHighscores();
        void gotError(String message);
    }

    // Constructor
    HighscoresHelper(Context c){
        context = c;
    }

    public void getHightScores(Callback activity){

        // Retrieve the scores from DB and save them

        // Notify activity
        cb = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("error","onErrorResponse: HighscoresHelper");
    }

    @Override
    public void onResponse(JSONArray response) {
        // Retrieve names and their (high)scores
        // Callback to HighscoreActivity with details
    }
}
