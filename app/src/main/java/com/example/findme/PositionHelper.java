package com.example.findme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PositionHelper extends SQLiteOpenHelper {

    public static  final String TABLE_POSITION = "position";
    public static  final String COL_ID = "id";
    public static  final String COL_LONG = "longitude";
    public static  final String COL_LAT = "latitude";
    public static  final String COL_NBR = "nbr";
    public static  String FILE = "my_file.db";
    public static  int DB_VERSION = 1;


    String req = "create table "+ TABLE_POSITION +" (" +
            COL_ID +" integer primary key autoincrement ," +
            COL_NBR +" text not null," +
            COL_LONG +" text not null," +
            COL_LAT +" text not null" +
            ")";
    public PositionHelper(@Nullable Context context,
                          @Nullable String name, // file name .db
                          @Nullable SQLiteDatabase.CursorFactory factory, //null
                          int version
    ) {

        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+ TABLE_POSITION);
        onCreate(db);
    }
}
