package com.example.fieldofdream;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.fieldofdream.Interface.ItemClickListener;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView textViewInMenu;
    ItemClickListener itemClickListener;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewInMenu = itemView.findViewById(R.id.menuItem);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(itemClickListener != null){
            itemClickListener.onItemClick(textViewInMenu.getText().toString());
        }
    }
}
