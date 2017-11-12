package com.example.amisha.notesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anisha Mascarenhas on 12-11-2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    ArrayList<NoteModel> noteList;

    public NoteAdapter(ArrayList<NoteModel> noteList) {
        this.noteList = noteList;
    }



    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View noteView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_note, parent, false);
        return new NoteViewHolder(noteView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteModel note = noteList.get(position);
        holder.titleView.setText(note.title);
        holder.descriptionView.setText(note.description);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView titleView, descriptionView;

        public NoteViewHolder(View view){
            super(view);
            titleView = (TextView)view.findViewById(R.id.title);
            descriptionView = (TextView)view.findViewById(R.id.description);
        }


    }

}
