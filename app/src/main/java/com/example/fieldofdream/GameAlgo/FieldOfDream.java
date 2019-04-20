package com.example.fieldofdream.GameAlgo;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.example.fieldofdream.Interface.FieldOfDreamInterface;

public class FieldOfDream implements FieldOfDreamInterface {
    public static String WORD;
    public static String description;
    public static String hitWord;
    public static String[] arrHitWord;
    public static int hitWordCount = 0;


    //    private TextView textViewDescription;
//    private TextView textViewCountOfLetter;
//    private TextView textViewHitWord;
    @SuppressLint("SetTextI18n")
    @Override
    public void startGame() {
        description = "text";
        WORD = "TEXT";
        hitWord = "";
        for (int i = 0; i < WORD.length(); i++) {
            hitWord += "*";
            hitWordCount++;
        }
        arrHitWord = hitWord.split("");
    }

    @Override
    public void letterCheck(String letter) {

    }

    @Override
    public String openLetter(String letter) {
        return null;
    }

    @Override
    public int randomPoint() {
        return 0;
    }

    @Override
    public void casket() {

    }



}
