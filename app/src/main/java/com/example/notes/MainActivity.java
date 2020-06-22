package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static NoteDatabase noteDatabase;
    private FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noteDatabase = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "Notes")
                .allowMainThreadQueries()
                .build();


        recyclerView = findViewById(R.id.notelist);
        button = findViewById(R.id.floatbutton);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NoteAdapter();

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(layoutManager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteDatabase.NoteDAO().create();
                adapter.reload();

            }
        });
        adapter.reload();
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.reload();


    }
}