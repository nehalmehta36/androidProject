package com.example.myapplication5;

public class Movie {

    private String username, email, password;

    public Movie() {
    }

    public Movie(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String name) {
        this.username = name;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
}
