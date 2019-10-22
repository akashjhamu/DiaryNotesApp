package com.example.user.diary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.diary.database.NoteEntity;
import com.example.user.diary.utils.constant;
import com.example.user.diary.viewmodel.EditorViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditorActivity extends AppCompatActivity {

    private EditorViewModel editorViewModel;
    @BindView(R.id.edit_note_text)
    TextView mEditText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_check);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        initviewmodel();
    }

    private void initviewmodel() {

        Observer<NoteEntity> mnoteEntity=new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {

                if(noteEntity!=null)
                mEditText.setText(noteEntity.getText());

            }
        } ;
        editorViewModel= ViewModelProviders.of(this).get(EditorViewModel.class);
        editorViewModel.mLiveNote.observe(this,mnoteEntity);

        Bundle bundle=getIntent().getExtras();

        if(bundle==null)
            setTitle("NEW NOTE");
        else
        {
            setTitle("EDIT NOTE");
            int noteId=bundle.getInt(constant.NOTE_ID_KEY);
            editorViewModel.loadNote(noteId);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            saveAndExit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {

        editorViewModel.saveAndExit(mEditText.getText().toString());
    }
}
