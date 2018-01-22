package com.itraxhelper.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itraxhelper.models.MessEscortDataModel;

import java.util.ArrayList;

/**
 * Created by Shankar on 12/24/2017.
 */

public class MessDataSource {

    private SQLiteDatabase mDatabase;
    private DatabaseHandler mHandler;
    private Context mContext;
    private String mColumns[] = {DBConstants.ID, DBConstants.RF_ID,
            DBConstants.TYPE, DBConstants.TIME,
            DBConstants.DATE, DBConstants.YEAR,
            DBConstants.MONTH, DBConstants.MODE, DBConstants.All};

    public MessDataSource(Context context) {
        if (context != null) {
            mContext = context;
            mHandler = new DatabaseHandler(mContext);
        }
    }

    private void open() {
        if (mHandler != null) {
            mDatabase = mHandler.getWritableDatabase();
        }
    }

    private void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public long insertData(MessEscortDataModel model) {
        long insertValue = -1;

        ContentValues values = new ContentValues();
        values.put(DBConstants.RF_ID, model.getRFId());
        values.put(DBConstants.TYPE, model.getType());
        values.put(DBConstants.TIME, model.getTime());
        values.put(DBConstants.DATE, model.getDate());
        values.put(DBConstants.YEAR, model.getYear());
        values.put(DBConstants.MONTH, model.getMonth());
        values.put(DBConstants.MODE, model.getMode());
        values.put(DBConstants.All, model.getAll());
        open();
        insertValue = mDatabase.insert(DBConstants.TABLE_MESS_ESCORT_DATA, null,
                values);
        close();
        return insertValue;
    }


    /* Get all workouts models */
    public ArrayList<MessEscortDataModel> selectAll() {
        ArrayList<MessEscortDataModel> createSalesModels = null;
        open();
        Cursor cursor = mDatabase.query(DBConstants.TABLE_MESS_ESCORT_DATA, mColumns,
                null, null, null, null, null);
        if (cursor.getCount() > 0) {
            createSalesModels = new ArrayList<MessEscortDataModel>();
            while (cursor.moveToNext()) {
                MessEscortDataModel model = new MessEscortDataModel();
                model.setRFId(cursor.getString(cursor
                        .getColumnIndex(DBConstants.RF_ID)));
                model.setType(cursor.getString(cursor
                        .getColumnIndex(DBConstants.TYPE)));
                model.setTime(cursor.getString(cursor
                        .getColumnIndex(DBConstants.TIME)));
                model.setDate(cursor.getString(cursor
                        .getColumnIndex(DBConstants.DATE)));
                model.setYear(cursor.getString(cursor
                        .getColumnIndex(DBConstants.YEAR)));
                model.setMonth(cursor.getString(cursor
                        .getColumnIndex(DBConstants.MONTH)));
                model.setMode(cursor.getString(cursor
                        .getColumnIndex(DBConstants.MODE)));
                model.setAll(cursor.getString(cursor
                        .getColumnIndex(DBConstants.All)));
                createSalesModels.add(model);
            }
        }
        cursor.close();
        close();
        return createSalesModels;
    }


    public int deleteAll() {
        int deleteValue = -1;
        open();
        deleteValue = mDatabase.delete(DBConstants.TABLE_MESS_ESCORT_DATA, null, null);
        close();

        return deleteValue;
    }

    /* Get count */
    public int getDataCount() {
        int brandCount = -1;
        open();
        Cursor cursor = mDatabase.query(DBConstants.TABLE_MESS_ESCORT_DATA, new String[]{DBConstants.ID},
                null, null, null, null, null);
        if (cursor.getCount() > 0) {
            brandCount = cursor.getCount();
        }
        cursor.close();
        close();
        return brandCount;
    }

    public boolean isExistingAddress(String all) {
        boolean isRead;
        open();
        Cursor cursor = mDatabase.query(DBConstants.TABLE_MESS_ESCORT_DATA, mColumns,
                mColumns[8] + " = ?", new String[]{"" + all}, null, null, null);
        if (cursor.getCount() > 0) {
            isRead = true;
        } else {
            isRead = false;
        }
        cursor.close();
        close();
        return isRead;
    }
}