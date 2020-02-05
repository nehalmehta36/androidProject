package com.example.myapplication5;

import android.util.Log;

public class RegisterData {


    private String username;
    private String email;
    private String password;


    // Empty constructor
    public RegisterData(){

    }
    // constructor
    public RegisterData(String usernamer, String  emailr,String passwordr){
        this.username = usernamer ;
        this.email =  emailr;
        this.password = passwordr ;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if(username != null)
        this.username = username;
        Log.d("!@#",username);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email != null)
        this.email = email;
        Log.d("!@#",email);
    }

    public String getPassword() {
        Log.d("!@#",password);
        return password;

    }

    public void setPassword(String password) {
        if(password != null)
        this.password = password;
        Log.d("!@#",password);
    }








}
