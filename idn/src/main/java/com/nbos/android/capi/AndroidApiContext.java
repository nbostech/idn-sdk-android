package com.nbos.android.capi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nbos.capi.api.v0.AbstractApiContext;
import com.nbos.capi.api.v0.IdnSDK;
import com.nbos.capi.api.v0.InMemoryApiContext;
import com.nbos.capi.api.v0.TokenApiModel;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by vivekkiran on 6/25/16.
 */

public class AndroidApiContext extends InMemoryApiContext {

    private static final String PREF_NAME = "idn.token";
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
    public void setClientToken(TokenApiModel tokenApiModel) {
        SharedPreferences sharedPreferences = androidContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String stringUser = gson.toJson(tokenApiModel);
        sharedPreferences.edit().putString("token.client", stringUser).apply();
        super.setUserToken(tokenApiModel);
    }
    public TokenApiModel getClientToken() {
        TokenApiModel tokenApiModel = super.getUserToken();
        if(tokenApiModel!=null) {
            return tokenApiModel;
        }
        SharedPreferences sharedPreferences = androidContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token.client", "");
        Gson gson = new Gson();
        tokenApiModel = gson.fromJson(token, TokenApiModel.class);
        setUserToken(tokenApiModel);
        return tokenApiModel;
    }
//
//    // USER TOKEN set/get
    public void setUserToken(TokenApiModel tokenApiModel) {
        // save in SharedPrefererences
        SharedPreferences sharedPreferences = androidContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String stringUser = gson.toJson(tokenApiModel);
        sharedPreferences.edit().putString("token.user", stringUser).apply();
        super.setUserToken(tokenApiModel);
    }

    public TokenApiModel getUserToken() {
          TokenApiModel tokenApiModel = super.getUserToken();
          if(tokenApiModel!=null) {
              return tokenApiModel;
          }
        SharedPreferences sharedPreferences = androidContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token.user", "");
        Gson gson = new Gson();
        tokenApiModel = gson.fromJson(token, TokenApiModel.class);
        setUserToken(tokenApiModel);
        return tokenApiModel;
    }


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
        String h="";
        Properties properties = new Properties();;
        AssetManager assetManager = androidContext.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("config.properties");
            properties.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String key: properties.stringPropertyNames()){
            if(key.startsWith("module.")){
                String val = properties.getProperty(key);
                System.out.println("Values ::" + val);
                System.out.println("Module ::" +key);
                if(moduleName.contains("identity")) {
                    h="http://api.qa1.nbos.in/";
                } else if(moduleName.contains("cafeteria")) {
                    h="";
                }
            } else  {
                return "http://api.qa1.nbos.in/";
            }
        }
        return h;
    }
}
