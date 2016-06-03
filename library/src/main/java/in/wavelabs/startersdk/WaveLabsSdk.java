package in.wavelabs.startersdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import in.wavelabs.startersdk.ConnectionAPI.NBOSCallback;
import in.wavelabs.startersdk.ConnectionAPI.service.StarterClient;
import in.wavelabs.startersdk.DataModel.oauth.NewTokenResponseModel;
import in.wavelabs.startersdk.DataModel.validation.ValidationMessagesApiModel;
import in.wavelabs.startersdk.Utils.Prefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * Created by vineelanalla on 17/02/16.
// */
public class WaveLabsSdk {

    private WaveLabsSdk(Context context){

    }
    static Context context;
    public static final String APPLICATION_ID_PROPERTY = "in.wavelabs.startersdk.WAVELABS_CLIENT_ID";
    public static final String APPLICATION_SECRET_PROPERTY = "in.wavelabs.startersdk.WAVELABS_CLIENT_SECRET";

    public static String clientId;
    public static String clientSecret;

    public static void SdkInitialize(final Context context) {

        getToken(context, getClientId(context, APPLICATION_ID_PROPERTY), getClientSecret(context, APPLICATION_SECRET_PROPERTY), new NBOSCallback<NewTokenResponseModel>() {

            @Override
            public void onResponse(Response<NewTokenResponseModel> response) {
                    Prefrences.setClientToken(context, "Bearer " + response.body().getAccess_token());

                }

            @Override
            public void onFailure(Throwable t) {

            }

        });
    }


    public static String getClientId(Context context, String name) {

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                System.out.println(appInfo.metaData.getString(name));
                clientId = String.valueOf(appInfo.metaData.getString(name));

                return appInfo.metaData.getString(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
// if we can’t find it in the manifest, just return null
            Toast.makeText(context, "Client Id Missing! Have you declared it in your manifest file?", Toast.LENGTH_SHORT).show();
            System.out.println("Client Id Missing! Have you declared it in your manifest file?");
            e.printStackTrace();
        }
        return clientId;

    }

    public static String getClientSecret(Context context, String name) {

        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                System.out.println(appInfo.metaData.getString(name));
                clientSecret = String.valueOf(appInfo.metaData.getString(name));

                return appInfo.metaData.getString(name);
            }
        } catch (PackageManager.NameNotFoundException e) {
// if we can’t find it in the manifest, just return null
            Toast.makeText(context, "Client Secret Missing! Have you declared it in your manifest file?", Toast.LENGTH_SHORT).show();
            System.out.println("Client Secret Missing! Have you declared it in your manifest file?");
            e.printStackTrace();
        }
        return clientSecret;
    }


    public static void getToken(Context context, String clientId, String clientSecret, final NBOSCallback<NewTokenResponseModel> nbosCallback) {
        Call<NewTokenResponseModel> call = StarterClient.getStarterAPI().getToken(clientId, clientSecret, "client_credentials");
        call.enqueue(new Callback<NewTokenResponseModel>() {


            @Override
            public void onResponse(Call<NewTokenResponseModel> call, Response<NewTokenResponseModel> response) {
                nbosCallback.onResponse(response);
            }


            @Override
            public void onFailure(Call<NewTokenResponseModel> call, Throwable t) {


            }


        });
    }


    public static Prefrences getPrefrences(Context context){
        Prefrences prefrences = new Prefrences(context);
        prefrences.getSharedPreferences(context);
        return prefrences;
    }

    public static void generateKeyHash(Context context, String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    packageName,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {

        }
    }
}
