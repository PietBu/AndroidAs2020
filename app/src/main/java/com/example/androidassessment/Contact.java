package com.example.androidassessment;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Contact implements Serializable {

    public String name;
    String phoneNumber;
    String description;

    Contact(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
