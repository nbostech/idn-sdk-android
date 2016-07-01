package com.nbos.android.capi;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.IdnSDK;
import com.nbos.capi.api.v0.InMemoryApiContext;
import com.nbos.capi.api.v0.TokenApiModel;
import com.nbos.capi.modules.ids.v0.IDS;
import com.nbos.capi.modules.ids.v0.IdsRemoteApi;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import io.swagger.models.Swagger;
import io.swagger.parser.Swagger20Parser;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vivekkiran on 6/25/16.
 */

public class AndroidApiContext extends InMemoryApiContext {

    private static final String APPLICATION_ID_PROPERTY = "in.nbos.idn.CLIENT_ID";
    private static final String APPLICATION_SECRET_PROPERTY = "in.nbos.idn.CLIENT_SECRET";

    Context androidContext;

    public AndroidApiContext(final Context context) {
        this.androidContext=context;
    }


    public static void initialize(final Context context) {
        IdnSDK.init(new AndroidApiContext(context));
    }

    // CLIENT TOKEN set/get
//    public void setClientToken(TokenApiModel tokenApiModel) {
//        //store.put("token.client",tokenApiModel);
//        TokenPrefrences.setClientToken(androidContext);
//        // save in SharedPrefererences
//    }
//    public TokenApiModel getClientToken() {
//        //return (TokenApiModel)store.get("token.client");
//        // retrieve from Shared preferences
//    }
//
//    // USER TOKEN set/get
//    public void setUserToken(TokenApiModel tokenApiModel) {
//        //store.put("token.user",tokenApiModel);
//        // save in SharedPrefererences
//    }
//    public TokenApiModel getUserToken() {
//        //return (TokenApiModel)store.get("token.user");
//        // retrieve from Shared preferences
//    }



    public void init() {
        HashMap map = new HashMap();
        String clientId = getConfig(APPLICATION_ID_PROPERTY);
        String clientSecret = getConfig(APPLICATION_SECRET_PROPERTY);
        map.put("client_id",clientId);
        map.put("client_secret",clientSecret);
        AbstractApiContext.get().setClientCredentials(map);
    }

    private String getConfig(String name) {
        String val = "";
        try {
            ApplicationInfo appInfo = androidContext.getPackageManager().getApplicationInfo(
                    androidContext.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                val = String.valueOf(appInfo.metaData.getString(name));
                return val;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(androidContext, "Missing Config: " + name, Toast.LENGTH_SHORT).show();
        }
        return val;
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

    @Override
    public TokenApiModel getToken(String s) {
        return null;
    }

    // TODO: @Vivek to get this from confi, similar to client id & secret
    public String getHost(String moduleName) {
            //TODO: Get Host From Module Name
            final String[] host = new String[1];
            IdsRemoteApi idsRemoteApi = IDS.getIDSApi();
            idsRemoteApi.getModApiJson(moduleName).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Swagger20Parser parser = new Swagger20Parser();
                    try {
                        Swagger sw = parser.parse(response.body().string());
                        System.out.println(sw.getHost());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
            return host[0];
        }
}
