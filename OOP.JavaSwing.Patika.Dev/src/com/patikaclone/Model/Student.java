package com.patikaclone.Model;

public class Student extends User{
    public Student() {
    }
    public Student(int id, String name, String user_name, String password) {
        super(id, name, user_name, password, "student");
    }
}
