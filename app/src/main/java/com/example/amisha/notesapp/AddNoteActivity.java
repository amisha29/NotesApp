package com.example.amisha.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddNoteActivity extends AppCompatActivity {

    Button submit;
    EditText titleEditText, descriptionEditText;
    String title, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEditText = (EditText) findViewById(R.id.title);
        descriptionEditText = (EditText)findViewById(R.id.description);

        submit = (Button)findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                NoteModel note = new NoteModel(title, description);
                HomeActivity.noteList.add(note);

                writeToFile(note);
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void writeToFile(NoteModel note){
        String filename = "notes.txt";
        Gson gson = new Gson();
        String jsonNote = gson.toJson(note);
        try {
            File file = new File(getApplicationContext().getFilesDir(), filename);
            FileWriter fw = new FileWriter(file, true);
            fw.write(jsonNote + "\n");
            fw.close();
        }
        catch (IOException e){
            e.getMessage();
        }


    }
}
