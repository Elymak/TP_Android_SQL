package com.breuzon.tpsql.bdd.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.breuzon.tpsql.bdd.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serial on 30/10/2017.
 *
 * Cette classe est utile si on veut créer une base avec plusieurs tables
 *
 */

public abstract class MyAbstractDao<M> {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    MyAbstractDao(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public abstract M createNewModel(M model);

    public abstract void deleteModel(M model);

    List<M> getAllModel(String tableName, String[] columns){
        List<M> models = new ArrayList<>();

        Cursor cursor = database.query(tableName, columns, null, null, null, null, null);
        //Cursor cursor = database.rawQuery("select * from ?", new String[]{tableName});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            M comment = cursorToModel(cursor);
            models.add(comment);
            cursor.moveToNext();
        }
        //fermeture du curseur
        cursor.close();
        return models;
    }

    M insertModel(String tableName, ContentValues values, String[] columns){

        long insertId = getDatabase().insert(tableName, null, values);
        Cursor cursor = getDatabase().query(tableName, columns,
                MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        M newModel = cursorToModel(cursor);
        cursor.close();

        return newModel;
    }

    void delete(String tableName, long id){
        getDatabase().delete(tableName, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
        System.out.println("Memo deleted with id: " + id);
    }

    protected abstract M cursorToModel(Cursor cursor);

    public abstract List<M> getAllModel();

    private SQLiteDatabase getDatabase() {
        return database;
    }
}
