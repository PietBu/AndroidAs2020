package com.example.androidassessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    TextView name, phoneNumber, description;
    Contact contact;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        this.contact = (Contact)i.getSerializableExtra("contact");
        sharedPreferences = getPreferences(MODE_PRIVATE);
        loadDescription();

        name = findViewById(R.id.name);
        phoneNumber = findViewById(R.id.phonenumber);
        description = findViewById(R.id.description);

        name.setText(contact.name);
        phoneNumber.setText(contact.phoneNumber);
        description.setText(contact.description);
    }

    public void onNewDescriptionClick(View view) {
        DescriptionFragment fragment = (DescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.recycler_fragment);
        if(fragment == null)
            return;

        fragment.loadDescriptionList(contact.name, contact.phoneNumber);
    }

    public void setDescription(String phoneNumber, String description){
        contact.description = description;
        this.description.setText(contact.description);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(phoneNumber, description);
        editor.apply();
    }

    private void loadDescription(){
        Object result = sharedPreferences.getString(contact.phoneNumber, null);
        if(result == null)
            return;

        contact.description = result.toString();
    }
}
