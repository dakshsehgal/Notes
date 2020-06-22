package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class NoteActivity extends AppCompatActivity {
    private EditText editor;
    private String contents;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editor = findViewById(R.id.editor);
        Intent intent = getIntent();
        contents = intent.getStringExtra("contents");
        id = intent.getIntExtra("id", 0);
        editor.setText(contents);
    }

    @Override
    protected void onPause() {
        super.onPause();


        MainActivity.noteDatabase.NoteDAO().save(editor.getText().toString(), id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editor.setText(contents);


    }
}