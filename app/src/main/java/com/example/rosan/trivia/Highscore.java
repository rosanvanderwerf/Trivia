package com.example.rosan.trivia;

import java.io.Serializable;
import java.util.Comparator;

/* Created by rosan on 16-3-2018. */

public class Highscore implements Serializable {

    private String name;
    private String cScore;
    private String aScore;
    private String finalScore;

    public Highscore(String name, String cscore, String ascore, String finalScore){
        this.name = name;
        this.cScore = cscore;
        this.aScore = ascore;
        this.finalScore = finalScore;
    }

    // Getter(s)
    public String getName() {
        return name;
    }

    public String getA() {
        return aScore;
    }

    public String getC() {
        return cScore;
    }

    public String getFinalScore(){return finalScore; }

    // Setter(s)
    public void setName(String name) {
        this.name = name;
    }

    public void setA(String aScore) {
        this.aScore = aScore;
    }

    public void setC(String cScore) {
        this.cScore = cScore;
    }

    public void setFinalScore(String finalScore){ this.finalScore = finalScore; }

    // Other methods: to compare/sort highscores

}
