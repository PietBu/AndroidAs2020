package com.example.androidassessment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.androidassessment.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

//public class MainActivity extends AppCompatActivity implements ContactAdapter.OnItemSelectedListener {
public class MainActivity extends AppCompatActivity implements RecyclerClickListener {
//    public class MainActivity extends AppCompatActivity implements ContactFragment.OnItemSelectedListener {
//public class MainActivity extends AppCompatActivity {

    final int READ_CONTACT_PERMISSION_REQUEST = 1;
    boolean READ_CONTACT_PERMISSION_GRANTED = false;
    List<Contact> contactList;

    @Override
    // - Startup splash screen
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ask for permissions
        askPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode)
        {
            case READ_CONTACT_PERMISSION_REQUEST:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                    READ_CONTACT_PERMISSION_GRANTED = true;
                }
            }
        }
    }

    private void askPermissions(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, READ_CONTACT_PERMISSION_REQUEST);
        }
    }

//    public void onItemSelected(Contact contact)
    public void RecyclerListClicked(Contact contact)
    {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            DetailViewFragment detail =(DetailViewFragment)getSupportFragmentManager().findFragmentById(R.id.detail_fragment);

            if (detail != null)
            {
                detail.setContact(contact);
            }
        }
        else
        {
            DetailViewFragment detail = new DetailViewFragment();

        }

    }

    private void readContacts() {
        contactList.add(new Contact("Maarten", "0631503261"));
        contactList.add(new Contact("Piet", "0666666666"));
    }

//    @Override
//    public void onItemSelected(int position) {
//        Contact charlie = contactList.get(position);
////        return null;
//    }
    public void RecyclerListClicked(int position) {
    String s = "Did I get it?";

    }
}