package com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.sbi.dimar.visitaoficialarribooffline.app.fragments.utils.ProgressDialogFragment;

/**
 * Created by Jenny Galindo on 25/5/2018
 * <p>
 * The ProgressDialogUtil class contains methods to handle the events that present
 * {@link ProgressDialogFragment} on screen to the user
 */
public class ProgressDialogUtil {
    private final FragmentManager mFragmentManager;
    private String message;

    public ProgressDialogUtil(FragmentManager fm) {
        // See if there are any DialogFragments from the FragmentManager
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(ProgressDialogFragment.TAG);

        // Remove if found
        if (prev != null) {
            ft.remove(prev);
            ft.commit();
        }
        this.mFragmentManager = fm;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }


    public void show() {
        ProgressDialogFragment dialogFragment = ProgressDialogFragment.newInstance(message);
        dialogFragment.setCancelable(false);
        dialogFragment.show(mFragmentManager, ProgressDialogFragment.TAG);
    }

    public void dismiss() {
        ProgressDialogFragment instance = ProgressDialogFragment.getInstance();
        if (instance != null) {
            instance.dismiss();
        }
    }

    /**
     * The following implements the builder API to simplify
     * creation and display of the dialog.
     */
    public static class Builder {
        // Required
        private final FragmentManager fm;
        private String message;


        public Builder(FragmentManager fm) {
            this.fm = fm;
        }

        /**
         * @see ProgressDialogUtil#setMessage(String)
         */
        public ProgressDialogUtil.Builder setMessage(String message) {
            if (!TextUtils.isEmpty(message)) {
                this.message = message;
            }
            return this;
        }

        /**
         * <p>Build and return a {@code Signature} object based on the previously
         * supplied parameters.</p>
         * <p>
         * <p>You should call {@link #show()} immediately after this.</p>
         *
         * @return
         */
        public ProgressDialogUtil build() {
            ProgressDialogUtil progressDialogUtil = new ProgressDialogUtil(fm);
            progressDialogUtil.setMessage(message);
            return progressDialogUtil;
        }
    }
}
