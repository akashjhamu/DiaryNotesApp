package com.example.user.diary.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.util.Log;

import com.example.user.diary.utils.SampleDataProvider;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class AppRepository {
    private static AppRepository ourInstance ;

    private AppDatabase mappdatabase;

    private Executor mExecutor=Executors.newSingleThreadExecutor();

    public LiveData<List<NoteEntity>> mnoteList;

    public static AppRepository getInstance(Context context) {
        return ourInstance= new AppRepository(context);
    }

    private AppRepository(Context context) {


        mappdatabase=AppDatabase.getInstance(context);
        mnoteList= getallnotes();
    }

    public void addsampleData() {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {

                mappdatabase.
                        noteDao().insertAll(SampleDataProvider.getSampledate());

            }
        });

    }

    public LiveData<List<NoteEntity>> getallnotes()
    {
        return mappdatabase.noteDao().getALLNote();
    }

    public void deleteAllData() {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
               int i= mappdatabase.noteDao().deleteAllNotes();

                Log.d("Tag","Number of node deleted= "+i);
            }
        });
    }

    public NoteEntity loadNote(int noteId) {

        return  mappdatabase.noteDao().getNoteId(noteId);

    }

    public void insertNote(final NoteEntity mnotentity) {

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mappdatabase.noteDao().insertNote(mnotentity);
            }
        });
    }
}
