package com.example.androidassessment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DetailActivity extends AppCompatActivity {
    TextView name, phoneNumber, description;
    Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        this.contact = (Contact)i.getSerializableExtra("contact");
        Log.d("tag", contact.name);

        name = (TextView) findViewById(R.id.name);
        phoneNumber = (TextView) findViewById(R.id.phonenumber);
        description = (TextView) findViewById(R.id.description);

        name.setText(contact.name);
        phoneNumber.setText(contact.phoneNumber);
        description.setText(contact.description);
    }

    public void onNewDescriptionClick(View view) {
        Log.d("tag", "button pressed!");
        UrbanDictService urbanDictService = UrbanDictService.getInstance(this);
        urbanDictService.requestDescription(contact.name);
    }
}
