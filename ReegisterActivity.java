package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReegisterActivity extends AppCompatActivity {


    EditText first, last, email, mobile, pass, confpass;
    Button save, cancel;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reegister);

        first= (EditText)findViewById(R.id.editfirstname);
//        last =(EditText)findViewById(R.id.editlastname);
        email=(EditText)findViewById(R.id.editemail);
//        mobile =(EditText)findViewById(R.id.editmobileno);
        pass=(EditText)findViewById(R.id.editpassword);
        confpass=(EditText)findViewById(R.id.editconformpassword);

        save=(Button)findViewById(R.id.btnsave);
        cancel=(Button)findViewById(R.id.btncancel);

        db = new DatabaseHandler(this, null, null, 2);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String edfirst = first.getText().toString();
//                String edlast = last.getText().toString();
                String edemail = email.getText().toString();
//                String edmobile = mobile.getText().toString();
                String edpass = pass.getText().toString();
                String edConf = confpass.getText().toString();

                if (edConf.equals(edpass)) {

                    Log.d("!@#","register data");
                    RegisterData reg = new RegisterData();

                    reg.setUsername(edfirst);
                   // Log.d("!@#",reg.setUsername(edfirst));
//                    reg.setlastName(edlast);
                    reg.setEmail(edemail);
//                    reg.setMobNo(edmobile);
                    reg.setPassword(edpass);

                    db.addregister(reg);


                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Log.d("!@#","register data else");

                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                    pass.setText("");
                    confpass.setText("");
                }
            }
            });
        }
    }

