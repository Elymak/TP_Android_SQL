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

        memoDao = new MemoDao(this);
        memoDao.open();

        final Button addAuthorButton = (Button) findViewById(R.id.addMemoButton);
        addAuthorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMemoOnClick(view);
            }
        });
    }

    public void addMemoOnClick(View view){

        EditText authorNameEditText = (EditText) findViewById(R.id.editText);
        String authorName = authorNameEditText.getText().toString();
        System.out.println("New Memo : " + authorName);

        Memo memo = new Memo();
        memo.setContent(authorName);

        Memo newMemo = memoDao.createNewModel(memo);

        if(newMemo != null){
            Toast.makeText(this,"Le mémo a été ajouté", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Erreur : le mémo n'a pas été ajouté", Toast.LENGTH_LONG).show();
        }

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
