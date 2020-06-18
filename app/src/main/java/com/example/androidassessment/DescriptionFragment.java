package com.example.androidassessment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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

public class DescriptionFragment extends Fragment implements DescriptionAdapter.OnDescriptionListener, DescriptionCallback {

    private DescriptionAdapter adapter;
    private String name, phoneNumber;
    private List<Description> descriptionList;
    public DescriptionFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.frag_description_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DescriptionAdapter(getContext(), new ArrayList<Description>(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void loadDescriptionList(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        DetailActivity activity = (DetailActivity) getActivity();
        UrbanDictService.getInstance(activity).getDescriptionsByName(this, name);
    }


    @Override
    public void onDescriptionClick(int position) {
        // TODO: Set contact description
        Log.d("name", name);
        Log.d("phone", phoneNumber);
        Log.d("description", descriptionList.get(position).description);

    }

    @Override
    public void onSuccessResponse(List<String> foundDescriptions) {
        List<Description> descriptionList = new ArrayList<>();

        for(int i = 0; i < foundDescriptions.size(); i++){
            descriptionList.add(new Description((foundDescriptions.get(i))));
        }

        this.descriptionList = descriptionList;
        adapter.setDescriptionList(descriptionList);
        adapter.notifyDataSetChanged();
    }
}
