package com.example.androidassessment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView name, phoneNumber, description;
    Contact contact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        this.contact = (Contact)i.getSerializableExtra("contact");

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phonenumber);
        description = findViewById(R.id.description);

        name.setText(contact.name);
        phoneNumber.setText(contact.phoneNumber);
        description.setText(contact.description);
    }

    public void onNewDescriptionClick(View view) {

        DescriptionFragment fragment = (DescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.recycler_fragment);
        fragment.loadDescriptionList(contact.name, contact.phoneNumber);
    }
}
