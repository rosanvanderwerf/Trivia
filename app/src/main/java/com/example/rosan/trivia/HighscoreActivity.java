package com.example.rosan.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class HighscoreActivity extends AppCompatActivity implements HighscoresHelper.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Create new request
        HighscoresHelper request = new HighscoresHelper(this);
        request.getHightScores(this);

        // ArrayList<Highscores> scores
    }

    @Override
    public void getHighscores() {
        // Find ListView and set the adapter
    }

    @Override
    public void gotError(String message) {
        Log.d("error","gotError: getHighscores");
    }
}
