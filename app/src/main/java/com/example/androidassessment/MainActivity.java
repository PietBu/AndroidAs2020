package com.example.androidassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.androidassessment.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactFragment.OnListFragmentInteractionListener {

    @Override
    //TODO:
    // - Startup splash screen
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.fragment_contact_list);

        // Taken from ppt week 2
        RecyclerView recyclerView = findViewById(R.id.contact_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> list = new ArrayList<>();

        list.add(new Contact("Bulbasaur", "123"));
        list.add(new Contact("Dragonite", "456"));
        list.add(new Contact("Pikachu", "789"));

        recyclerView.setAdapter(new ContactAdapter(list));
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}

//import android.os.Bundle
//        import android.support.v7.app.AppCompatActivity
//
//class MainActivity: AppCompatActivity() {
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//        // Make sure this is before calling super.onCreate
//
//        super.onCreate(savedInstanceState)
//        // …
//        }
//
//        }
