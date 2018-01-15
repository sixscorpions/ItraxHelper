package com.itraxhelper.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "db_icue_helper";

    private static final String TABLE_NAME_1 = "TB_SWIPED_DETAILS_IN";
    private static final String TABLE_NAME_2 = "TB_SWIPED_DETAILS_OUT";
    private static final String TABLE_NAME_3 = "TB_SWIPED_DETAILS_MESS";

    private static final String COLUMN = "swipedetails";


    public DBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_1 + " (" + COLUMN
                + " text)");
        db.execSQL("create table " + TABLE_NAME_2 + " (" + COLUMN
                + " text)");
        db.execSQL("create table " + TABLE_NAME_3 + " (" + COLUMN
                + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_3);
        onCreate(db);

    }

    public void insertINSwipedDetailsFromDB(String swipe) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN, swipe);
        db.insert(TABLE_NAME_1, null, contentValues);
    }

    public void insertOUTSwipedDetailsFromDB(String swipe) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN, swipe);
        db.insert(TABLE_NAME_2, null, contentValues);
    }

    public void insertMESSSwipedDetailsFromDB(String swipe) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN, swipe);
        db.insert(TABLE_NAME_3, null, contentValues);
    }

    public JSONArray getINSwipedDetailsFromDB() {

        Cursor cursor = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("select * from " + TABLE_NAME_1, null);

            JSONArray array = new JSONArray();

            if (cursor.moveToFirst()) {
                do {
                    try {
                        JSONObject jsonObject = new JSONObject(cursor.getString(0));
                        array.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } while (cursor.moveToNext());
            }

            return array;

        } finally {
            if (cursor != null)
                cursor.close();
        }


    }

    public JSONArray getOUTSwipedDetailsFromDB() {

        Cursor cursor = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("select * from " + TABLE_NAME_2, null);

            JSONArray array = new JSONArray();

            if (cursor.moveToFirst()) {
                do {
                    try {
                        JSONObject jsonObject = new JSONObject(cursor.getString(0));
                        array.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } while (cursor.moveToNext());
            }

            return array;

        } finally {
            if (cursor != null)
                cursor.close();
        }


    }

    public JSONArray getMESSSwipedDetailsFromDB() {

        Cursor cursor = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("select * from " + TABLE_NAME_3, null);

            JSONArray array = new JSONArray();

            if (cursor.moveToFirst()) {
                do {
                    try {
                        JSONObject jsonObject = new JSONObject(cursor.getString(0));
                        array.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } while (cursor.moveToNext());
            }

            return array;

        } finally {
            if (cursor != null)
                cursor.close();
        }


    }

    public Integer deleteINSwipeDetailsFromDB() {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME_1, null, null);
    }

    public Integer deleteOUTSwipeDetailsFromDB() {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME_2, null, null);
    }

    public Integer deleteMESSSwipeDetailsFromDB() {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME_3, null, null);
    }

}
