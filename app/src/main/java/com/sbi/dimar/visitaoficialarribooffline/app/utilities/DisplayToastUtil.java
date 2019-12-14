package com.sbi.dimar.visitaoficialarribooffline.app.utilities;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jenny Galindo
 * <p>
 * The DisplayToastUtil class contains the methods that show an informative message to the user
 */
public class DisplayToastUtil implements Runnable {
    private final Context mContext;
    private final String mText;

    public DisplayToastUtil(Context mContext, String text) {
        this.mContext = mContext;
        this.mText = text;
    }

    public void run() {
        Toast.makeText(mContext, mText, Toast.LENGTH_LONG).show();
    }
}
