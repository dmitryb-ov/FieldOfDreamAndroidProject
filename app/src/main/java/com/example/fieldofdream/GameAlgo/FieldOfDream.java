package com.example.fieldofdream.GameAlgo;

import android.annotation.SuppressLint;
import android.app.Activity;

import com.example.fieldofdream.Activities.InGameActivity;
import com.example.fieldofdream.Interface.FieldOfDreamInterface;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class FieldOfDream implements FieldOfDreamInterface {
    public static String WORD;
    public static String description;
    public static String hitWord;
    public static String[] arrHitWord;
    public static int hitWordCount = 0;
    public static String strLet;
    public static String action = "";
    public static String events = "";
    public static int POINT = 0;
    public static boolean winner = false;

    private int fileCounter = 0;

    //    private TextView textViewDescription;
//    private TextView textViewCountOfLetter;
//    private TextView textViewHitWord;
    @SuppressLint("SetTextI18n")
    @Override
    public void startGame() {
//            Random random = new Random(descriptionMas.length);
//            description = descriptionMas[random.nextInt()];
//            description = "TEXTTT";
//            WORD = "Текст";
//            hitWord = "";
//            for (int i = 0; i < WORD.length(); i++) {
//                hitWord += "*";
//                hitWordCount++;
//            }
//            arrHitWord = hitWord.split("");
    }

    @Override
    public void letterCheck(String letter) {
        if (letter.length() > 1) {
            action = "Введите одну букву";
        }
        System.out.println("2222222222222222");
        if (Pattern.compile("[а-я]").matcher(letter.toLowerCase()).matches()) {
            System.out.println("2222222222222222");
            if (WORD.toLowerCase().contains(letter.toLowerCase())) {
                System.out.println("2222222222222222");
                try {
                    strLet = openLetter(letter).replaceAll("[,\\[\\]]", "");
                    if (strLet.equals("ПОБЕДИТЕЛЬ")) {
                        action = "ПОБЕДИТЕЛЬ";
                        winner = true;
                    } else {
                        action = "Верно, откройте букву";
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
    public static int casketCounter = 0;

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
//                    casketCounter++;
//                    if (casketCounter == 3) {
//                        events = "Три верно угаданные буквы дает вам право на три шкатулки\n" +
//                                "Три шкатулки в студию!";
//                    }
                }
            }
            for (int i = 0; i < arrWord.length; i++) {
                if (arrHitWord[i].equals(arrWord[i])) {
                    letterCounter++;
                    if (letterCounter == arrWord.length) {
                        return "ПОБЕДИТЕЛЬ";
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
    public int casket(int number) {
        return 0;
//        Random randomCasket = new Random();
//        int randomPointForCasket = randomPoint();
//        int fieldRandomCasket = randomCasket.nextInt(4);
//        if (fieldRandomCasket == 0) {
//            return casket(number);
//        } else if (number == fieldRandomCasket) {
//            POINT += randomPointForCasket;
//            events = "Поздравляю, вы получили " + randomPointForCasket + " очков";
//        } else {
//            events = "К сожалению, вы не угадали, очки были в шкатулке под номером: " + fieldRandomCasket;
//        }
//        return 0;
    }


}
