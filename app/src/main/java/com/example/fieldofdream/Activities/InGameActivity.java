package com.example.fieldofdream.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fieldofdream.GameAlgo.FieldOfDream;
import com.example.fieldofdream.R;

public class InGameActivity extends AppCompatActivity {
    final Context context = this;

    private TextView textViewDescription;
    private TextView textViewCountOfLetter;
    private TextView textViewHitWord;

    private Button addLetterButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_in_game);


        final FieldOfDream fieldOfDream = new FieldOfDream();
        fieldOfDream.startGame();

        textViewDescription = findViewById(R.id.textViewDescription);
        textViewCountOfLetter = findViewById(R.id.textViewCountOfLetter);
        textViewHitWord = findViewById(R.id.textViewHitWord);
        addLetterButton = findViewById(R.id.addLetterButton);

        textViewDescription.setText("Описание слова:\n" + FieldOfDream.description);
        textViewCountOfLetter.setText("Количество бвув\n" + FieldOfDream.hitWordCount);
        textViewHitWord.setText(FieldOfDream.hitWord);


        addLetterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = new LayoutInflater.from(context);
                View promtptView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder.setView(promtptView);

                final EditText inputText = promtptView.findViewById(R.id.input_text);

                dialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        });

    }
}
