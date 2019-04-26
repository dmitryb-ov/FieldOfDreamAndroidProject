package com.example.fieldofdream.GameAlgo;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.util.Log;

public class ReadWriteFile extends Activity {
    private static final String LOG_TAG = "myLogs";

    public void writeFile(int point){
        try {
            Context context = getApplicationContext();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("file",MODE_PRIVATE)));
            bw.write(""+point);
            bw.close();
            Log.d(LOG_TAG, "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("file")));
            String str = "";
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
