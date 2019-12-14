package com.sbi.dimar.visitaoficialarribooffline.app.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sbi.dimar.visitaoficialarribooffline.app.to.UserTO;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface;

import java.lang.reflect.Type;

/**
 * Created by JENNY GALINDO
 * <p>
 * This SessionManager class represents the key-value storage for the handling of small information,
 * that is why the user's information, network connection, and the data stored or not in the database is handled by this class.
 */
public class SessionManager {
    // Shared Preferences
    private final SharedPreferences prefs;
    private static SessionManager instance;
    private Context context;
    private static SharedPreferences.Editor editor;


    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public SessionManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(AppConstantsInterface.APP_PREFERENCIAS, Context.MODE_PRIVATE);
        this.editor = prefs.edit();
    }

    // Clearing all data from Shared Preferences
    public void clearSharedPrefs() {
        editor.clear();
        editor.commit();
    }

    public void saveUser(UserTO userTO) {
        SharedPreferences.Editor editor = prefs.edit();
        String toJson = new Gson().toJson(userTO);
        editor.putString(AppConstantsInterface.PREF_USR, toJson);
        //editor.commit();
        editor.apply();
    }

    public UserTO getUser() {
        if (prefs.contains(AppConstantsInterface.PREF_USR)) {
            String json = prefs.getString(AppConstantsInterface.PREF_USR, null);
            Type type = new TypeToken<UserTO>() {
            }.getType();
            return new Gson().fromJson(json, type);
        } else {
            return null;
        }

    }

    // to store and retrieve the login status of the user
    public boolean getIsUserLoggedIn() {
        return prefs.getBoolean(AppConstantsInterface.PREF_USR_LOGGED, false);
    }

    public void setIsUserLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(AppConstantsInterface.PREF_USR_LOGGED, isLoggedIn);
        editor.commit();
    }

    // to store and retrieve the network  status
    public String getNetworkStatus() {
        return prefs.getString(AppConstantsInterface.PREF_NET_STATUS, "");
    }

    public void setNetworkStatus(String status) {
        editor.putString(AppConstantsInterface.PREF_NET_STATUS, status);
        editor.commit();
    }

    public String getNetworkStatusValue() {
        return prefs.getString(AppConstantsInterface.PREF_NET_STATUS_VALUE, "");
    }

    public void setNetworkStatusValue(String status) {
        editor.putString(AppConstantsInterface.PREF_NET_STATUS_VALUE, status);
        editor.commit();
    }

    // to store and retrieve the data status
    public boolean getIsDataSaved() {
        return prefs.getBoolean(AppConstantsInterface.PREF_DAT_SAVED, false);
    }

    public void setIsDataSaved(boolean isDataSaved) {
        editor.putBoolean(AppConstantsInterface.PREF_DAT_SAVED, isDataSaved);
        editor.commit();
    }
}
