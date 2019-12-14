package com.sbi.dimar.visitaoficialarribooffline.app.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.database.entities.AgencyEntity;
import com.sbi.dimar.visitaoficialarribooffline.app.to.AgencyTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jenny Galindo on 25/4/2018
 * <p>
 * This class implements the interface {@link AgencyDAO}, is responsible for obtaining data from the database.
 */
public class AgencyDAOImpl implements AgencyDAO {
    private static final String TAG = AgencyDAOImpl.class.getSimpleName();
    private final SQLiteDatabase sqLiteDatabase;


    public AgencyDAOImpl(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public List<AgencyTO> getAgencies() {
        List<AgencyTO> agencyTOS = null;
        try {
            Cursor cursor = sqLiteDatabase.query(AgencyEntity.TABLE_DIM_OFF_AGE.getValue(),
                    new String[]{AgencyEntity.COLUMN_DIM_OFF_AGE_NIT.getValue(), AgencyEntity.COLUMN_DIM_OFF_AGE_RAZON_SOCIAL.getValue()},
                    null, null, null, null, null);
            if (cursor.moveToFirst()) {
                agencyTOS = new ArrayList<>();
                do {
                    AgencyTO agencyTO = cursorToAgency(cursor);
                    if (agencyTO != null) {
                        agencyTOS.add(agencyTO);
                    }
                    Log.i(TAG, "getAgencies");
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (SQLException e) {
            Log.e(TAG, "getAgencies ", e);
        }
        return agencyTOS;
    }

    public boolean saveAgencies(List<AgencyTO> agencyTOS) {
        int countAgencies = 0;
        try {
            for (int i = 0; i < agencyTOS.size(); i++) {
                ContentValues values = new ContentValues();
                values.put(AgencyEntity.COLUMN_DIM_OFF_AGE_NIT.getValue(), agencyTOS.get(i).getNit());
                values.put(AgencyEntity.COLUMN_DIM_OFF_AGE_RAZON_SOCIAL.getValue(), agencyTOS.get(i).getRazonSocial());
                // insert row
                long id = sqLiteDatabase.insert(AgencyEntity.TABLE_DIM_OFF_AGE.getValue(), null, values);
                if (id != -1) {
                    countAgencies=countAgencies+1;
                }else{
                    countAgencies=countAgencies+1;
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "saveAgencies ", e);
            countAgencies++;
        } catch (Exception e){

        }
        return countAgencies == agencyTOS.size();
    }

    public boolean deleteAgencies() {
        try {
            return sqLiteDatabase.delete(AgencyEntity.TABLE_DIM_OFF_AGE.getValue(), null, null) >= 0;
        } catch (SQLException e) {
            Log.e(TAG, "saveAgencies ", e);
            return false;
        }
    }


    public AgencyTO cursorToAgency(Cursor cursor) {
        Log.i(TAG, "cursorToAgency");
        try {
            if (cursor != null && cursor.getCount() > 0) {
                return new AgencyTO(cursor.getString(0), cursor.getString(1));
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

}
