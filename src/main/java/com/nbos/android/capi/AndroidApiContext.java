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
import com.nbos.capi.api.v0.models.TokenApiModel;

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
    Properties configProperties;

    public AndroidApiContext(final Context context, String name) {
        super(name);
        this.androidContext = context;
    }

    public AndroidApiContext(final Context context) {
        this.androidContext = context;
    }


    public static void initialize(final Context context) {
        IdnSDK.init(new AndroidApiContext(context, "app"));
        IdnSDK.init(new AndroidApiContext(context, "api"));
    }

    protected SharedPreferences getSharedPreferences() {
        return androidContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // CLIENT TOKEN set/get
    public void setClientToken(TokenApiModel tokenApiModel) {
        saveModel("token.client", tokenApiModel);
        super.setClientToken(tokenApiModel);
    }

    public TokenApiModel getClientToken() {
        TokenApiModel tokenApiModel = super.getClientToken();
        if (tokenApiModel != null) {
            return tokenApiModel;
        }
        tokenApiModel = (TokenApiModel) readModel("token.client", TokenApiModel.class);
        super.setClientToken(tokenApiModel);  // lets save it in memory
        return tokenApiModel;
    }

    // USER TOKEN set/get
    public void setUserToken(String moduleName, TokenApiModel tokenApiModel) {
        saveModel("token.user", tokenApiModel);
        super.setUserToken(moduleName, tokenApiModel);
    }

    public TokenApiModel getUserToken(String moduleName) {
        TokenApiModel tokenApiModel = super.getUserToken(moduleName);
        if (tokenApiModel != null) {
            return tokenApiModel;
        }
        tokenApiModel = (TokenApiModel) readModel("token.user." + moduleName, TokenApiModel.class);
        super.setUserToken(moduleName, tokenApiModel);  // lets save it in memory
        return tokenApiModel;
    }


    public void init() {
        HashMap<String,String> map = new HashMap<>();
        String clientId = getConfig(APPLICATION_ID_PROPERTY);
        String clientSecret = getConfig(APPLICATION_SECRET_PROPERTY);
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        setClientCredentials(map);
    }

    protected void saveModel(String prefName, Object model) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        Gson gson = new Gson();
        String jsonModel = gson.toJson(model);
        sharedPreferences.edit().putString(prefName, jsonModel).apply();
    }

    protected Object readModel(String prefName, Class modelClass) {
        SharedPreferences sharedPreferences = getSharedPreferences();
        String jsonModel = sharedPreferences.getString(prefName, "");
        Gson gson = new Gson();
        return gson.fromJson(jsonModel, modelClass);
    }

    private String getConfig(String name) {
        String val = "";
        try {
            ApplicationInfo appInfo = androidContext.getPackageManager().getApplicationInfo(androidContext.getPackageName(), PackageManager.GET_META_DATA);
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
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {

        }
    }

    protected Properties getConfigProperties() {
        if (configProperties == null) {
            configProperties = new Properties();
        }
        AssetManager assetManager = androidContext.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open("config.properties");
            configProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configProperties;
    }

    public String getHost(String moduleName) {
        Properties properties = getConfigProperties();
        String h = properties.getProperty("module." + moduleName + ".host");

        if (h != null && h.length() > 0) {
            return h;
        }
        return "http://api.qa1.nbos.in/";
    }

}
