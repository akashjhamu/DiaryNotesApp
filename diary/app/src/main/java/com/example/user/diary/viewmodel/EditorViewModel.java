package com.example.user.diary.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.user.diary.database.AppRepository;
import com.example.user.diary.database.NoteEntity;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EditorViewModel extends AndroidViewModel {

    public MutableLiveData<NoteEntity> mLiveNote  = new MutableLiveData<>();

    private AppRepository  mAppRepository;
    private Executor mexecotor= Executors.newSingleThreadExecutor();


    public EditorViewModel(@NonNull Application application) {
        super(application);
        mAppRepository=AppRepository.getInstance(application.getApplicationContext());
    }


    public void loadNote(final int noteId) {
      mexecotor.execute(new Runnable() {
          @Override
          public void run() {
              NoteEntity noteEntity=mAppRepository.loadNote(noteId);
              mLiveNote.postValue(noteEntity);
          }
      });

    }

    public void saveAndExit(String noteText) {

        NoteEntity mnotentity=mLiveNote.getValue();

        if(mnotentity==null)
        {
          if(TextUtils.isEmpty(noteText.trim()))
          {
              return;
          }
          else
          {
              mnotentity=new NoteEntity(new Date(),noteText.trim());
          }
        }
        else
        {
            mnotentity.setText(noteText.trim());
            mnotentity.setDate(new Date());


        }
        mAppRepository.insertNote(mnotentity);
    }
}
