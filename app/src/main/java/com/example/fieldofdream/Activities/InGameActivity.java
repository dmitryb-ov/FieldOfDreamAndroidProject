package com.example.fieldofdream.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView event;

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
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder.setTitle("Буква");
                View promptView = LayoutInflater.from(context).inflate(R.layout.prompt, null);
                dialogBuilder.setView(promptView);

                final EditText inputText = promptView.findViewById(R.id.input_text);


                dialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(inputText.getText().length() > 1){

                                }
                                fieldOfDream.letterCheck(inputText.getText().toString());
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("И так!")
                                        .setMessage(FieldOfDream.action)
                                        .setCancelable(false)
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        });
//                                if(FieldOfDream.strLet.equals("1")){
//                                    textViewHitWord.setText("ВЫ ПОБЕДИТЕЛЬ");
//                                }
                                textViewHitWord.setText(FieldOfDream.strLet);
                                textViewHitWord.refreshDrawableState();
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
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
    public void showDialog(String text) {
        System.out.println("111111111111111111111111111111111");
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("И так!")
                .setMessage(text)
                .setCancelable(false)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
