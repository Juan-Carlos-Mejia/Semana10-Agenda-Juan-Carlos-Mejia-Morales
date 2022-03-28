package com.example.semana10_agenda_juan_carlos_mejia_morales.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "agenda.db";
    public static final String TABLE_CONTACTOS = "T_CONTACTOS";
    public static final String TABLE_CONTACTOS2 = "T_CONTACTOS2";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase base) {
        base.execSQL("CREATE TABLE " + TABLE_CONTACTOS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL, TELEFONO TEXT NOT NULL, EMAIL TEXT NOT NULL)");
        base.execSQL("CREATE TABLE " + TABLE_CONTACTOS2 + "(id INTEGER PRIMARY KEY AUTOINCREMENT, NOMBRE TEXT NOT NULL, TELEFONO TEXT NOT NULL, EMAIL TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_CONTACTOS);
        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_CONTACTOS2);
        onCreate(sqLiteDatabase);
    }
}
