package com.sbi.dimar.visitaoficialarribooffline.app.utilities.signature;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sbi.dimar.visitaoficialarribooffline.app.fragments.utils.SignatureFragment;
import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;

/**
 * Created by Jenny Galindo on 10/5/2018
 * <p>
 * The SignaturePickerUtil class contains the instance of the {@link SignatureFragment} library,
 * which is what allows the user to enter a signature of a representative
 */
public class SignaturePickerUtil {
    private final FragmentManager mFragmentManager;
    private SignatureListener mListener;
    private SignatureTO signatureTO;

    /**
     * Creates a new instance of {@code Signature}.
     *
     * @param fm The {@code FragmentManager} from the calling activity that is used
     *           internally to show the {@code DialogFragment}.
     */
    public SignaturePickerUtil(FragmentManager fm) {
        // See if there are any DialogFragments from the FragmentManager
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(SignatureFragment.TAG);

        // Remove if found
        if (prev != null) {
            ft.remove(prev);
            ft.commit();
        }

        mFragmentManager = fm;
    }

    /**
     * <p>Sets the listener that is used to inform the client when
     * the user selects a new date and time.</p>
     * <p>
     * <p>This must be called before {@link #show()}.</p>
     *
     * @param listener
     */
    public void setListener(SignatureListener listener) {
        mListener = listener;
    }

    /**
     * <p>Sets the listener that is used to inform the client when
     * the user selects a new date and time.</p>
     * <p>
     * <p>This must be called before {@link #show()}.</p>
     *
     * @param signatureTO
     */
    public void setSignatureTO(SignatureTO signatureTO) {
        this.signatureTO = signatureTO;
    }

    /**
     * Shows the dialog to the user. Make sure to call
     * {@link #setListener(SignatureListener listener)} before calling this.
     */
    public void show() {
        if (mListener == null) {
            throw new NullPointerException(
                    "Attempting to bind null listener to SlideDateTimePicker");
        }

        SignatureFragment dialogFragment =
                SignatureFragment.newInstance(mListener,
                        signatureTO);

        dialogFragment.show(mFragmentManager,
                SignatureFragment.TAG);
    }


    /*
     * The following implements the builder API to simplify
     * creation and display of the dialog.
     */
    public static class Builder {
        // Required
        private final FragmentManager fm;
        private SignatureListener listener;
        private SignatureTO signatureTO;


        public Builder(FragmentManager fm) {
            this.fm = fm;
        }

        /**
         * @see SignaturePickerUtil#setListener(SignatureListener)
         */
        public Builder setListener(SignatureListener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * @see SignaturePickerUtil#setSignatureTO(SignatureTO)
         */
        public Builder setSignatureTO(SignatureTO signatureTO) {
            this.signatureTO = signatureTO;
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
        public SignaturePickerUtil build() {
            SignaturePickerUtil signaturePickerUtil = new SignaturePickerUtil(fm);
            signaturePickerUtil.setListener(listener);
            signaturePickerUtil.setSignatureTO(signatureTO);
            return signaturePickerUtil;
        }
    }
}
