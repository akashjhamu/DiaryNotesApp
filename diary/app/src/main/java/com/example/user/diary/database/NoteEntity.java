package com.example.user.diary.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "notes")
public class NoteEntity {
    @PrimaryKey (autoGenerate=true)
    private   int id;

    private Date date;

    private String text;

    @Ignore
    public NoteEntity()
    {

    }

    @Ignore
    public NoteEntity(Date date,String text)
    {
        this.text=text;
        this.date=date;
    }
    public  NoteEntity(int id,Date date,String text)
    {
        this.text=text;
        this.id=id;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
