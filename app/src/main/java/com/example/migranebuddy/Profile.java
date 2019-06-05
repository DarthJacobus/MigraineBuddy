package com.example.migranebuddy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity
{

    User currentUser;
    static MBDatabaseHelper mbDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String currentUserEmail = getIntent().getStringExtra("currentUserEmail");

        TextView displayEmailText = findViewById(R.id.displayEmailText);
        displayEmailText.setText(currentUserEmail);

        TextView displayPasswordText = findViewById(R.id.displayPasswordText);

        mbDatabase = new MBDatabaseHelper(this);

        try {
            currentUser = mbDatabase.getUser(currentUserEmail);

            displayPasswordText.setText(currentUser.getPassword());
        }
        catch(Exception e) {
            Toast.makeText(Profile.this, "This did not work yet!", Toast.LENGTH_SHORT).show();
        }








    }




}
