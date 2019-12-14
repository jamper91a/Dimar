package com.sbi.dimar.visitaoficialarribooffline.app.utilities.signature;

import com.sbi.dimar.visitaoficialarribooffline.app.to.SignatureTO;

/**
 * Created by Jenny Galindo on 10/5/2018
 * <p>
 * The SignatureListener class defines the method to obtain the signature that was just registered by each representative
 */
public abstract class SignatureListener {

    /**
     * Informs the client when the user presses "OK"
     * and selects information to representant
     */
    public abstract void onSignatureSet(SignatureTO signatureTO);
}
