package com.breuzon.tpsql;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.breuzon.tpsql.bdd.dao.MemoDao;
import com.breuzon.tpsql.bdd.model.Memo;

import java.util.List;

public class DeleteMemoActivity extends ListActivity {

    MemoDao memoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_memo);

        //TODO initialiser le Dao

        //TODO créer un écouteur d'évenement et appeler le delete de l'item à supprimer

        Button finishButton = findViewById(R.id.returnButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void deleteItem(AdapterView<?> adapterView, int i) {
        //TODO récupérer le mémo à supprimer et appeler le Dao

    }

    public void refreshView(){
        List<Memo> values = memoDao.getAllModel();
        ArrayAdapter<Memo> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,values);
        setListAdapter(arrayAdapter);
    }

    @Override
    public void onPause(){
        memoDao.close();
        super.onPause();
    }

    @Override
    public void onResume(){
        memoDao.open();
        super.onResume();
    }
}
