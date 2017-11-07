package com.breuzon.tpsql.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by serial on 30/10/2017.
 *
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_MEMO = "memos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CONTENT = "content";

    private static final String DATABASE_NAME = "memos.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE =
            "create table " + TABLE_MEMO + " ( " +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_CONTENT + " text not null " +
                    ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Est appelée par le framework pour accéder à une BDD qui n'est pas encore créée
        sqLiteDatabase.execSQL(DATABASE_CREATE);
        String insertMemo = "insert into " + TABLE_MEMO + " (" + COLUMN_ID + ", " + COLUMN_CONTENT + ") " +
                "values (1, 'Memo 1');";
        sqLiteDatabase.execSQL(insertMemo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Est appelée si la version de la BDD est augmentée dans le code de votre application
        //Cette méthode permet de mettre à jour un schéma de BDD existant
        //ou de supprimer la BDD existante et la recréer par la méthode onCreate().

        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMO); //on drop d'abord les books
        onCreate(sqLiteDatabase);

    }
}
