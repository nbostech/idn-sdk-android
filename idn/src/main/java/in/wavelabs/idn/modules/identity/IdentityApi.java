package in.wavelabs.idn.modules.identity;

import com.nbos.capi.modules.identity.v0.TokenApiModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by vivekkiran on 6/14/16.
 */

public interface IdentityApi {

    String tokenUrl = "/oauth/token";

    @FormUrlEncoded
    @POST(tokenUrl)
    Call<TokenApiModel> getToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);

}
