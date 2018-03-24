package com.example.rosan.trivia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;


import java.util.Random;

public class GameActivity extends AppCompatActivity implements TriviaHelper.Callback {

    String correctAnswer;
    Button answer;
    Integer storedCorrect;
    Integer storedAll;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Create new request
        TriviaHelper request = new TriviaHelper(this);
        request.getNextQuestion(this);

        // Save correctAnswers and allAnswers
        prefs = getSharedPreferences("settings", MODE_PRIVATE);
        storedCorrect = prefs.getInt("correctAnswers",0);
        storedAll = prefs.getInt("allAnswers", 0);

        // Find view(s) and set value
        TextView corrAs = findViewById(R.id.correctAnswers);
        TextView allAs = findViewById(R.id.allAnswers);

        corrAs.setText(String.valueOf(storedCorrect));
        allAs.setText(String.valueOf(storedAll));

        // Check if StoredAll == 10, and if so: go to Score activity (and maybe save it in FireBase)
        if (storedAll >= 10){

            // Save scores in new Intent and...
            Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
            intent.putExtra("correct", storedCorrect);
            intent.putExtra("all", storedAll);

            startActivity(intent);

            // ... reset storedCorrect and storedAll
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("correctAnswers", 0 );
            editor.putInt("allAnswers", 0);
            editor.apply();
        }
    }

    @Override
    public void gotQuestion(Question q) {

        // Find views
        TextView category = findViewById(R.id.category);
        TextView question = findViewById(R.id.question);
        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);

        // Choose a random button to put the correct answer in
        Button [] all = {answer1, answer2,answer3};

        Random random = new Random();
        answer = all[random.nextInt(all.length)];

        // Set the views
        category.setText(q.getCategory().toUpperCase());
        question.setText(q.getQuestion());
        answer.setText(q.getCorrectAnswer());

        // Save correctAnswer in String
        correctAnswer = q.getCorrectAnswer();
    }

    @Override
    public void gotError(String message) {

        // Log error
        Log.d("error", message);
    }

    public void checkAnswer(View view) {
        Button b = (Button)view;
        String chosenAnswer = b.getText().toString();

        if (chosenAnswer.equals(correctAnswer)){

            // Feedback: correct option is clicked
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            b.setTextColor(ContextCompat.getColor(this, R.color.buttonText));

            // Something to save the score
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("correctAnswers",storedCorrect + 1 );
            editor.putInt("allAnswers", storedAll + 1);
            editor.apply();

            // and display new question, by restarting the activity
            finish();
            startActivity(getIntent());
        } else {

            // Feedback: incorrect option is clicked
            b.setBackgroundColor(ContextCompat.getColor(this, R.color.incorrect));
            answer.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            answer.setTextColor(ContextCompat.getColor(this, R.color.buttonText));

            // Something to save the score
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("allAnswers", storedAll + 1);
            editor.apply();

            // and display new question
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Go back to HomescreenActivity when back button is pressed
        startActivity(new Intent(GameActivity.this, HomescreenActivity.class));
    }
}
