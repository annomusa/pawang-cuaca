package com.example.maunorafiq.pawangcuaca.mvp.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by maunorafiq on 11/10/16.
 */

public class CalendarHelper {

    // "dt_txt":"2016-11-09 12:00:00"

    public static List<String> getDayAndTime (String dt_txt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date date = new Date();

        try {
            date = sdf.parse(dt_txt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String day = new SimpleDateFormat("EEE", Locale.ENGLISH).format(date);
        String time = new SimpleDateFormat("HH", Locale.ENGLISH).format(date);
        int endTime = Integer.valueOf(time) + 3;
        time = time + "-" + String.valueOf(endTime);

        List<String> ret = new ArrayList<>();
        ret.add(day);
        ret.add(time);
        return ret;
    }
}
