package com.example.androidassessment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder>{

    private List<Contact> contactSet;

    public ContactAdapter(List<Contact> contacts)
    {
        this.contactSet = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.textView.setText(contactSet.get(position).name);
        holder.nickView.setText(contactSet.get(position).nickname);
    }

    @Override
    public int getItemCount() {
        return contactSet.size();
    }
}
