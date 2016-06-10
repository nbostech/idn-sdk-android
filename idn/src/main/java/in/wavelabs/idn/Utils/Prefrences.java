package in.wavelabs.idn.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vineelanalla on 04/03/16.
 */
public class Prefrences {
    public Prefrences(Context context){

    }
    static SharedPreferences sharedPreferences;
    public SharedPreferences getSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public static long getUserId(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("userId", 0);
    }

    public static void  setUserId(Context context,long userId) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong("userId", userId).apply();
    }
    public static void setFirstName(Context context,String firstName) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("firstName", firstName).apply();
    }
    public static String getFirstName(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("firstName", "");
    }
    public static void setLastName(Context context,String lastName) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("lastName", lastName).apply();
    }
    public static String getLastName(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("lastName", "");
    }
    public static void setEmailId(Context context,String accessToken) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("emailId", accessToken).apply();
    }
    public static String getEmailId(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("emailId", "");
    }
    public static void setPhoneNumber(Context context,Long phoneNumber) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong("phoneNumber", phoneNumber).apply();
    }
    public static Long getPhoneNumber(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getLong("phoneNumber", 0);
    }
    public static void setClientToken(Context context, String clientToken) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("clientToken", clientToken).apply();
    }

    public static String getClientToken(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("clientToken", "");
    }
    public static String getDescription(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("description", "");
    }

    public static void setAccessToken(Context context, String accessToken) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("accessToken", accessToken).apply();
    }

    public static String getAccessToken(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("accessToken", "");
    }

    public static void setRefreshToken(Context context, String refreshToken) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("refreshToken", refreshToken).apply();
    }

    public static String getRefreshToken(Context context) {
        sharedPreferences = context.getSharedPreferences("library", Context.MODE_PRIVATE);
        return sharedPreferences.getString("refreshToken", "");
    }
}
