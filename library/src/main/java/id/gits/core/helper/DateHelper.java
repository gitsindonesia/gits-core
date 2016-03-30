package id.gits.core.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ibun on 12/07/15.
 */
public class DateHelper {
    public final static String PATTERN_YYYYMMDD = "yyyy-MM-dd";
    public final static String PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String PATTERN_EEEEDDMMMYYYY = "EEEE, dd MMM yyyy";
    public final static String PATTERN_DDMMMYYYY = "dd MMM yyyy";
    public final static String PATTERN_DDMMYY = "dd.MM.yy";
    public final static String PATTERN_DDMMMYYYYHHMM = "dd MMM yyyy, HH:mm";

    public static SimpleDateFormat SDF_DDMMYY = new SimpleDateFormat(PATTERN_DDMMYY, Locale.getDefault());
    public static SimpleDateFormat SDF_DDMMMYYYY = new SimpleDateFormat(PATTERN_DDMMMYYYY, Locale.getDefault());
    public static SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat(PATTERN_YYYYMMDD, Locale.getDefault());
    public static SimpleDateFormat SDF_YYYYMMDDHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    public static SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat(PATTERN_YYYYMMDDHHMMSS, Locale.getDefault());

    public static String formatToYYYYMMDD(Date date) {
        return SDF_YYYYMMDD.format(date);
    }

    public static String formatToDDMMYY(String date){
        Date myFormat;
        try {
            myFormat = SDF_YYYYMMDDHHMMSS.parse(date);
            SimpleDateFormat SDF_MYFORMAT = new SimpleDateFormat(PATTERN_DDMMYY);
            String myDate = SDF_MYFORMAT.format(myFormat);
            return myDate;
        } catch (Exception e){
            return null;
        }
    }

    public static Date dateParseToDate(String dateInput, String old_format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(old_format, Locale.getDefault());
            Date date = sdf.parse(dateInput);
            return date;
        } catch (Exception e) {
            return null;
        }
    }

    public static String dateParseToString(Date date, String newFormat) {
        SimpleDateFormat format = new SimpleDateFormat(newFormat, Locale.getDefault());
        String result = format.format(date);
        return result;
    }


    public static String dateParseToString(String dateInput, String old_format, String formatDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(old_format, Locale.getDefault());
            Date date;
            date = sdf.parse(dateInput);
            SimpleDateFormat format = new SimpleDateFormat(formatDate, Locale.getDefault());
            String result = format.format(date);
            return result;
        } catch (ParseException e) {
            return dateInput;
        }
    }
}
