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

    public static String clientId;
    private static String clientSecret;
    public static void initialize(final Context context) {
        IdnSDK.init(new AndroidApiContext());
        HashMap map = new HashMap();
        map.put("client_id",getConfig(context,APPLICATION_ID_PROPERTY));
        map.put("client_secret",getConfig(context,APPLICATION_SECRET_PROPERTY));
        AbstractApiContext.get().setClientCredentials(map);
        getClientToken();
    }
    private static void getClientToken(){
        IdentityApi identityApi = IDS.getModuleApi("identity");
        IdentityRemoteApi remoteApi = identityApi.getRemoteApi();
        Map map = AbstractApiContext.get().getClientCredentials();
        String clientId = (String)map.get("client_id");
        String secret = (String)map.get("client_secret");
        //CallremoteApi.getToken(clientId,secret,"client_credentials");
    }

    public static String getConfig(Context context, String name) {
        String val = "";
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                String prop = String.valueOf(appInfo.metaData.getString(name));
                return appInfo.metaData.getString(prop);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(context, "Missing Config: " + name, Toast.LENGTH_SHORT).show();
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
        if(moduleName.equals("identity")) {
            return "http://api.qa1.nbos.in/";
        } else if(moduleName.equals("cafeteria")) {
            return "";
        }
        return "http://api.qa1.nbos.in/";
    }

}
