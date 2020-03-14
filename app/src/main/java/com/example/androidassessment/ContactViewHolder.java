package com.example.androidassessment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView nickView;
    TextView textView;

    ContactViewHolder(View view)
    {
        super(view);

        nickView = view.findViewById(R.id.nickView);
        textView = view.findViewById(R.id.textView);
    }
}
