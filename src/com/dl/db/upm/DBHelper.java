package com.dl.db.upm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by IntelliJ IDEA.
 * User: Ulises
 * Date: 16/03/12
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "list_data";
    private static final int DATABASE_VERSION = 2;
    public boolean DATABASE_EMPTY = true;

    public boolean isEMPTY() {
        return true;
    }

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        DBTables.onCreate(database);
        DATABASE_EMPTY = true;
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        DBTables.onUpgrade(database, oldVersion, newVersion);
        DATABASE_EMPTY = false;
    }
}

