package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

/**
 * Created by Jenny Galindo on 26/4/2018
 * <p>
 * This class defines the standard operations that are carried out in the application for the persistence of the information.
 */
public abstract class AbstractFacade {

    private final java.lang.String entityClassTable;
    private final List<android.content.ContentValues> contentValues;
    private static final java.lang.String TAG = AbstractFacade.class.getName();
    private final String[] columnsNames;


    public AbstractFacade(java.lang.String entityClassTable, List<android.content.ContentValues> contentValues, String[] columnsNames) {
        this.entityClassTable = entityClassTable;
        this.contentValues = contentValues;
        this.columnsNames = columnsNames;
    }

    protected abstract SQLiteDatabase getSQLiteDatabaseManeger();

    public boolean create() {
        int countRecords = 0;
        try {
            for (int i = 0; i < contentValues.size(); i++) {
                // insert row
                long id = getSQLiteDatabaseManeger().insert(entityClassTable.toString(), null, contentValues.get(i));
                if (id != -1) {
                    countRecords++;
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "create ", e);
        }
        return countRecords == contentValues.size();
    }

    public boolean createIfDoesNotExist() {
        int countRecords = 0;
        try {
            for (int i = 0; i < contentValues.size(); i++) {
                // insert row
                long id = getSQLiteDatabaseManeger().insertWithOnConflict(entityClassTable.toString(), null, contentValues.get(i), SQLiteDatabase.CONFLICT_REPLACE);
                if (id != -1) {
                    countRecords++;
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "create ", e);
        }
        return countRecords == contentValues.size();
    }

    public boolean updateRecord(String whereClause, String[] whereArgs) {
        int countRecords = 0;
        try {
            for (int i = 0; i < contentValues.size(); i++) {
                // insert row
                long id = getSQLiteDatabaseManeger().update(entityClassTable.toString(), contentValues.get(i), whereClause, whereArgs);
                if (id != -1) {
                    countRecords++;
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "create ", e);
        }
        return countRecords == contentValues.size();
    }

    public boolean deleteAll() {
        try {
            return getSQLiteDatabaseManeger().delete(entityClassTable, null, null) >= 0;
        } catch (SQLException e) {
            Log.e(TAG, "delete", e);
            return false;
        }
    }

    public Cursor findAll() {
        Cursor cursor = null;
        try {

            cursor = getSQLiteDatabaseManeger().query(entityClassTable,
                    columnsNames,
                    null, null, null, null, null);
        } catch (SQLException e) {
            Log.e(TAG, "findAll ", e);
        }
        return cursor;
    }

    public Cursor findAllByArguments(String whereClause, String[] whereArgs) {
        Cursor cursor = null;
        try {

            cursor = getSQLiteDatabaseManeger().query(entityClassTable,
                    columnsNames,
                    whereClause, whereArgs, null, null, null);
        } catch (SQLException e) {
            Log.e(TAG, "findAllByArguments ", e);
        }
        return cursor;
    }

    public Cursor findAllByArgumentsWhitJoin(String tables, String[] columnas, String whereClause, String[] whereArgs) {
        Cursor cursor = null;
        try {

            cursor = getSQLiteDatabaseManeger().query(tables,
                    columnas,
                    whereClause, whereArgs, null, null, null);
        } catch (SQLException e) {
            Log.e(TAG, "findAllByArgumentsWhitJoin ", e);
        }
        return cursor;
    }
}
