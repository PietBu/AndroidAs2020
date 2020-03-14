package com.example.androidassessment;

public class Contact {
    public String name;
    public String nickname;
    public String number;

    public Contact(String name, String number)
    {
        this.name = name;
        this.number = number;
        this.nickname = "";
    }

    public Contact(String name, String number, String nickname)
    {
        this.name = name;
        this.number = number;
        this.nickname = nickname;
    }
}
