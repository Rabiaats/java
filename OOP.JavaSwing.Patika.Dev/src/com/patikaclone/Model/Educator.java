package com.patikaclone.Model;

public class Educator extends User{
    public Educator() {
    }

    public Educator(int id, String name, String user_name, String password) {
        super(id, name, user_name, password, "educator");
    }
}
