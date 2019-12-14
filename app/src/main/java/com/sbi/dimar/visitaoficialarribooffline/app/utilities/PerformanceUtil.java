package com.sbi.dimar.visitaoficialarribooffline.app.utilities;

/**
 * Created by Jenny Galindo
 * <p>
 * The PerformanceUtil class contains the methods that allow improving the performance of the application
 */
public class PerformanceUtil {

    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }
}
