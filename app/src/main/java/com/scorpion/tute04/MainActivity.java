package com.scorpion.tute04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scorpion.tute04.Database.DBHandler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button select, add, signIn, delete, update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);

        select = findViewById(R.id.selectBtn);
        add = findViewById(R.id.addBtn);
        signIn = findViewById(R.id.signBtn);
        delete = findViewById(R.id.deleteBtn);
        update =  findViewById(R.id.updateBtn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                long id = dbHandler.insertUser(username.getText().toString(),password.getText().toString());
                Toast.makeText(MainActivity.this, "User Inserted Successfully"+id, Toast.LENGTH_SHORT).show();

                username.setText(null);
                password.setText(null);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                int count = dbHandler.deleteUser(username.getText().toString());
                Toast.makeText(MainActivity.this, "User Removed Successfully"+count+ " Rows Effected", Toast.LENGTH_SHORT).show();

                username.setText(null);
                password.setText(null);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler dbHandler = new DBHandler(getApplicationContext());

                int count = dbHandler.updateUser(username.getText().toString(),password.getText().toString());
                Toast.makeText(MainActivity.this, "User updated Successfully."+count+ " Rows Effected.", Toast.LENGTH_SHORT).show();

                username.setText(null);
                password.setText(null);
            }
        });


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler = new DBHandler(getApplicationContext());

                ArrayList usernames = dbHandler.showAllUsers();

                username.setText(usernames.get(0).toString());

            }
        });

    }
}
