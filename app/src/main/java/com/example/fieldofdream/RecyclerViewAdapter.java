package com.example.fieldofdream;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fieldofdream.Interface.ItemClickListener;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> itemList;
    //public  ItemClickListener itemClickListener;

    public RecyclerViewAdapter(List<String> itemList) {
        this.itemList = itemList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_menu_main,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String items = itemList.get(i);
        viewHolder.textViewInMenu.setText(items);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        com.example.fieldofdream.ViewHolder.itemClickListener = itemClickListener;
    }





//    private List<String> list;
//    private ItemClickListener clickListener;
//
//    // data is passed into the constructor
//    public  RecyclerViewAdapter(List<String> data) {
//        this.list = data;
//    }
//
//    // inflates the row layout from xml when needed
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_main, parent, false);
//        return new ViewHolder(view);
//    }
//
//    // binds the data to the TextView in each row
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = list.get(position);
//        holder.myTextView.setText(animal);
//    }
//
//    // total number of rows
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//
//    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView myTextView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            myTextView = itemView.findViewById(R.id.menuItem);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (clickListener != null) clickListener.onItemClick(myTextView.getText().toString());
//        }
//    }
//
//    // allows clicks events to be caught
//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.clickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(String name);
//    }

}
