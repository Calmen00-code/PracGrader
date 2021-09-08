package com.calmen.pracgrader.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.calmen.pracgrader.users.Admin;
import com.calmen.pracgrader.database.DBSchema.AdminTable;

public class DBCursor extends CursorWrapper {
    public DBCursor (Cursor cursor) { super(cursor); }

    public Admin getAdmin() {
        String name = getString(getColumnIndex(AdminTable.Cols.NAME));

        return new Admin(name);
    }
}
