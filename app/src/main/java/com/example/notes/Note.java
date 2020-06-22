package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes")
public class Note {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "contents")
    public String contents;

    public Note(Integer id, String contents) {
        this.id = id;
        this.contents = contents;
    }


}
