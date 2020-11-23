package com.example.findme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PositionManager {
    Context context;
    SQLiteDatabase db;

    public PositionManager(Context context,String file , int version) {
        this.context = context;
        open(file,version);
    }

    private void open(String file , int version) {
        PositionHelper positionHelper = new PositionHelper(context,file,null,version);
        db = positionHelper.getWritableDatabase();
    }
    public  long  insert(String nbr , String longitude , String latitude){
        long a = 1;
        ContentValues contentValues = new ContentValues();
        contentValues.put(PositionHelper.COL_LAT,latitude);
        contentValues.put(PositionHelper.COL_LONG,longitude);
        contentValues.put(PositionHelper.COL_NBR,nbr);
        a = db.insert(PositionHelper.TABLE_POSITION,null,contentValues);
        return a;
    }

    public ArrayList<Position> getAll() {
        ArrayList<Position> positions = new ArrayList<>();
        Cursor cursor = db.query(
                PositionHelper.TABLE_POSITION,
                new String[]{
                        PositionHelper.COL_ID,
                        PositionHelper.COL_NBR,
                        PositionHelper.COL_LAT,
                        PositionHelper.COL_LONG },
                null,
                null,
                PositionHelper.COL_NBR,
                null,
                PositionHelper.COL_ID);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String nbr = cursor.getString(1);
            String lat = cursor.getString(2);
            String lon = cursor.getString(3);
            Position p = new Position(id,lon,lat,nbr);
            positions.add(p);
            cursor.moveToNext();

        }
        return positions;
    }
    public int deleteNbr(String nbr){
        return  db.delete(PositionHelper.TABLE_POSITION,PositionHelper.COL_NBR+"="+nbr,null);
    }
}
