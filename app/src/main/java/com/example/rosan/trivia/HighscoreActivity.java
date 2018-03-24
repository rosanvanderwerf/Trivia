package com.example.rosan.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity implements HighscoresHelper.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Create new request
        HighscoresHelper request = new HighscoresHelper(this);
        request.getHighScores(this);
    }

    @Override
    public void gotHighscores(ArrayList<Highscore> highscores) {
        ListView scores = findViewById(R.id.scores);

        HighscoresAdapter adapter = new HighscoresAdapter(this, highscores);
        scores.setAdapter(adapter);
    }

    @Override
    public void gotError(String message) {
        Log.d("gotError", message);
    }
}
