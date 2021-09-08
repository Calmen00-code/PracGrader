package com.calmen.pracgrader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.calmen.pracgrader.database.DBSchema.AdminTable;

import com.calmen.pracgrader.users.Admin;

import java.io.Serializable;
import java.util.ArrayList;

public class DBModel {
    SQLiteDatabase db;

    public void load(Context context) {
        this.db = new DBHelper(context).getWritableDatabase();
    }

    public void addAdmin(Admin admin) {
        ContentValues cv = new ContentValues();
        cv.put(AdminTable.Cols.NAME, admin.getName());
        db.insert(AdminTable.NAME, null, cv);
    }

    public ArrayList<Admin> getAllAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        Cursor cursor = db.query(AdminTable.NAME, null, null,null,null,null,null);
        DBCursor dbCursor = new DBCursor(cursor);

        try {
            dbCursor.moveToFirst();
            while (!dbCursor.isAfterLast()) {
                admins.add(dbCursor.getAdmin());
                dbCursor.moveToNext();
            }
        } finally {
            dbCursor.close();
        }
        return admins;
    }
}
