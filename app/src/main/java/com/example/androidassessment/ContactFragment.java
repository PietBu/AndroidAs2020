package com.example.androidassessment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactFragment extends Fragment implements RecyclerClickListener {
//public class ContactFragment extends Fragment{
//    public interface OnItemSelectedListener{
////        public void onItemSelected(Contact c);
//        public void onItemSelected(int position);
//    }

//    private OnItemSelectedListener onItemSelectedListener;
    private RecyclerClickListener onItemSelectedListener;

    private RecyclerView recyclerView;
    private View view;

//    public ContactFragment(OnItemSelectedListener listen){
public ContactFragment(){
//        onItemSelectedListener = listen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.frag_contact_list, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        final ContactAdapter adapter = new ContactAdapter(this.getContext(), readContactList(), this);
//        final ContactAdapter adapter = new ContactAdapter(this.getContext(), readContactList());
        recyclerView.setAdapter(adapter);

//        recyclerView.setOnClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                onItemSelectedListener.RecyclerListClicked((int) adapter.getItemId(position));
//            }
//        });

//        recyclerView.setOnClickListener(new AdapterView.OnItemClickListener(){
//            public void onItemClick(AdapterView parent, View v, int position, long id)
//            {
////                onItemSelectedListener.onItemSelected(adapter.getItemId(position));
//                onItemSelectedListener.onItemSelected(adapter.getItemId(position));
//            }
//        });

//        recyclerView.setOnClickListener(new AdapterView.OnClickListener(){
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                onItemSelectedListener.onItemSelected(adapter.getItemId(position));
//            }
//        });

//        // Basic API test
//        final ArrayList<String> foundDescs = new ArrayList<String>();
//        final Activity fragmentActivity = this.getActivity();
//        UrbanDictService.getInstance(fragmentActivity).getAllDescriptions(new ManyDescriptionVolleyCallback() {
//            @Override
//            public void onSuccessResponse(ArrayList<String> foundDescriptions) {
//                String collectionName = "descFavourites";
//
//                SharedPreferences sharedPref = fragmentActivity.getSharedPreferences("pokedroid-preferences", Context.MODE_PRIVATE);
//                Set<String> set = sharedPref.getStringSet(collectionName, new ArraySet<String>());
//                for (int i = 0; i < foundDescriptions.size(); i++) {
//                    String description = foundDescriptions.get(i);
//                    // TODO: separate "favorite" descriptions
//                }
//                foundDescs.addAll(foundDescriptions);
//            }
//        }, "maarten");
//
//        foundDescs.size();

        return view;
    }

    private List<Contact> readContactList(){

        List<Contact> contactList = new ArrayList<>();

        Cursor cursor = getContext().getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME + " ASC");
        //cursor.moveToFirst();

        while(cursor.moveToNext()){

            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(new Contact(contactName, phoneNumber));
        }

        return contactList;
    }

//    public void RecyclerListClicked(View v, Contact c) {
//        String s = "load detail fragment I guess";
//
//    }
    public void RecyclerListClicked(Contact c) {
    String s = "load detail fragment I guess";

    }
}
