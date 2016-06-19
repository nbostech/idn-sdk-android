package in.wavelabs.idn.ConnectionAPI;


import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by vivekkiran on 6/15/16.
 */

public interface OkHttpNBOSCallback {
    void onResponse(Call call, Response response);

    void onFailure(Call call, Throwable t);

}
