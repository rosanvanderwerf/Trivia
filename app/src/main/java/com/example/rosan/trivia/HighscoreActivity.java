package com.example.rosan.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HighscoreActivity extends AppCompatActivity implements HighscoresHelper.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        // Create new request
        HighscoresHelper request = new HighscoresHelper(this);
        request.gotHightScores(this);

        // Adapter

        //HighscoresAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        //gridView.setAdapter(adapter);
    }

    @Override
    public void gotHighscores() {

    }

    @Override
    public void gotError(String message) {

    }
}
