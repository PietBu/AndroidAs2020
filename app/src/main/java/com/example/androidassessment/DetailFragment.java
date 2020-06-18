package com.example.androidassessment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private Contact _contact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        String name = getArguments().getString("name");
        String phoneNumber = getArguments().getString("phoneNumber");
        String description = getArguments().getString("description");
        _contact = new Contact(name, phoneNumber, description);

        Log.d("tag", "Detail fragment reached!");
        Log.d("tag", "Name:" + name);
        Log.d("tag", "phoneNumber:" + phoneNumber);
        Log.d("tag", "description:" + description);


        return view;
    }

    public void setContact(Contact contact) {
        final View view = this.getView();
        _contact = contact;
        if (view != null) {
            view.findViewById(R.id.name).setVisibility(View.VISIBLE);
            view.findViewById(R.id.phonenumber).setVisibility(View.VISIBLE);
            view.findViewById(R.id.description).setVisibility(View.VISIBLE);
            view.findViewById(R.id.changebutton).setVisibility(View.VISIBLE);
        }
    }
}
