package com.example.notes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public class NoteViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout row;
        private TextView textView;

        public NoteViewHolder(@NonNull View view) {
            super(view);
            row = view.findViewById(R.id.row);
            textView = view.findViewById(R.id.row_text);


            row.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Note current = (Note) row.getTag();
                    MainActivity.noteDatabase.NoteDAO().delete(current.id);
                    NoteAdapter.this.reload();
                    return true;
                }
            });

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note current = (Note) row.getTag();
                    Intent intent = new Intent(v.getContext(), NoteActivity.class);
                    intent.putExtra("id", current.id);
                    intent.putExtra("contents", current.contents);
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

    public List<Note> notes = new ArrayList<>();

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row,  parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.textView.setText(current.contents);
        holder.row.setTag(current);

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void reload() {
        notes = MainActivity.noteDatabase.NoteDAO().getAllNotes();
        notifyDataSetChanged();
    }


}
