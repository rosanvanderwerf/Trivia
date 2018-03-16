package com.example.rosan.trivia;

/* Created by rosan on 15-3-2018. */

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TriviaHelper implements Response.Listener<JSONArray>, Response.ErrorListener{

    public Context context;
    public Callback cb;

    public interface Callback{
        void gotQuestion(Question question);
        void gotError(String message);
    }

    // Constructor
    TriviaHelper(Context c){
        context = c;
    }

    public void getNextQuestion(Callback activity){
        /* Retrieve (next) question from the API */
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        String url = "http://jservice.io/api/random";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,this,this);
        queue.add(request);

        // Notify GameActivity
        cb = activity;
    }

    @Override
    public void onResponse(JSONArray response) {
        for (int i=0;i<response.length();i++){
            try {
                JSONObject object = response.getJSONObject(i);

                // Retrieve question and answer
                String sQquestion = object.getString("question");
                //ArrayList<String> answers = object
                String sAnswer = object.getString("answer");

                // Retrieve category
                JSONObject oCategory = object.getJSONObject("category");
                String sCategory = oCategory.getString("title");

                Question question = new Question(sQquestion,sAnswer,sCategory);

                cb.gotQuestion(question);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // Among other things: an error message
        Log.d("error", "error");
        cb.gotError("onErrorResponse: retrieval unaccomplished");
    }




}
