package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.to.PortInstallationTO;

import java.util.ArrayList;
import java.util.List;

import static com.sbi.dimar.visitaoficialarribooffline.app.database.entities.PortInstallationEntity.PORT_INSTALLATION_BD;

/**
 * Created by Jenny Galindo on 25/4/2018
 *
 * This class implements the interface {@link PortInstallationDAO}, is responsible for obtaining data from the database.
 */
public class PortInstallationDAOImpl extends AbstractFacade implements PortInstallationDAO {
    private static final String TAG = PracticalPilotDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;

    @Override
    protected SQLiteDatabase getSQLiteDatabaseManeger() {
        return sqLiteDatabase;
    }

    public PortInstallationDAOImpl(SQLiteDatabase sqLiteDatabase, List<PortInstallationTO> portInstallationTOS) {
        super(PORT_INSTALLATION_BD.table(), PORT_INSTALLATION_BD.columnsWhitValues(portInstallationTOS), PORT_INSTALLATION_BD.columnsNames());
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public List<PortInstallationTO> findAllRecords() {
        Cursor cursor = super.findAll();
        List<PortInstallationTO> portInstallationTOS = null;
        if (cursor != null && cursor.moveToFirst()) {
            portInstallationTOS = new ArrayList<>();
            do {
                PortInstallationTO portInstallationTO = cursorToRecordTO(cursor);
                if (portInstallationTO != null) {
                    portInstallationTOS.add(portInstallationTO);
                }
                Log.i(TAG, "findAllRecords");
            } while (cursor.moveToNext());
        }
        return portInstallationTOS;
    }

    @Override
    public PortInstallationTO cursorToRecordTO(Cursor cursor) {
        Log.i(TAG, "cursorToRecordTO");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new PortInstallationTO(cursor.getString(0), cursor.getString(1));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }
}
