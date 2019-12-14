package com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.sbi.dimar.visitaoficialarribooffline.app.R;
import com.sbi.dimar.visitaoficialarribooffline.app.activities.MainActivity;
import com.sbi.dimar.visitaoficialarribooffline.app.services.database.DatabaseService;
import com.sbi.dimar.visitaoficialarribooffline.app.storage.SessionManager;

/**
 * Created by Jenny Galindo
 * <p>
 * The DialogUtil class contains methods that display an informative dialog to the user with whom you can perform a certain action
 */
public class DialogUtil {
    private static final String TAG = DialogUtil.class.getName();

    /**
     * custom method to show an alert dialog for information
     *
     * @param title:   String to set as alert dialog title
     * @param message: String to set as alert dialog msg
     */
    public static void alertDialogInfo(final Activity activity,
                                       String title, String message) {
        Log.i(TAG, "dialogInfo called");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, android.R.style.ThemeOverlay_Material_Dialog);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(activity.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /**
     * custom method to show an alert dialog for Sign Off and delete all saved data
     */
    public static void alertDialogActionSignOff(final Activity activity) {
        Log.i(TAG, "alertDialogAction called");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, android.R.style.ThemeOverlay_Material_Dialog);
        alertDialogBuilder.setTitle(R.string.ALERT);
        alertDialogBuilder.setMessage(R.string.sign_off);
        alertDialogBuilder.setPositiveButton(activity.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DatabaseService databaseService = DatabaseService.getInstance(activity);
                        databaseService.deleteAllInfo();
                        SessionManager sessionManager = SessionManager.getInstance(activity);
                        sessionManager.clearSharedPrefs();
                        activity.startActivity(new Intent(activity, MainActivity.class));
                    }
                });
        alertDialogBuilder.setNegativeButton(activity.getString(R.string.cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
}
