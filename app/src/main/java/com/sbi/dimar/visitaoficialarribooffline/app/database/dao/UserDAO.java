package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;

/**
 * Created by Jenny Galindo on 25/4/2018
 *
 * @deprecated use in {@link SessionManager}
 */
public class UserDAO {
    private static final String TAG = UserDAO.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    public UserDAO(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public UserTO getUser() {
        UserTO userTO = null;
        try {
            String query = "SELECT DIM_OFF_USR_ID_LOGIN FROM DIM_OFF_USR";
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    String value = cursor.getString(0);
                    userTO = new UserTO();
                    userTO.setIdLogin(value);
                    Log.i(TAG, "getUser: " + value);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e(TAG, "getUser ", e);
        }
        return userTO;
    }
}
