package com.sbi.dimar.visitaoficialarribooffline.app.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;


/**
 * Created by Jenny Galindo on 10/5/2018
 * <p>
 * The BitmapSerializableUtil class contains the methods that allow the byte serialization of the signature and PDF images
 */
public class BitmapSerializableUtil {

    private static final String TAG = BitmapSerializableUtil.class.getSimpleName();

    public static String bitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    /**
     * @param encodedString
     * @return bitmap (from given string)
     */
    public static Bitmap stringToBitMap(String encodedString) {
        try {
            Log.i(TAG, "stringToBitMap");
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en  stringToBitMap");
            return null;
        }
    }

    public static byte[] bitMapToByte(Bitmap bitmap) {
        Log.i(TAG, "bitMapToByte");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static byte[] stringToByte(String encodedString) {
        try {
            Log.i(TAG, "stringToByte");
            return Base64.decode(encodedString, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage() != null ? e.getMessage() : "Error en  stringToByte");
            return null;
        }
    }

    public static String byteToString(byte[] bytes) {
        Log.i(TAG, "byteToString");
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}