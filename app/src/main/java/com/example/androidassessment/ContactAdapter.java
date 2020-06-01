package com.example.androidassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
{

    private List<Contact> contactList;

    ContactAdapter(Context context, List<Contact> contactList)
    {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        TextView name, phoneNumber;

        name = holder.name;
        phoneNumber = holder.phoneNumber;

        name.setText(contactList.get(position).name);
        phoneNumber.setText(contactList.get(position).phoneNumber);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView name, phoneNumber;

        ContactViewHolder(View view)
        {
            super(view);

            name = view.findViewById(R.id.name);
            phoneNumber = view.findViewById(R.id.phonenumber);
        }
    }
}
