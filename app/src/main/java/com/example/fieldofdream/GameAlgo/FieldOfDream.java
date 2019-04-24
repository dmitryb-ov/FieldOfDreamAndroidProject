package com.example.fieldofdream.GameAlgo;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.example.fieldofdream.Activities.InGameActivity;
import com.example.fieldofdream.Interface.FieldOfDreamInterface;

import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class FieldOfDream implements FieldOfDreamInterface {
    public static String WORD;
    public static String description;
    public static String hitWord;
    public static String[] arrHitWord;
    public static int hitWordCount = 0;
    private int POINT = 0;
    public static String strLet;

    public static String action = "";


    //    private TextView textViewDescription;
//    private TextView textViewCountOfLetter;
//    private TextView textViewHitWord;
    @SuppressLint("SetTextI18n")
    @Override
    public void startGame() {
        description = "text";
        WORD = "Текст";
        hitWord = "";
        for (int i = 0; i < WORD.length(); i++) {
            hitWord += "*";
            hitWordCount++;
        }
        arrHitWord = hitWord.split("");
    }

    @Override
    public void letterCheck(String letter) {
        System.out.println("2222222222222222");
        if (Pattern.compile("[а-я]").matcher(letter.toLowerCase()).matches()) {
            System.out.println("2222222222222222");
            if (WORD.toLowerCase().contains(letter.toLowerCase())) {
                System.out.println("2222222222222222");
                try {
                    strLet = openLetter(letter).replaceAll("[,\\[\\]]", "");
                    if (strLet.equals("1")) {
                        //mes
                    } else {
                        //mes
                        int randomNumber = randomPoint();
                        //int randomNumber = 0;
                        switch (randomNumber) {
                            case 0:
                                action = "Вы банкрот";
                                POINT = 0;
                                break;
                            case 1000:
                                action = "ТЫСЯЧА ОЧКОВ";
                                POINT = POINT + 1000;
                                //
                                break;
                            default:
                                POINT = POINT + randomNumber;
                                action = POINT + " очков";
                                break;
                        }
                    }
                } catch (NullPointerException e) {
                    action = "Такая буква уже была!";
                }
            } else {
                action = "Не тот символ/буква";
            }
        }
    }

    private List<String> letterMemory = new ArrayList<>();
    private int casketCounter = 0;

    @Override
    public String openLetter(String letter) {
        System.out.println("3333333333333333333333");
        String[] arrWord = WORD.toLowerCase().split("");
        int letterCounter = 0;
        if (letterMemory.contains(letter)) {
            casketCounter = 0;
            letterMemory.add(letter);
            return null;
        } else {
            for (int i = 0; i < arrWord.length; i++) {
                if (arrWord[i].equals(letter)) {
                    arrHitWord[i] = letter;
                    casketCounter++;
                    if (casketCounter == 3) {
                        //шкалутка
                    }
                }
            }
            for (int i = 0; i < arrWord.length; i++) {
                if (arrHitWord[i].equals(arrWord[i])) {
                    letterCounter++;
                    if (letterCounter == arrWord.length) {
                        return "1";
                    }
                }
            }
            letterMemory.add(letter);
            return Arrays.toString(arrHitWord);
        }
    }

    @Override
    public int randomPoint() {
        int rand = (int) (Math.random() * 1000);
        rand = rand * 10;
        if (rand > 1000 || rand < 0) {
            rand = 0;
            return randomPoint();
        }
        return rand;
    }

    @Override
    public void casket() {

    }


}
