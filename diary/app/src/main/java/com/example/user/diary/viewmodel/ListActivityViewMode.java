package com.example.user.diary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.user.diary.database.AppRepository;
import com.example.user.diary.database.NoteEntity;
import com.example.user.diary.utils.SampleDataProvider;

import java.util.List;

public class ListActivityViewMode extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mnoteList;
    private  AppRepository mapprepository;

    public ListActivityViewMode(@NonNull Application application) {
        super(application);
       mapprepository= AppRepository.getInstance(application.getApplicationContext());
       mnoteList=mapprepository.mnoteList;
    }

    public void addsampleData() {

        mapprepository.addsampleData();
    }

    public void deleteAllData() {
        mapprepository.deleteAllData();
    }
}
