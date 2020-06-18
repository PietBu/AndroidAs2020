package com.example.androidassessment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void onContactClick(int position) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("contact", readContactList().get(position));
        startActivity(intent);
    }
}
