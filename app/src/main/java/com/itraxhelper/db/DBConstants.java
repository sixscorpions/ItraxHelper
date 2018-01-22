package com.itraxhelper.db;

/**
 * Created by Shankar on 12/24/2017.
 */

public class DBConstants {

    public static final String TABLE_MESS_ESCORT_DATA = "mess_escort_data";

    public static final String ID = "_Id";
    public static final String RF_ID = "RFId";
    public static final String TYPE = "Type";
    public static final String TIME = "time";
    public static final String DATE = "Date";
    public static final String YEAR = "Year";
    public static final String MONTH = "Month";
    public static final String MODE = "Mode";
    public static final String All = "ALL_DATA";

    public static final String CREATE_TABLE_MESS_ESCORT_DATA = "CREATE TABLE IF NOT EXISTS " +
            TABLE_MESS_ESCORT_DATA
            + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RF_ID + "  TEXT NOT NULL, "
            + TYPE + "  TEXT NOT NULL, "
            + TIME + "  TEXT NOT NULL, "
            + DATE + "  TEXT NOT NULL, "
            + YEAR + "  TEXT NOT NULL, "
            + MONTH + "  TEXT NOT NULL, "
            + All + "  TEXT NOT NULL, "
            + MODE + "  TEXT NOT NULL"
            + ")";
}
