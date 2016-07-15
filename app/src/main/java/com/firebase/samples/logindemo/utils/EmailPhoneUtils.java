package com.firebase.samples.logindemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by arms on 7/11/16.
 */
public class EmailPhoneUtils {
    private static String TAG = "EmailPhoneUtils";

    public static boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches()) {
            ArmsLogs.i(TAG, "isEmailValid:" + String.valueOf(true));
            return true;
        }
        else{
        ArmsLogs.i(TAG, "isEmailValid:" + String.valueOf(false));
        return false;
    }
    }


    public static boolean isValidMobile(String phone)
    {
        if(phone.length() < 6 || phone.length() > 13){
        ArmsLogs.i(TAG,"isValidMobile:"+ String.valueOf(false));

        return false;
        }
        else {
            return  true;
        }
    }
}
