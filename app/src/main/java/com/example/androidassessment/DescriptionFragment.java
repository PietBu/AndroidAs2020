package com.example.androidassessment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    // Loads list of descriptions by name
    void loadDescriptionList(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
        DetailActivity activity = (DetailActivity) getActivity();

        try{
            if(!isConnected()){ // No internet connection
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show();
            }else{ // Get descriptions
                UrbanDictService.getInstance(activity).getDescriptionsByName(this, name);
            }
        }catch(Exception e){ // Error
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Sets description when clicked on
    @Override
    public void onDescriptionClick(int position) {
        DetailActivity activity = (DetailActivity) getActivity();
        if(activity == null)
            return;

        activity.setDescription(phoneNumber, descriptionList.get(position).description);
    }

    // API response
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

    // Check if device is connected to the internet
    public boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }
}
