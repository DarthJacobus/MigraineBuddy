package com.example.migranebuddy;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{

    String email, password, firstName, lastName;

    EditText firstNameField, lastNameField, emailField, passwordField;
    Button createAccountButton;

    MBDatabaseHelper mbDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFieldWidgets();

        mbDatabase =  new MBDatabaseHelper(this);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                putFieldtoVar();

                long val = mbDatabase.addUser(email, password, firstName, lastName);
                if(val > 0 ) {
                    showToast("success");
                    Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intent);
                }
                else {
                    showToast("failure");
                }

            }
        });

    }


    public void initFieldWidgets()
    {
        firstNameField = findViewById(R.id.firstNameField);
        lastNameField = findViewById(R.id.lastNameField);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        createAccountButton = findViewById(R.id.createAccountButton);
    }

    public void putFieldtoVar()
    {
        firstName = firstNameField.getText().toString();
        lastName = lastNameField.getText().toString();
        email = emailField.getText().toString();
        password = passwordField.getText().toString();
    }

    public void showToast(String arg)
    {
        if(arg.equals("success")) {
            Toast.makeText(this, "New account created. Welcome!", Toast.LENGTH_SHORT).show();
        }
        else if(arg.equals("failure")) {
            Toast.makeText(this, "Failed to create new account. Try again!", Toast.LENGTH_SHORT).show();
        }

    }






}
