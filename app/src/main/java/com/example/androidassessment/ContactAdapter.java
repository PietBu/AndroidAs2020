package com.example.androidassessment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
{
    private LayoutInflater layoutInflater;
    private Context context;

    private List<Contact> contactList;

    private static RecyclerClickListener itemListener;
//    private static ContactFragment.OnItemSelectedListener itemListener;

//    public ContactAdapter(Context context, List<Contact> contactList, ContactFragment.OnItemSelectedListener listen)
    public ContactAdapter(Context context, List<Contact> contactList, RecyclerClickListener listen)
    {
        this.context = context;
        this.contactList = contactList;
        itemListener = listen;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
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

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name, phoneNumber;

        ContactViewHolder(View view)
        {
            super(view);

            name = view.findViewById(R.id.name);
            phoneNumber = view.findViewById(R.id.phonenumber);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            itemListener.onItemSelected(contactList.get(this.getLayoutPosition()));
            itemListener.RecyclerListClicked(contactList.get(this.getLayoutPosition()));
        }
    }
}
