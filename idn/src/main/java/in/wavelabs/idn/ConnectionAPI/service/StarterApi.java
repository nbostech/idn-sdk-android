package in.wavelabs.idn.ConnectionAPI.service;


import com.nbos.capi.api.v0.RestMessage;
import com.nbos.capi.modules.identity.v0.MemberApiModel;
import com.nbos.capi.modules.identity.v0.NewMemberApiModel;
import com.nbos.capi.modules.identity.v0.SocialConnectUrlResponse;
import com.nbos.capi.modules.identity.v0.TokenApiModel;
import com.nbos.capi.modules.media.v0.MediaApiModel;

import java.util.Map;

import in.wavelabs.idn.DataModel.auth.ChangePassword;
import in.wavelabs.idn.DataModel.auth.Connect;
import in.wavelabs.idn.DataModel.auth.DigitsConnect;
import in.wavelabs.idn.DataModel.auth.Login;
import in.wavelabs.idn.DataModel.auth.Reset;
import in.wavelabs.idn.DataModel.auth.SignUp;
import in.wavelabs.idn.DataModel.member.UpdateMemberApiModel;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Vivek Kiran on 1/1/2016.
 */
public interface StarterApi {
    String baseIdentityUrl = "/api/identity/v0";
    String tokenUrl = "/oauth/token";
    String loginUrl = baseIdentityUrl + "/auth/login";
    String signupUrl = baseIdentityUrl + "/users/signup";
    String connectUrl = baseIdentityUrl + "/auth/social/{connectService}/connect";
    String authorizeUrl = baseIdentityUrl + "/auth/social/{authorizeService}/authorize";
    String digitsUrl = baseIdentityUrl + "/auth/social/digits/connect";
    String profileUrl = baseIdentityUrl + "/users/{uuid}";
    String forgotUrl = baseIdentityUrl + "/auth/forgotPassword";
    String changeUrl  =baseIdentityUrl + "/auth/changePassword";
    String logoutUrl = baseIdentityUrl + "/auth/logout";
    String socialLoginUrl = baseIdentityUrl + "/auth/social/{loginService}/login";
    String mediaUrl = "/api/media/v0/media";

    @FormUrlEncoded
    @POST(tokenUrl)
    Call<TokenApiModel> getToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);
    @FormUrlEncoded
    @POST(tokenUrl)
    Call<TokenApiModel> refreshToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType, @Field("refresh_token") String refreshToken);
    @POST(loginUrl)
    Call<NewMemberApiModel> login(@Header("Authorization") String authorization, @Body Login login);
    @POST(signupUrl)
    Call<NewMemberApiModel> signup(@Header("Authorization") String authorization, @Body SignUp signUpBody);
    @POST(forgotUrl)
    Call<RestMessage> forgot(@Header("Authorization") String authorization, @Body Reset resetBody);
    @POST(changeUrl)
    Call<RestMessage> changePassword(@Header("Authorization") String authorization, @Body ChangePassword changePassword);
    @GET(socialLoginUrl)
    Call<SocialConnectUrlResponse> socialLogin(@Header("Authorization") String authorization, @Path("loginService") String connectService);
    @GET(authorizeUrl)
    Call<NewMemberApiModel> authorize(@Header("Authorization") String authorization, @Path("authorizeService") String connectService, @Query("code") String code, @Query("state") String state);
    @POST(connectUrl)
    Call<NewMemberApiModel> connect(@Header("Authorization") String authorization, @Path("connectService") String connectService, @Body Connect connect);
    @POST(digitsUrl)
    Call<NewMemberApiModel> digitsConnect(@Header("Authorization") String authorization, @Body DigitsConnect connect);
    @GET(logoutUrl)
    Call<NewMemberApiModel> logout(@Header("Authorization") String authorization);
    @GET(profileUrl)
    Call<MemberApiModel> getProfile(@Header("Authorization") String authorization, @Path("uuid") String uuid);
    @GET(mediaUrl)
    Call<MediaApiModel> media(@Header("Authorization") String authorization, @Query("id") String uuid, @Query("mediafor") String mediafor);
    @Multipart
    @POST(mediaUrl)
    Call<RestMessage> uploadMedia(@Header("Authorization") String authorization, @PartMap Map<String, RequestBody> params);
    @PUT(profileUrl)
    Call<MemberApiModel> updateProfile(@Header("Authorization") String authorization, @Path("uuid") String uuid, @Body UpdateMemberApiModel updateMemberApiModel);

}

