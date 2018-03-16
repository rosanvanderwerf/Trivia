package com.example.rosan.trivia;

/* Created by rosan on 16-3-2018. */

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class HighscoresHelper implements Response.Listener<JSONArray>, Response.ErrorListener {
    public Context context;
    public Callback cb;

    public interface Callback{
        void gotHighscores();
        void gotError(String message);
    }

    // Constructor
    HighscoresHelper(Context c){
        context = c;
    }

    public void gotHightScores(Callback activity){
        //RequestQueue queue = Volley.newRequestQueue((Context) activity);

        // Retrieve the scores from DB



        // Notify
        cb = activity;
    }




    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONArray response) {

    }


}
