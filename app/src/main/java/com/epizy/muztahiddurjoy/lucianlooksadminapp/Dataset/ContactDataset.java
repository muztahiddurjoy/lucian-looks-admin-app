package com.epizy.muztahiddurjoy.lucianlooksadminapp.Dataset;

public class ContactDataset {
    String Name;
    String Email;
    String Message;
    String Number;

    public ContactDataset() {
    }

    public ContactDataset(String name, String email, String message, String number) {
        Name = name;
        Email = email;
        Message = message;
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
