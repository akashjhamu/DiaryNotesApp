package com.example.user.diary.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DatabaseConverter {

    @TypeConverter
    public static Long totimestamp(Date date)
    {
        return (date==null)? null: date.getTime();
    }
    @TypeConverter
    public static  Date toDate(Long timestamp)
    {
        return timestamp==null? null:new Date(timestamp);
    }
}
