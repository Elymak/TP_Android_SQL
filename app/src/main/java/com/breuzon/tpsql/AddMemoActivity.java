package com.breuzon.tpsql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.breuzon.tpsql.bdd.dao.MemoDao;
import com.breuzon.tpsql.bdd.model.Memo;

public class AddMemoActivity extends AppCompatActivity {

    private MemoDao memoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        //TODO initialiser le Dao

        Button addAuthorButton = (Button) findViewById(R.id.addMemoButton);
        addAuthorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMemoOnClick(view);
            }
        });
    }

    public void addMemoOnClick(View view){

        //TODO récupérer l'éditeur et le contenu de l'éditeur


        //TODO créer le mémo


        finish();
    }

    @Override
    public void onResume(){
        memoDao.open();
        super.onResume();
    }

    @Override
    public void onPause(){
        memoDao.close();
        super.onPause();
    }
}
