package in.wavelabs.idn.ConnectionAPI.service;


import java.util.HashMap;

import in.wavelabs.idn.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Vivek Kiran on 1/1/2016.
 */
public class StarterClient {
    private static final HashMap<Class, Object> apiClients = new HashMap<>();
    private static final String ROOT = Constants.MAIN;
    static {
        setupRestClient(StarterApi.class);
    }


    public static StarterApi getStarterAPI(){
        return (StarterApi) apiClients.get(StarterApi.class);
    }

    private static void setupRestClient(Class type) {


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiClients.put(type, retrofit.create(type));

    }
}
