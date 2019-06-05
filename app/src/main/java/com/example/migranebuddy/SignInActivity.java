package com.example.migranebuddy;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText emailField, passwordField;
    Button signInButton;
    String email, password;
    String userFirstName;

    MBDatabaseHelper mbDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mbDatabase = new MBDatabaseHelper(this);

        emailField = findViewById(R.id.emailFieldSI);
        passwordField = findViewById(R.id.passwordFieldSI);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailField.getText().toString();
                password = passwordField.getText().toString();


                if(mbDatabase.userExists(email, password)) {
                    Toast.makeText(SignInActivity.this, "Signed in. Welcome, " + getUserFirstName(email) + "!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, Profile.class);
                    intent.putExtra("currentUserEmail", email);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SignInActivity.this, "Sign in failed. Try again!", Toast.LENGTH_SHORT).show();
                }





            }
        });



    }


    public String getUserFirstName(String currentUserEmail)
    {
        mbDatabase = new MBDatabaseHelper(this);

        try {

            User currentUser = mbDatabase.getUser(currentUserEmail);

            return currentUser.getPassword();
        }
        catch(Exception e) {

        }

        return null;

    }



}
