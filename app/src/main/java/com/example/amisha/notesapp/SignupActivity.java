package com.example.amisha.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button signupBtn;
    EditText usernameEditText, passwordEditText, cPasswordEditText;
    SharedPreferences auth_sharedpref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupBtn = (Button) findViewById(R.id.signup);
        usernameEditText = (EditText) findViewById(R.id.username);
        passwordEditText = (EditText) findViewById(R.id.password);
        cPasswordEditText = (EditText) findViewById(R.id.confirmpassword);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get user data
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String cpassword = cPasswordEditText.getText().toString();

                //validation
                if(username.isEmpty() || password.isEmpty() || cpassword.isEmpty())
                    Toast.makeText(getApplicationContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                else if(!password.equals(cpassword))
                    Toast.makeText(getApplicationContext(), "Passwords dont match", Toast.LENGTH_SHORT).show();

                //save to a local file and switch activity
                else {
                    auth_sharedpref = getApplicationContext().getSharedPreferences(getString(R.string.auth_sharedpref), Context.MODE_PRIVATE);
                    editor = auth_sharedpref.edit();

                    editor.putString(getString(R.string.key_username), username);
                    editor.putString(getString(R.string.key_password), password);
                    editor.apply();

                    Intent intent = new Intent(getApplicationContext() ,HomeActivity.class);
                    intent.putExtra(getString(R.string.key_username), username);
                    startActivity(intent);
                }



            }
        });
    }
}
