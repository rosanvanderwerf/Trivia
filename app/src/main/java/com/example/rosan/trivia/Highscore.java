package com.example.rosan.trivia;

import java.io.Serializable;

/* Created by rosan on 16-3-2018. */

public class Highscore implements Serializable {

    private String name;
    private String cScore;
    private String aScore;

    public Highscore(String name, String cscore, String ascore){
        this.name = name;
        this.cScore = cscore;
        this.aScore = ascore;
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
}
