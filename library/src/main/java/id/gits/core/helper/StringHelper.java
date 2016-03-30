package id.gits.core.helper;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.RawRes;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Cipher;

import id.gits.core.R;


/**
 * Created by ibun on 21/03/16.
 */
public class StringHelper {
    public static String formatCurrency(Context context, double value, boolean isIdr) {
        String langCode = "en";
        Locale locale;
        if (isIdr) {
            langCode = "in";
        }

        locale = new Locale(langCode);
        NumberFormat formatter = NumberFormat.getNumberInstance(locale);
        String priceStr = formatter.format(value);
        return priceStr;
    }

    public static String encrypt(Context ctx, String data, @RawRes int raw) throws Exception {
        // reads the public key stored in a file
        InputStream is = ctx.getResources().openRawResource(raw);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        List<String> lines = new ArrayList<>();
        String line = null;
        while ((line = br.readLine()) != null)
            lines.add(line);

        // removes the first and last lines of the file (comments)
        if (lines.size() > 1 && lines.get(0).startsWith("-----")
                && lines.get(lines.size() - 1).startsWith("-----")) {
            lines.remove(0);
            lines.remove(lines.size() - 1);
        }

        // concats the remaining lines to a single String
        StringBuilder sb = new StringBuilder();
        for (String aLine : lines)
            sb.append(aLine);
        String keyString = sb.toString();

        // converts the String to a PublicKey instance
        byte[] keyBytes = Base64.decode(keyString.getBytes("utf-8"),
                Base64.DEFAULT);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(spec);

        // decrypts the message
        byte[] encryptedText = null;
        Cipher cipher = Cipher
                .getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        encryptedText = cipher.doFinal(data.getBytes());
        return Base64.encodeToString(encryptedText, Base64.DEFAULT);
    }

    public static Map<String, String> getQueryMapFromUrl(String url) {
        Uri uri = Uri.parse(url);
        String query = uri.getQuery();
        String[] pairs = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                map.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String getCountdownString(Context context, Date date, boolean withSecond) {
        String str = "";
        long expiredTime = date.getTime() - new Date().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(expiredTime);
        long hour = expiredTime / 1000 / 60 / 60;
        long minute = expiredTime / 1000 / 60 % 60;
        long second = expiredTime / 1000 % 60;
        str = hour + " "
                + context.getResources().getQuantityString(R.plurals.plural_hour, (int) hour)
                + " " + minute + " "
                + context.getResources().getQuantityString(R.plurals.plural_minute, (int) minute);
        if (withSecond) {
            str = str + " " + second + " "
                    + context.getResources().getQuantityString(R.plurals.plural_second, (int) second);
        }
        return str;
    }

    public static String implodeList(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i != list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

}
