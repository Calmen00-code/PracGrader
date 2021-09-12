package com.calmen.pracgrader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.calmen.pracgrader.database.DBSchema.*;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "pracgrader.db";
    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + AdminTable.NAME + "(" +
                AdminTable.Cols.NAME + " TEXT," +
                AdminTable.Cols.PIN + " INTEGER);");

        db.execSQL("CREATE TABLE " + DBSchema.InstructorTable.NAME + "(" +
                InstructorTable.Cols.NAME + " TEXT," +
                InstructorTable.Cols.USERNAME + " TEXT," +
                InstructorTable.Cols.EMAIL + " TEXT," +
                InstructorTable.Cols.PIN + " INTEGER," +
                InstructorTable.Cols.COUNTRY_FLAG + " INTEGER," +
                InstructorTable.Cols.COUNTRY + " TEXT);");

        db.execSQL("CREATE TABLE " + DBSchema.StudentTable.NAME + "(" +
                StudentTable.Cols.NAME + " TEXT," +
                StudentTable.Cols.USERNAME + " TEXT," +
                StudentTable.Cols.EMAIL + " TEXT," +
                StudentTable.Cols.PIN + " INTEGER," +
                StudentTable.Cols.COUNTRY_FLAG + " INTEGER," +
                StudentTable.Cols.COUNTRY + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        throw new UnsupportedOperationException("Operation unavailable");
    }
}
