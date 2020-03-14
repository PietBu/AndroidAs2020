package com.example.androidassessment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
{
    private List<Contact> dataset;

    public ContactAdapter(List<Contact> contactList)
    {
        dataset = contactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int position) {
        holder.name.setText(dataset.get(position).name);
        holder.phoneNumber.setText(dataset.get(position).phoneNumber);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView phoneNumber;

        ContactViewHolder(View view)
        {
            super(view);

            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            phoneNumber = view.findViewById(R.id.phonenumber);
        }
    }
}
