package com.example.fieldofdream.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fieldofdream.Interface.ItemClickListener;
import com.example.fieldofdream.R;
import com.example.fieldofdream.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> listOfMenuItem = new ArrayList<>();
        listOfMenuItem.add("Играть");
        listOfMenuItem.add("Список лидеров");
        listOfMenuItem.add("Настройки");
        //listOfMenuItem.add("Добавить свой вопрос(возможно будет)");

        RecyclerView menuRecyclerView = findViewById(R.id.mainMenu);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(listOfMenuItem);
        adapter.setItemClickListener(this);
        //adapter.setClickListener(this);
        menuRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(String item) {
        switch (item.toLowerCase()){ //Костыль, но рабочий
            case "играть":
                Intent goToInGameActivity = new Intent(this,InGameActivity.class);
                startActivity(goToInGameActivity);
                break;
            case "список лидеров":
                break;
            case "настройки":
                break;
        }
    }
}
