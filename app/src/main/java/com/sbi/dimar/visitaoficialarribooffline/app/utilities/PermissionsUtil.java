package com.sbi.dimar.visitaoficialarribooffline.app.utilities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface;
import com.sbi.dimar.visitaoficialarribooffline.app.utilities.network.NetworkUtil;

import java.util.ArrayList;

import static com.sbi.dimar.visitaoficialarribooffline.app.utilities.constants.AppConstantsInterface.PERMISSION_REQUEST_CODE_MULTIPLE;

/**
 * Created by Jenny Galindo
 * <p>
 * The PermissionsUtil class contains the methods that allow the user to request the required permissions for the use of the application
 */
public class PermissionsUtil {

    private static final String TAG = NetworkUtil.class.getName();
    private static ArrayList<String> requiredPermissionsList = null;
    private static ArrayList<String> requiredPermissionMsgs = null;


    public static void permissionReview(final Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList permissionConstantsList = new ArrayList<>();
            permissionConstantsList.add(AppConstantsInterface.PermissionConstants.PERMISSION_READ_PHONE_STATE);
            permissionConstantsList.add(AppConstantsInterface.PermissionConstants.PERMISSION_WRITE_EXTERNAL_STORAGE);
            permissionConstantsList.add(AppConstantsInterface.PermissionConstants.PERMISSION_READ_EXTERNAL_STORAGE);

            PermissionsUtil.requestPermission(activity, permissionConstantsList, PERMISSION_REQUEST_CODE_MULTIPLE);
        }
    }

    /**
     * @param activity:                 reference of current activity
     * @param requestedPermissionsList: list of the permissions requested
     * @param PERMISSION_REQUEST_CODE:  int type to check the corresponding response on onRequestPermissionsResult
     * @return boolean: true if all requested permissions are already granted else false
     * custom method to check, add and request for run time permissions
     */
    private static boolean requestPermission(final Activity activity, ArrayList<AppConstantsInterface.PermissionConstants> requestedPermissionsList, final int PERMISSION_REQUEST_CODE) {
        if (requiredPermissionsList == null) {
            requiredPermissionsList = new ArrayList<String>();
            requiredPermissionMsgs = new ArrayList<String>();
        } else {
            requiredPermissionsList.clear();
            requiredPermissionMsgs.clear();
        }

        if (requestedPermissionsList != null && !requestedPermissionsList.isEmpty()) {
            for (AppConstantsInterface.PermissionConstants requestedPermission : requestedPermissionsList) {

                switch (requestedPermission) {
                    case PERMISSION_READ_PHONE_STATE:
                        addPermission(Manifest.permission.READ_PHONE_STATE, activity);
                        break;
                    case PERMISSION_WRITE_EXTERNAL_STORAGE:
                        addPermission(Manifest.permission.READ_EXTERNAL_STORAGE, activity);
                        break;

                    case PERMISSION_READ_EXTERNAL_STORAGE:
                        addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, activity);
                        break;
                    default:
                        break;
                }
            }
        }

        if (requiredPermissionsList != null && !requiredPermissionsList.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.requestPermissions(requiredPermissionsList.toArray(new String[requiredPermissionsList.size()]), PERMISSION_REQUEST_CODE);
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param permission: the requested permission
     * @param activity:   current activity reference
     *                    custom method to check whether the requested permission has granted or not
     *                    If hasn't been granted add to requested list
     */
    private static void addPermission(final String permission, final Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            requiredPermissionsList.add(permission);
        }
    }

    /**
     * custom method to show alert dialog
     * context: reference of calling activity
     */
    public static void dialogOnPermissionDenied(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(context.getResources().getString(R.string.alert));
        alertDialogBuilder.setMessage(context.getResources().getString(R.string.denyPermissions));
        alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.appSetting),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                        Intent settingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri);
                        settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(settingsIntent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}

