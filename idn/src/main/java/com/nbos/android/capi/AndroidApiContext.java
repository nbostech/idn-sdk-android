package com.nbos.android.capi;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.AssetManager;
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
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        String h="";
//        String packageName = androidContext.getPackageName();
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

//        Field[] fields =  R.string.class.getFields(); // or Field[] fields = R.string.class.getFields();
//        String str = "";
//
//        for(Field i : fields) {
//            if (i.getName().startsWith("module.")) {
//                int resId = androidContext.getResources().getIdentifier(i.getName(), "string", packageName);
//                str += i.getName().startsWith("module.");
//                if (resId != 0) {
//                    str += androidContext.getResources().getString(resId);
//                }
//                str += "\n";
//            }
//        }

//        int strings = androidContext.getResources().getIdentifier("","string",packageName);

        return h;
    }
}
