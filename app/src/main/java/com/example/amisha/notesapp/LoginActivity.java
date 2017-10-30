package com.example.amisha.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView signupTextView;
    Button loginBtn;
    EditText usernameEditText, passwordEditText;
    SharedPreferences auth_sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupTextView = (TextView)findViewById(R.id.signup);
        loginBtn = (Button) findViewById(R.id.login);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);

        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() ,SignupActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get usernae and password entered by user
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                auth_sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.auth_sharedpref), Context.MODE_PRIVATE);

                //Get username and password stored in shared preferences
                String saved_username = auth_sharedPref.getString(getString(R.string.key_username), "");
                String saved_password = auth_sharedPref.getString(getString(R.string.key_password), "");

                //Check if any field is empty
                if(username.isEmpty() || password.isEmpty())
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();

                //Check whether username and password are correct
                else if(!(username.equals(saved_username) && password.equals(saved_password) )) {
                    Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT).show();
                }

                //Switch to next activity
                else {
                    Intent intent = new Intent(getApplicationContext() ,HomeActivity.class);
                    //Pass username along with intent
                    intent.putExtra(getString(R.string.key_username), username);
                    startActivity(intent);
                }
            }
        });


    }
}
