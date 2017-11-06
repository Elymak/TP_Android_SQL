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
        //TODO créer un ContentValues et l'insérer dans la base + retourner le nouveau memo avec son id

        return null;
    }

    @Override
    public void deleteModel(Memo memo) {
        //TODO BONUS : rendre cette méthode générique

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
        //TODO appeler la methode de la super classe avec les bons paramètres
        return null;
    }

}
