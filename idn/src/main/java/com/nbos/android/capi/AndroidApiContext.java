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
import com.nbos.capi.modules.identity.v0.IdentityApi;
import com.nbos.capi.modules.identity.v0.IdentityRemoteApi;
import com.nbos.capi.modules.ids.v0.IDS;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

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
        IdentityApi identityApi = IDS.getModuleApi("identity");
        identityApi.getClientToken();
    }


    public void init() {
        HashMap map = new HashMap();
        // TODO: @Vivek figure out why this value isn't being retrieved from config.
//        map.put("client_id",getConfig(androidContext,APPLICATION_ID_PROPERTY));
//        map.put("client_secret",getConfig(androidContext,APPLICATION_SECRET_PROPERTY));
        map.put("client_id","sample-app-client");
        map.put("client_secret","sample-app-secret");
        AbstractApiContext.get().setClientCredentials(map);
    }

    public String getConfig(String name) {
        String val = "";
        try {
            ApplicationInfo appInfo = androidContext.getPackageManager().getApplicationInfo(
                    androidContext.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                String prop = String.valueOf(appInfo.metaData.getString(name));
                return appInfo.metaData.getString(prop);
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

    public String getHost(String moduleName) {
        String h="";
        if(moduleName.equals("identity")) {
            h="http://api.qa1.nbos.in/";
        } else if(moduleName.equals("cafeteria")) {
            h="";
        }
        if(h.length()>0)return h;
        else return "http://api.qa1.nbos.in/"; // default to idn
    }

}
