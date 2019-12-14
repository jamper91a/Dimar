package com.sbi.dimar.visitaoficialarribooffline.app.storage;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.DBConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JENNY GALINDO on 18/09/2016.
 * <p>
 * This subclass DatabaseHelper is responsible for opening the database if it exists, creating it if it is not and updating it as necessary.
 * Transactions are used to ensure that the database is always in a sensitive state.
 */
public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private final Context context;
    private SQLiteDatabase dataBase;
    private final String APP_DATA_PATH = "";


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        this.context = context;
        //call this if API level is lower than 17  String appDataPath = "/data/data/" + context.getPackageName() + "/databases/"
       /* if (Build.VERSION.SDK_INT <= 17) {
            APP_DATA_PATH = "/data/data/" + context.getPackageName();
        } else {
            APP_DATA_PATH = context.getApplicationInfo().dataDir;

        }*/
        Log.i(TAG, "Contructor BD: " + DBConstants.DATABASE_NAME);
    }

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    /*@Override
    public void onCreate(SQLiteDatabase db) {
    }

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
*/

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws SQLException {
        boolean dbExist = false;
        try {
            dbExist = checkDataBase();
        } catch (SQLiteException e) {
            Log.e(TAG, "Error en " + TAG + ".createDataBase database dose not exist", e);
        }
        if (dbExist) {
            //do nothing - database already exist
        } else {
            copyDatabaseFileInApp();
        }
        //By calling this method and empty database will be created into the default system path
        //of your application so we are gonna be able to overwrite that database with our database.
        this.getReadableDatabase();
    }


    public boolean openDataBase() {
        try {
            String mPath = context.getApplicationInfo().dataDir + DBConstants.DATABASE_NAME_SUB_PATH + DBConstants.DATABASE_NAME;
            //Note that this method assumes that the db file is already copied in place
            dataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.OPEN_READWRITE);
            return dataBase != null;
        } catch (SQLException e) {
            Log.e(TAG, "Error en " + TAG + ".openDataBase", e);
            return false;
        }
    }

    @Override
    public synchronized void close() {
        if (dataBase != null) {
            dataBase.close();
        }
        super.close();
    }

    private void copyDatabaseFileInApp() {
        File dbFolder = new File(APP_DATA_PATH + "/databases");//Make sure the /databases folder exists
        if (!dbFolder.exists()) {
            dbFolder.mkdir();//This can be called multiple times.
        }
        File dbFilePath = new File(APP_DATA_PATH + DBConstants.DATABASE_NAME_SUB_PATH + DBConstants.DATABASE_NAME);
        try {
            InputStream inputStream = context.getAssets().open(DBConstants.DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream(dbFilePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "Error en " + TAG + ".copyDatabaseFileInApp", e);
        }
    }

    private void backupdDatabase() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            if (sd.canWrite()) {
                Date now = new Date();
                String currentDBPath = context.getApplicationInfo().dataDir + "/databases/" + DBConstants.DATABASE_NAME;
                SimpleDateFormat dateFormat = new SimpleDateFormat(AppConstantsInterface.DATE_HOUR_FORMAT);
                String backupDBPath = DBConstants.DATABASE_NAME_BACKUP + dateFormat.format(now) + ".db";

                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                Log.i(TAG, "backupDB=" + backupDB.getAbsolutePath());
                Log.i(TAG, "sourceDB=" + currentDB.getAbsolutePath());

                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error en " + TAG + ".backupdDatabase", e);
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;
        try {
            String currentDBPath = context.getApplicationInfo().dataDir + DBConstants.DATABASE_NAME_SUB_PATH + DBConstants.DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(currentDBPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Log.e(TAG, "Error en " + TAG + ".verificarCreacionDB", e);
        }

        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

}

