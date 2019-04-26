package com.example.fieldofdream.Activities;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fieldofdream.GameAlgo.FieldOfDream;
import com.example.fieldofdream.GameAlgo.ReadWriteFile;
import com.example.fieldofdream.R;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class InGameActivity extends AppCompatActivity {
    final Context context = this;

    private TextView textViewHitWord;
    private TextView points;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE);
        setContentView(R.layout.activity_in_game);

        final Toast toast = Toast.makeText(getApplicationContext(), "Формулировка вопроса в левом верхнем углу", Toast.LENGTH_LONG);
        toast.show();

        final FieldOfDream fieldOfDream = new FieldOfDream();
        //fieldOfDream.startGame();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(InGameActivity.this);
        SharedPreferences.Editor editor = preferences.edit();

        String[] keys = {"Индия", "Войско", "Олух"};

        editor.putString(keys[0], "В какой азиатской стране родился Редьярд Киплинг");
        editor.putString(keys[1], "Как назывался союз племен");
        editor.putString(keys[2], "Как в старину называли пастуха");

        editor.commit();

        Random random = new Random();

        FieldOfDream.WORD = keys[random.nextInt(keys.length)];
        FieldOfDream.description = preferences.getString(FieldOfDream.WORD, "unknown");
        FieldOfDream.hitWord = "";
        for (int i = 0; i < FieldOfDream.WORD.length(); i++) {
            FieldOfDream.hitWord += "*";
            FieldOfDream.hitWordCount++;
        }
        FieldOfDream.arrHitWord = FieldOfDream.hitWord.split("");
        FieldOfDream.strLet = FieldOfDream.hitWord;

        ImageButton questionButton = findViewById(R.id.question);
        ImageButton letterButton = findViewById(R.id.letterButton);
        ImageButton allWordButton = findViewById(R.id.allWordButton);
        textViewHitWord = findViewById(R.id.hitWord);
        textViewHitWord.setText(FieldOfDream.hitWord);
        points = findViewById(R.id.point);
        points.setText("" + FieldOfDream.POINT);
        animateTextView(0, FieldOfDream.POINT, points);


//        textViewDescription = findViewById(R.id.textViewDescription);
//        nameTextView = findViewById(R.id.nameTextView);
//        points = findViewById(R.id.points);
//        textViewHitWord = findViewById(R.id.textViewHitWord);
//        addLetterButton = findViewById(R.id.addLetterButton);
//        event = findViewById(R.id.textViewEvent);
////        openCasketButton = findViewById(R.id.opencasket);
////        openCasketButton.setVisibility(View.GONE);
//
//        // points.setText(FieldOfDream.POINT);
//        textViewDescription.setText(FieldOfDream.description);
//        textViewHitWord.setText(FieldOfDream.hitWord);
//
//        nameTextView.setText(getIntent().getExtras().getString("nickname"));
//        points.setText("" + FieldOfDream.POINT);
        allWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                dialogBuilder.setTitle("Слово");
                View promptView = LayoutInflater.from(context).inflate(R.layout.prompt, null);
                dialogBuilder.setView(promptView);

                final EditText inputText = promptView.findViewById(R.id.input_text);


                dialogBuilder
                        .setCancelable(false)
                        .setMessage("Введите всё слово сразу\n" +
                                "Если слово будет некорректное, вы проиграете!")
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (inputText.getText().toString().toLowerCase().equals(FieldOfDream.WORD.toLowerCase())) {
                                    textViewHitWord.setText("ПОБЕДИТЕЛЬ");
                                } else {
                                    textViewHitWord.setText("Вы проиграли");
                                    Intent intent = new Intent(InGameActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast toastFail = Toast.makeText(getApplicationContext(),"Вы проиграли",Toast.LENGTH_LONG);
                                    toastFail.setGravity(Gravity.CENTER, 0,0);
                                    LinearLayout toastContainer = (LinearLayout) toastFail.getView();
                                    ImageView yakub = new ImageView(getApplicationContext());
                                    yakub.setImageResource(R.drawable.yakub);
                                    toastContainer.addView(yakub,0);
                                    toastFail.show();
                                }
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
                textViewHitWord.setText(FieldOfDream.strLet);
            }
        });
        questionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Вопрос:")
                        .setMessage(FieldOfDream.description)
                        .setCancelable(true)
                        .setNegativeButton("Ок", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        letterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //points.setText(FieldOfDream.POINT);

                //event.setText(FieldOfDream.events);
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
                                textViewHitWord.setText(FieldOfDream.strLet);
                                if (inputText.getText().length() > 1 || inputText.getText().length() == 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("И так!")
                                            .setMessage("Введите одну букву!")
                                            .setCancelable(false)
                                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                }
                                            });
                                } else {
                                    fieldOfDream.letterCheck(inputText.getText().toString());
                                    textViewHitWord.setText(FieldOfDream.strLet);
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setTitle("И так!")
                                            .setMessage(FieldOfDream.action)
                                            .setCancelable(false)
                                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.cancel();
                                                    //points.setText(FieldOfDream.POINT);
                                                }
                                            });
                                    textViewHitWord.setText(FieldOfDream.strLet);
                                    points.setText("" + FieldOfDream.POINT);
                                    animateTextView(0, FieldOfDream.POINT, points);
                                    if (FieldOfDream.winner) {
                                        try {
                                            Context context = getApplicationContext();
                                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(context.openFileOutput("file", MODE_PRIVATE)));
                                            bw.write("" + FieldOfDream.POINT+"\n");
                                            bw.close();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                    textViewHitWord.setText(FieldOfDream.strLet);
                                }
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
                textViewHitWord.setText(FieldOfDream.strLet);

//                if (FieldOfDream.casketCounter == 3) {
//                    openCasketButton.setVisibility(View.VISIBLE);
//                    event.setText(FieldOfDream.events);
//
//                    openCasketButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            final String[] casketVariant = {"Шкатулка №1", "Шкатулка №2", "Шкатулка №3"};
//                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                            builder.setItems(casketVariant, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    fieldOfDream.casket(which);
//                                }
//                            });
//                            AlertDialog casketAlert = builder.create();
//                            casketAlert.show();
//                            FieldOfDream.casketCounter = 0;
//                            openCasketButton.setVisibility(View.GONE);
//                            points.setText("" + FieldOfDream.POINT);
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            AlertDialog.Builder casketPointsDialog = new AlertDialog.Builder(context);
//                            casketPointsDialog.setTitle("Шкатулка")
//                                    .setMessage(FieldOfDream.action)
//                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.cancel();
//                                        }
//                                    });
//                        }
//                    });
//                }
            }
        });
    }
    private void animateTextView(int initialValue, int finalValue, final TextView  textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(initialValue, finalValue);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                textview.setText(valueAnimator.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();
    }
}
