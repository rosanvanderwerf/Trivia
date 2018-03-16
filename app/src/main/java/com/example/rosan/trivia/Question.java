package com.example.rosan.trivia;

import java.io.Serializable;
import java.util.ArrayList;

/* Created by rosan on 16-3-2018. */

public class Question implements Serializable{
    private String question;
    private String category;
    //private ArrayList<String> answers;
    private String correctAnswer;

    // Constructor
    public Question(String question, /*ArrayList<String> answers,*/ String correctAnswer, String category){
        this.question = question;
        //this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.category = category;
    }

    // Getter(s)

    public String getQuestion() {
        return question;
    }

    /*public ArrayList<String> getAnswers() {
        return answers;
    }*/

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    // Setter(s)
    public void setQuestion(String question){
        this.question = question;
    }

    /*public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }*/

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
