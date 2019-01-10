package com.sample.pooja.sampleapplication.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.sample.pooja.sampleapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {
    private static String TAG = "AppUtils";
    static ProgressDialog dialog;

    /**
     * Method to check the network connection
     *
     * @return Return the connection status
     */
    public static Boolean checkNetworkConnection(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo wifiInfo, mobileInfo;
        try {
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiInfo.getState() == NetworkInfo.State.CONNECTED || mobileInfo.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        } catch (Exception exception) {
            Log.e(TAG, "CheckConnectivity Exception: " + exception.getMessage());
        }

        return false;
    }

    /**
     * Method to validate email address
     *
     * @param email The email id to be validated
     */

    public static boolean isEmailValid(String email) {
        boolean isValid;
        String email_address = email.replaceAll("\\s", "");
        email_address = email_address.replaceAll(" ", "");
        String expression = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email_address);
        isValid = email_address.length() > 0 && matcher.matches();
        return isValid;
    }

    public static void showLoadingProgress(Context context) {
        dialog = new ProgressDialog(context, R.style.MyTheme);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();

    }


    /**
     * hide Loading Progress(Transparent Theme)
     *
     * @return null
     */
    public static void hideLoadingProgress() {
        if (dialog != null) {
            if (dialog.isShowing())
                dialog.dismiss();
        }
    }
}
