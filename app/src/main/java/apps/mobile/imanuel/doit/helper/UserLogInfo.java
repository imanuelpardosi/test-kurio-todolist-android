package apps.mobile.imanuel.doit.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Imanuel on 13/04/2016.
 */
public class UserLogInfo {
    static final String ACCESS_TOKEN = "access_token";
    static final String USERNAME = "username";
    static final String USER_ID = "user_id";
    static final String LAST_CONNECT = "last_connect";

    static SharedPreferences getSharedPreferences(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    /* use after login*/
    public static void setAccessToken(Context context, String access_token){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(ACCESS_TOKEN,access_token);
        editor.commit();
    }

    /* use when check smthing that need to log in 1st */
    public static String getAccessToken(Context context){
        return getSharedPreferences(context).getString(ACCESS_TOKEN, "");
    }
    /* used for check last transaction (so we dont need to get all trans), because tbl transaction has field created at & updated at*/
    public static void setLastConnect(Context context, String lastConnect){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(LAST_CONNECT,lastConnect);
        editor.commit();
    }

    public static String getLastConnect(Context context){
        return getSharedPreferences(context).getString(LAST_CONNECT, "");
    }

    public static void setUserId(Context context, int user_id){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USER_ID,String.valueOf(user_id));
        editor.commit();
    }

    public static String getUserId(Context context){
        return getSharedPreferences(context).getString(USER_ID, "");
    }

    public static void setUsername(Context context, String username){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(USERNAME,username);
        editor.commit();
    }

    public static String getUsername(Context context){
        return getSharedPreferences(context).getString(USERNAME, "");
    }

    /* use after log out*/
    public static void clearData(Context context){
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.commit();
    }
}
