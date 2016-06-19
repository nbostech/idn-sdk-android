package in.wavelabs.ids;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vivekkiran on 6/14/16.
 */

public interface IDSInterface {
     String hostName();


    @GET("")
    Call<?> getTenants();
}
