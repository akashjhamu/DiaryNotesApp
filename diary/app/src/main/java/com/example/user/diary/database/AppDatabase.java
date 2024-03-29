package com.example.user.diary.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities={NoteEntity.class},version = 1)
@TypeConverters(DatabaseConverter.class)
public abstract class AppDatabase  extends RoomDatabase{

    public static  final String DATABASE_NAME="notedatabase.db";

    public static volatile AppDatabase instance;

    private static final Object LOCK=new Object();

    public  abstract  NoteDao noteDao();

    public static  AppDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            synchronized (LOCK){
                if(instance==null)
                {
                    instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).build();

                }
            }
        }
        return  instance;
    }

}
