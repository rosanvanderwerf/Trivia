package com.example.rosan.trivia;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rosan.trivia.Highscore;
import com.example.rosan.trivia.R;

import java.util.ArrayList;

/* Created by rosan on 16-3-2018. */

public class HighscoresAdapter extends ArrayAdapter<Highscore> {

    public Context context;
    public ArrayList scores;

    public HighscoresAdapter(Context context, ArrayList<Highscore> scores) {
        super(context,0, scores);
        this.context = context;
        this.scores = scores;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, @Nullable View v, @NonNull ViewGroup p) {
        Highscore score = getItem(i);

        // Inflate view
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_item, p,false);
        }

        TextView name = v.findViewById(R.id.name);
        TextView details = v.findViewById(R.id.details);
        TextView highscore = v.findViewById(R.id.highscore);


        assert score != null;
        name.setText(score.getName());
        details.setText(score.getC()+"/"+score.getA());
        highscore.setText(String.valueOf(score.getFinalScore()));





        return v;
    }
}
