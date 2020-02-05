package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    EditText user, pass;
    Button login, not_reg;
    DatabaseHandler db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user =(EditText)findViewById(R.id.eduser);
        pass = (EditText)findViewById(R.id.edpass);
        login =(Button)findViewById(R.id.login);
        not_reg =(Button)findViewById(R.id.not_reg);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                db=new DatabaseHandler(MainActivity.this, null, null, 2);
                String username=user.getText().toString();
                String password= pass.getText().toString();

               //db.truncate();

                String StoredPassword =db.getregister(username);
                Log.d("!@#", StoredPassword);
                Log.d("!@#", password);
                if(password.equals(StoredPassword)){



                    Toast.makeText(getApplicationContext(), StoredPassword+"Login Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), RecyclerviewActivity.class));

                }
                else{
                    Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                    user.setText("");
                    pass.setText("");
                }


            }
        });

        not_reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                startActivity(new Intent(getApplicationContext(), ReegisterActivity.class));
            }
        });

    }
}
