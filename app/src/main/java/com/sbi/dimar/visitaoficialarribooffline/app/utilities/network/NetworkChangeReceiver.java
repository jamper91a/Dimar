package com.sbi.dimar.visitaoficialarribooffline.app.utilities.network;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.MainActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.COMMA;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_CONNECTED;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_NOTIFICATION;
import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.NET_STATUS;

/**
 * Created by Jenny Galindo
 * <p>
 * The NetworkChangeReceiver class contains methods for handling internet network change events
 */
public class NetworkChangeReceiver extends BroadcastReceiver implements NetworkListener {
    private static final String TAG = NetworkChangeReceiver.class.getName();

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.i(TAG, "onReceive: IntentGetAction: " + intent.getAction());
        String status = NetworkUtil.getConnectivityStatusString(context);
        String[] networkStatus = status.split(COMMA);
        onNetworkStateChanges(NET_CONNECTED.equals(networkStatus[1].trim()));

        Intent homeIntent = new Intent(NET_STATUS);
        homeIntent.putExtra("STATUS", networkStatus[1]);
        context.sendBroadcast(homeIntent);

        SessionManager sessionManager = SessionManager.getInstance(context);
        sessionManager.setNetworkStatus(networkStatus[0].trim());
        sessionManager.setNetworkStatusValue(networkStatus[1].trim());

        if (!NET_CONNECTED.equals(networkStatus[1])) {
            sendNotificationNetwork(context, status);
        } else if (NET_CONNECTED.equals(networkStatus[1].trim())) {
            try {
                cancelNotificationNetwork(context);
                //CONEXION SERVICIOS
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void sendNotificationNetwork(Context context, String status) {
        String[] networkStatus = status.split(COMMA);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent intent = PendingIntent.getActivity(context, NET_NOTIFICATION, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        Bitmap icon1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        //Assign inbox style notification
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        if (NET_CONNECTED.equals(networkStatus[1])) {
            bigText.bigText(networkStatus[0]);
        } else {
            bigText.bigText(networkStatus[0]);
        }
        //bigText.bigText(context.getString(R.string.alert));
        bigText.setBigContentTitle(context.getString(R.string.app_name));

        //build notification
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(intent)
                        .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                        .setPriority(NotificationCompat.PRIORITY_MAX) //must give priority to High, Max which will considered as heads-up notification)
                        .setContentTitle(context.getString(R.string.app_name))
                        .setAutoCancel(true)
                        .setContentText("")
                        .setLargeIcon(icon1)
                        .setStyle(bigText);


        if (NET_CONNECTED.equals(networkStatus[1])) {
            mBuilder.setContentText(networkStatus[0]);
        } else {
            mBuilder.setContentText(networkStatus[0]);
        }
        // Gets an instance of the NotificationManager service
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //to post your notification to the notification bar
        mNotificationManager.notify(NET_NOTIFICATION, mBuilder.build());

    }

    private void cancelNotificationNetwork(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(NET_NOTIFICATION);
    }

    @Override
    public void onNetworkStateChanges(boolean status) {
        Log.i(TAG, "onNetworkStateChanges: Network Status: " + status);
    }
}
