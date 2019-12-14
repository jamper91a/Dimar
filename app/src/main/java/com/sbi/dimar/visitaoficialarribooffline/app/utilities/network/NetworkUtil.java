package com.sbi.dimar.visitaoficialarribooffline.app.utilities.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_CONNECTED;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_NOT_CONNECTED;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_TYPE_MOBILE;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_TYPE_WIFI;

/**
 * Created by Jenny Galindo
 * <p>
 * The NetworkUtil class defines the methods to obtain the current state of the internet network
 */
public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getName();


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return NET_TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return NET_TYPE_MOBILE;
        }
        return NET_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == NET_TYPE_WIFI) {
            status = "Wifi enabled," + NET_CONNECTED;
            Log.i(TAG, status);
        } else if (conn == NET_TYPE_MOBILE) {
            status = "Mobile data enabled," + NET_CONNECTED;
            Log.i(TAG, status);
        } else if (conn == NET_NOT_CONNECTED) {
            status = context.getString(R.string.netwotk_not_connected) + "," + NET_NOT_CONNECTED;
            Log.i(TAG, status);
        } else {
            status = context.getString(R.string.netwotk_not_connected) + "," + NET_NOT_CONNECTED;
            Log.i(TAG, status);
        }
        return status;
    }


    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = null;
        boolean isNetworkAvail = false;
        try {
            connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++)
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            Log.i(TAG, "Internet" + NetworkInfo.State.CONNECTED);
                            return true;
                        }
                }
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, AppConstantsInterface.ERROR, e);

        } finally {
            if (connectivity != null) {
                connectivity = null;
            }
        }
        return isNetworkAvail;
    }
}
