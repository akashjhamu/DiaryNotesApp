package com.example.user.diary;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.diary.Model.NoteAdapter;
import com.example.user.diary.database.NoteEntity;
import com.example.user.diary.viewmodel.ListActivityViewMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private List<NoteEntity> mliste= new ArrayList<>();
    private ListActivityViewMode mviewmodel;
    private  NoteAdapter adapter;

    @BindView(R.id.note_recycleview)
    RecyclerView mrecyclerview;
    @OnClick(R.id.fab_add_note)
    void onFabClicked()
    {
        Intent intent=new Intent(this,EditorActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add_note);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        initviewmodel();
       // mliste= SampleDataProvider.getSampledate();
        ButterKnife.bind(this);
        iniiRecyclerView();




    }

    private void initviewmodel() {

        Observer<List<NoteEntity>>  noteobserver=new Observer<List<NoteEntity>>() {
            @Override
            public void onChanged(@Nullable List<NoteEntity> noteEntities) {

                mliste.clear();
                mliste.addAll(noteEntities);

                if(adapter==null)
                {
                    adapter=new NoteAdapter(MainActivity.this,mliste);
                    mrecyclerview.setAdapter(adapter);
                }
                else
                {
                    adapter.notifyDataSetChanged();
                }

            }
        };

     mviewmodel= ViewModelProviders.of(MainActivity.this).get(ListActivityViewMode.class);

     mviewmodel.mnoteList.observe(MainActivity.this,noteobserver);
    }


    private void iniiRecyclerView() {

        mrecyclerview.hasFixedSize();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mrecyclerview.setLayoutManager(linearLayoutManager);
         }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.add_sample_data:{

                addsampleData();
                return true;

            }



            case R.id.delete_sample_data:
            {
                deleteAllData();
                return true;
            }


        }


        return super.onOptionsItemSelected(item);
    }

    private void deleteAllData() {

        mviewmodel.deleteAllData();
    }

    private void addsampleData() {

      mviewmodel.addsampleData();


    }


}
