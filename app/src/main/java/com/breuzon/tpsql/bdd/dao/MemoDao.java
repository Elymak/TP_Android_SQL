package com.breuzon.tpsql.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.breuzon.tpsql.bdd.MySQLiteHelper;
import com.breuzon.tpsql.bdd.model.Memo;

import java.util.List;

/**
 * Created by serial on 30/10/2017.
 *
 */
public class MemoDao extends MyAbstractDao<Memo> {

    private static String[] allColumns = new String[] {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CONTENT
    };

    public MemoDao(Context context) {
        super(context);
    }

    @Override
    public Memo createNewModel(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CONTENT, memo.getContent());

        return insertModel(MySQLiteHelper.TABLE_MEMO, values, allColumns);
    }

    @Override
    public void deleteModel(Memo memo) {
        delete(MySQLiteHelper.TABLE_MEMO, memo.getId());
    }

    @Override
    protected Memo cursorToModel(Cursor cursor) {
        Memo memo = new Memo();
        memo.setId(cursor.getLong(0));
        memo.setContent(cursor.getString(1));
        return memo;
    }

    @Override
    public List<Memo> getAllModel() {
        return getAllModel(MySQLiteHelper.TABLE_MEMO, allColumns);
    }

}
