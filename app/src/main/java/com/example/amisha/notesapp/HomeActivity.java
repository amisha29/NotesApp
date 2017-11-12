package com.example.amisha.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    static ArrayList<NoteModel> noteList = new ArrayList<>();
    RecyclerView recyclerView;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteList = readFromFile();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<NoteModel> readFromFile(){
        String filename = "notes.txt";
        ArrayList<NoteModel> noteList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            File file = new File(getApplicationContext().getFilesDir(), filename);
            String line;
            BufferedReader br = new BufferedReader(new FileReader(file));
            while( (line = br.readLine()) != null ){
                NoteModel note = gson.fromJson(line, NoteModel.class);
                noteList.add(note);
            }
            br.close();
        }catch (Exception e){
            e.getMessage();
        }
        return noteList;
    }
}
