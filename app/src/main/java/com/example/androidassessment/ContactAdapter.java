package com.example.androidassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private OnContactListener _onContactListener;

    ContactAdapter(Context context, List<Contact> contactList, OnContactListener onContactListener)
    {
        this.contactList = contactList;
        this._onContactListener = onContactListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view, _onContactListener);
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

    static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, phoneNumber;
        OnContactListener onContactLister;

        ContactViewHolder(View view, OnContactListener onContactListener)
        {
            super(view);

            name = view.findViewById(R.id.name);
            phoneNumber = view.findViewById(R.id.phonenumber);
            this.onContactLister = onContactListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactLister.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactListener {
        void onContactClick(int position);
    }
}
