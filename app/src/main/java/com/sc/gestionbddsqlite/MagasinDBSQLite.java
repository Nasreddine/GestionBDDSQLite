package com.sc.gestionbddsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MagasinDBSQLite extends SQLiteOpenHelper {

    public static final String TABLE_MAGASIN = "magasin";
    public static final String COL_ID = "id_magasin";
    public static final String COL_NOM = "nom";
    public static final String COL_ADR = "adresse";
    public static final String COL_LAT = "latitude";
    public static final String COL_LONG = "longitude";
    public static final String COL_LOGO = "logo";

    private static final String CREATE_BDD =
            "CREATE TABLE " + TABLE_MAGASIN + " ("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COL_NOM + " varchar NOT NULL, "
                    + COL_ADR + " varchar, "
                    + COL_LAT + " DECIMAL, "
                    + COL_LONG + " DECIMAL, "
                    + COL_LOGO + " string " + ");";


    public static final int VERSION = 1;
    public static final String NOM_BDD = "chapitre.db";


    public MagasinDBSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_MAGASIN);
        onCreate(db);
    }

}
