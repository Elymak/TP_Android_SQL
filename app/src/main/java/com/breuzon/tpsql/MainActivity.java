package com.breuzon.tpsql;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.breuzon.tpsql.bdd.dao.MemoDao;
import com.breuzon.tpsql.bdd.model.Memo;

import java.util.List;

public class MainActivity extends ListActivity {

    private MemoDao memoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoDao = new MemoDao(this);
        memoDao.open();
        refreshView();

        Button addAuthorButton = findViewById(R.id.addButton);
        addAuthorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAddMemoActivityOnClick(view);
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchDeleteMemoActivity(view);
            }
        });
    }

    public void refreshView(){
        List<Memo> values = memoDao.getAllModel();
        ArrayAdapter<Memo> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,values);
        setListAdapter(arrayAdapter);
    }

    public void launchAddMemoActivityOnClick(View view){
        Intent addMemoActivity = new Intent(MainActivity.this, AddMemoActivity.class);
        startActivity(addMemoActivity);
    }

    public void launchDeleteMemoActivity(View view){
        Intent deleteMemoActivity = new Intent(MainActivity.this, DeleteMemoActivity.class);
        startActivity(deleteMemoActivity);
    }

    @Override
    public void onResume(){
        memoDao.open();
        refreshView();
        super.onResume();
    }

    @Override
    protected void onPause() {
        memoDao.close();
        super.onPause();
    }
}
