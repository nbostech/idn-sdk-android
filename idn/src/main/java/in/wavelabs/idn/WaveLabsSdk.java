package in.wavelabs.idn;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.nbos.modules.identity.v0.TokenApiModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.wavelabs.idn.ConnectionAPI.NBOSCallback;
import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.utils.TokenPrefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

///**
// * Created by Vivek Kiran on 17/02/16.
// *
public class WaveLabsSdk {

    private WaveLabsSdk(Context context){

    }
    private static final String APPLICATION_ID_PROPERTY = "in.wavelabs.idn.WAVELABS_CLIENT_ID";
    private static final String APPLICATION_SECRET_PROPERTY = "in.wavelabs.idn.WAVELABS_CLIENT_SECRET";

    public static String clientId;
    private static String clientSecret;

    public static void SdkInitialize(final Context context) {

        getToken(context, getClientId(context, APPLICATION_ID_PROPERTY), getClientSecret(context, APPLICATION_SECRET_PROPERTY), new NBOSCallback<TokenApiModel>() {

            @Override
            public void onResponse(Response<TokenApiModel> response) {
                    TokenPrefrences.setClientToken(context, "Bearer " + response.body().getAccess_token());
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


    public static void getToken(Context context, String clientId, String clientSecret, final NBOSCallback<TokenApiModel> nbosCallback) {
        Call<TokenApiModel> call = StarterClient.getStarterAPI().getToken(clientId, clientSecret, "client_credentials");
        call.enqueue(new Callback<TokenApiModel>() {


            @Override
            public void onResponse(Call<TokenApiModel> call, Response<TokenApiModel> response) {
                nbosCallback.onResponse(response);
            }


            @Override
            public void onFailure(Call<TokenApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);


            }


        });
    }
    public static void refreshToken(Context context,String clientId, String clientSecret,final NBOSCallback<TokenApiModel> nbosCallback){
        String refreshToken = TokenPrefrences.getRefreshToken(context);
        Call<TokenApiModel> call = StarterClient.getStarterAPI().refreshToken(clientId, clientSecret, "refresh_token",refreshToken);
        call.enqueue(new Callback<TokenApiModel>() {


            @Override
            public void onResponse(Call<TokenApiModel> call, Response<TokenApiModel> response) {
                nbosCallback.onResponse(response);
            }


            @Override
            public void onFailure(Call<TokenApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);


            }


        });

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
