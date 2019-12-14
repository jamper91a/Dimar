package com.sbi.dimar.visitaoficialarribooffline.app.utilities.dialogs;

import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.sbi.dimar.visitaoficialarribooffline.app.R;

import java.util.Date;

/**
 * Created by Jenny Galindo on 8/5/2018
 * <p>
 * The DateTimeDialogUtil class contains the instance of the {@link SlideDateTimePicker} library,
 * which is the one that allows the selection of dates and times
 */
public class DateTimeDialogUtil {
    private static final String TAG = DateTimeDialogUtil.class.getSimpleName();

    public static SlideDateTimePicker getBuildSlideDateTimePicker(FragmentManager fragmentManager) {
        Log.i(TAG, "call getBuildSlideDateTimePicker");

        SlideDateTimePicker buildSlideDateTimePicker = new SlideDateTimePicker.Builder(fragmentManager)
                .setInitialDate(new Date())
                //.setMinDate(minDate)
                .setMaxDate(new Date())
                .setIs24HourTime(true)
                .setTheme(SlideDateTimePicker.HOLO_DARK)
                .setIndicatorColor(R.color.colorAccent)
                .build();
        return buildSlideDateTimePicker;

    }
}
