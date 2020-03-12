package com.example.androidassessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidassessment.dummy.DummyContent;

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
//        RecyclerView recyclerView = findViewById(R.id.contact_list);

//        recyclerView.LayoutManager = new LinerLayoutManager(this);
//        recyclerView.
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        List<Pokemon> list = new ArrayList<>();
//
//        list.add(new Pokemon(R.drawable.bulbasaur, "Bulbasaur"));
//        list.add(new Pokemon(R.drawable.dragonite, "Dragonite"));
//        list.add(new Pokemon(R.drawable.pikachu, "Pikachu"));
//
//        recyclerView.setAdapter(new PokemonAdapter(list));
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
