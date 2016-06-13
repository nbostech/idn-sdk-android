package in.wavelabs.idn.ConnectionAPI;

import retrofit2.Response;

/**
 * Created by Vivek Kiran on 11/02/16.
 */
public interface NBOSCallback<T> {

    void onResponse(Response<T> response);

    void onFailure(Throwable t);

}
