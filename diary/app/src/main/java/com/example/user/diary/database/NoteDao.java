package com.example.user.diary.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<NoteEntity>  noteEntities);

    @Delete
    void deleteNote(NoteEntity noteEntity);

    @Query("SELECT * FROM  notes  where id = :id ")
    NoteEntity getNoteId(int id);

    @Query("SELECT * FROM notes ORDER BY date desc")
    LiveData<List<NoteEntity>> getALLNote();

    @Query("DELETE  FROM notes ")
    int deleteAllNotes();

    @Query("SELECT COUNT(*) FROM notes")
    int getcount();
}
