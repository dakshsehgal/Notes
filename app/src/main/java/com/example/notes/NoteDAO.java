package com.example.notes;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDAO {
    @Query("INSERT INTO Notes (contents) VALUES ('New note')")
    void create();

    @Query("SELECT * FROM Notes")
    List<Note> getAllNotes();

    @Query("UPDATE Notes SET contents = :contents WHERE id = :id")
    void save(String contents, int id);

    @Query("DELETE FROM Notes WHERE id = :id")
    void delete(int id);

}
