package com.example.user.diary.utils;

import com.example.user.diary.database.NoteEntity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SampleDataProvider {

    private static final String Sample_Text1="A simple note";
    private static final String Sample_Text2="A simple note Note \n With two Line";
    private static final String Sample_Text3="A simple note Note  With Line Multiple line. here it show about that the sample can hold multiple line";


    private  static Date getdate(int diff)
    {
        GregorianCalendar calender=new GregorianCalendar();
        calender.add(Calendar.MILLISECOND,diff);
        return calender.getTime();
    }

    public static List<NoteEntity> getSampledate()
    {
        List<NoteEntity> noteEntities=new ArrayList<>();

        noteEntities.add(new NoteEntity(getdate(0),Sample_Text1));
        noteEntities.add(new NoteEntity(getdate(-1),Sample_Text2));
        noteEntities.add(new NoteEntity(getdate(-2),Sample_Text3));
        return  noteEntities;

    }
}
