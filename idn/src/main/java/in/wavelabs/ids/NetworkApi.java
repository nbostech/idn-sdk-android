package in.wavelabs.ids;

import java.io.IOException;

import in.wavelabs.idn.ConnectionAPI.OkHttpNBOSCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by vivekkiran on 6/14/16.
 */

public class NetworkApi {
    String host;

    public void setHost(String host) {
        this.host=host;
        if(host.endsWith("/")) {
            this.host = host.substring(0,host.length()-1);
        }
    }
    public Request.Builder newRequest(String api) {
        String endpoint = api;
        if(!api.startsWith("http")) {

            endpoint = host+api;
        }
        return new Request.Builder().url(endpoint);
    }

    public void get(Request request, final OkHttpNBOSCallback callback) {
        OkHttpClient client = getOkHttpClient();
        Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onFailure(call,e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    callback.onResponse(call,response);
                }
            });

    }


    protected OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    public Response post(Request request) {
        return null;
    }

    public Response put(Request request) {
        return null;
    }
}
