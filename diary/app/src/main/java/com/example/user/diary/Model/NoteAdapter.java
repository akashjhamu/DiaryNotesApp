package com.example.user.diary.Model;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.diary.EditorActivity;
import com.example.user.diary.database.NoteEntity;

import com.example.user.diary.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.user.diary.utils.constant.NOTE_ID_KEY;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context mcontext;
    List<NoteEntity> mnote;


    public NoteAdapter(Context mcontext,List<NoteEntity> mnote)
    {
        this.mcontext=mcontext;
        this.mnote=mnote;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=LayoutInflater.from(mcontext).inflate(R.layout.note_item_layout,parent,false);

      return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    final NoteEntity mnoteEntity=mnote.get(position);
    holder.textview.setText(mnoteEntity.getText());

    holder.fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(mcontext, EditorActivity.class);
            intent.putExtra(NOTE_ID_KEY,mnoteEntity.getId());
            mcontext.startActivity(intent);
        }
    });



    }

    @Override
    public int getItemCount() {
        return mnote.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder
    {
       @BindView(R.id.note_text)
        TextView textview;
       @BindView(R.id.fab_edit_note)
        FloatingActionButton fab;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }
}

