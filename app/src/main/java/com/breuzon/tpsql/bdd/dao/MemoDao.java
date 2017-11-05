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

        long insertId = getDatabase().insert(MySQLiteHelper.TABLE_MEMO, null, values);
        Cursor cursor = getDatabase().query(MySQLiteHelper.TABLE_MEMO, allColumns,
                MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Memo newMemo = cursorToModel(cursor);
        cursor.close();

        return newMemo;
    }

    @Override
    public void deleteModel(Memo memo) {
        long id = memo.getId();
        getDatabase().delete(MySQLiteHelper.TABLE_MEMO, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
        System.out.println("Memo deleted with id: " + id);
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
