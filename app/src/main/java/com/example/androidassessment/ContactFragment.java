package com.example.androidassessment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ContactFragment extends Fragment implements ContactAdapter.OnContactListener {

    public ContactFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag_contact_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        final ContactAdapter adapter = new ContactAdapter(getContext(), readContactList(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private List<Contact> readContactList(){

        List<Contact> contactList = new ArrayList<>();

        @SuppressLint("Recycle") Cursor cursor = Objects.requireNonNull(getContext()).getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        assert cursor != null;
        while(cursor.moveToNext()){

            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(new Contact(contactName, phoneNumber));
        }

        return contactList;
    }

    private void setDescription(Contact contact, String description) {

        // Retrieve the descriptions from localstorage
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(sharedPref.contains(contact.phoneNumber)){
            editor.remove(contact.phoneNumber);
            editor.putString(contact.phoneNumber, description);
        } else{
            editor.putString(contact.phoneNumber, description);
        }

        editor.apply();
    }

    private String getDescription(Contact contact){
        // Retrieve the descriptions from localstorage
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        if(sharedPref.contains(contact.phoneNumber)){
            // Get contact by phone number
            return sharedPref.getString(contact.phoneNumber, "");
        }
        return "";
    }

    @Override
    public void onContactClick(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("contact", readContactList().get(position));
        startActivity(intent);

//        DetailFragment fragment = new DetailFragment();
//        fragment.setArguments(new Bundle());
//        fragment.setContact(readContactList().get(position));
//        getFragmentManager().beginTransaction()
//                .replace(R.id.container, fragment, "detail").addToBackStack(null).commit();

    }
}
