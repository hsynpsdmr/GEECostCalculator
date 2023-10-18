package com.example.geecostcalculator.service;

import static com.example.geecostcalculator.constants.Constant.DATABASE_NAME;
import static com.example.geecostcalculator.constants.Constant.DATABASE_VERSION;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.geecostcalculator.constants.Constant;
import com.example.geecostcalculator.ui.detail.TablesInfo;
import com.example.geecostcalculator.ui.home.CostData;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NOTE_CREATE =
            "CREATE TABLE " + TablesInfo.DataEntry.TABLE_NAME + " (" +
                    TablesInfo.DataEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TablesInfo.DataEntry.COLUMN_SERVICE + " TEXT, " +
                    TablesInfo.DataEntry.COLUMN_METER + " TEXT, " +
                    TablesInfo.DataEntry.COLUMN_DATE + " TEXT, " +
                    TablesInfo.DataEntry.COLUMN_COST + " TEXT " +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NOTE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TablesInfo.DataEntry.TABLE_NAME);

        onCreate(db);
    }

    public void addData(CostData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TablesInfo.DataEntry.COLUMN_SERVICE, data.getServiceNumber().trim());
        cv.put(TablesInfo.DataEntry.COLUMN_METER, data.getMeter().trim());
        cv.put(TablesInfo.DataEntry.COLUMN_DATE, data.getDate().trim());
        cv.put(TablesInfo.DataEntry.COLUMN_COST, data.getCost().trim());

        long result = db.insert(TablesInfo.DataEntry.TABLE_NAME, null, cv);

        if (result > -1)
            Log.i("DatabaseHelper", "Data başarıyla kaydedildi");
        else
            Log.i("DatabaseHelper", "Data kaydedilemedi");

        db.close();
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TablesInfo.DataEntry.TABLE_NAME, TablesInfo.DataEntry.COLUMN_ID + "=?", new String[]{String.valueOf(id)});

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<CostData> getDataList(String serviceNumber) {

        ArrayList<CostData> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                TablesInfo.DataEntry.COLUMN_ID,
                TablesInfo.DataEntry.COLUMN_SERVICE,
                TablesInfo.DataEntry.COLUMN_METER,
                TablesInfo.DataEntry.COLUMN_DATE,
                TablesInfo.DataEntry.COLUMN_COST,
        };

        Cursor c = db.query(TablesInfo.DataEntry.TABLE_NAME, projection, null, null, null, null, null);
        while (c.moveToNext()) {
            if(c.getString(c.getColumnIndex(TablesInfo.DataEntry.COLUMN_SERVICE)).equals(serviceNumber)){
                data.add(new CostData(
                        c.getInt(c.getColumnIndex(TablesInfo.DataEntry.COLUMN_ID)),
                        c.getString(c.getColumnIndex(TablesInfo.DataEntry.COLUMN_SERVICE)),
                        c.getString(c.getColumnIndex(TablesInfo.DataEntry.COLUMN_METER)),
                        c.getString(c.getColumnIndex(TablesInfo.DataEntry.COLUMN_DATE)),
                        c.getString(c.getColumnIndex(TablesInfo.DataEntry.COLUMN_COST))
                ));
            }

        }

        c.close();
        db.close();

        return data;
    }
}
