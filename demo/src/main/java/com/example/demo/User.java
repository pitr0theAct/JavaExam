package com.example.demo;

public class User {
    private String firstname;
    private String secondname;
    private String thirdname;
    private String password;

    public User(String firstname, String secondname, String thirdname, String password) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.thirdname = thirdname;
        this.password = password;
    }

    public User() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getThirdname() {
        return thirdname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
