package in.wavelabs.idn.ConnectionAPI.service;


import java.util.Map;

import in.wavelabs.idn.DataModel.auth.ChangePassword;
import in.wavelabs.idn.DataModel.auth.Connect;
import in.wavelabs.idn.DataModel.auth.DigitsConnect;
import in.wavelabs.idn.DataModel.auth.Login;
import in.wavelabs.idn.DataModel.auth.Reset;
import in.wavelabs.idn.DataModel.auth.SignUp;
import in.wavelabs.idn.DataModel.auth.social.SocialLogin;
import in.wavelabs.idn.DataModel.media.MediaApiModel;
import in.wavelabs.idn.DataModel.member.MemberApiModel;
import in.wavelabs.idn.DataModel.member.NewMemberApiModel;
import in.wavelabs.idn.DataModel.member.UpdateMemberApiModel;
import in.wavelabs.idn.DataModel.oauth.NewTokenResponseModel;
import in.wavelabs.idn.DataModel.validation.MessagesApiModel;
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
 * Created by vinay on 1/1/2016.
 */
public interface StarterApi {
    String baseIdentityUrl = "/api/identity/v0";
    String tokenUrl = "/oauth/token";
    String loginUrl = baseIdentityUrl + "/auth/login";
    String signupUrl = baseIdentityUrl + "/users/signup";
    String connectUrl = baseIdentityUrl + "/auth/social/{connectService}/connect";
    String authorizeUrl = baseIdentityUrl + "/auth/social/{authorizeService}/authorize";
    String digitsUrl = baseIdentityUrl + "/auth/social/digits/connect";
    String profileUrl = baseIdentityUrl + "/users/{userId}";
    String forgotUrl = baseIdentityUrl + "/auth/forgotPassword";
    String changeUrl  =baseIdentityUrl + "/auth/changePassword";
    String logoutUrl = baseIdentityUrl + "/auth/logout";
    String socialLoginUrl = baseIdentityUrl + "/auth/social/{loginService}/login";
    String mediaUrl = "/api/media/v0/media";

    @FormUrlEncoded
    @POST(tokenUrl)
    Call<NewTokenResponseModel> getToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);
    @FormUrlEncoded
    @POST(tokenUrl)
    Call<NewTokenResponseModel> refreshToken(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType, @Field("refresh_token") String refreshToken);

    @POST(loginUrl)
    Call<NewMemberApiModel> login(@Header("Authorization") String authorization, @Body Login login);
    @POST(signupUrl)
    Call<NewMemberApiModel> signup(@Header("Authorization") String authorization, @Body SignUp signUpBody);
    @POST(forgotUrl)
    Call<MessagesApiModel> forgot(@Header("Authorization") String authorization, @Body Reset resetBody);
    @POST(changeUrl)
    Call<MessagesApiModel> changePassword(@Header("Authorization") String authorization, @Body ChangePassword changePassword);
    @GET(socialLoginUrl)
    Call<SocialLogin> socialLogin(@Header("Authorization") String authorization, @Path("loginService") String connectService);
    @GET(authorizeUrl)
    Call<NewMemberApiModel> authorize(@Header("Authorization") String authorization, @Path("authorizeService") String connectService, @Query("code") String code, @Query("state") String state);
    @POST(connectUrl)
    Call<NewMemberApiModel> connect(@Header("Authorization") String authorization, @Path("connectService") String connectService, @Body Connect connect);
    @POST(digitsUrl)
    Call<NewMemberApiModel> digitsConnect(@Header("Authorization") String authorization, @Body DigitsConnect connect);
    @GET(logoutUrl)
    Call<NewMemberApiModel> logout(@Header("Authorization") String authorization);
    @GET(profileUrl)
    Call<MemberApiModel> getProfile(@Header("Authorization") String authorization, @Path("userId") long userId);
    @GET(mediaUrl)
    Call<MediaApiModel> media(@Header("Authorization") String authorization, @Query("id") long userId, @Query("mediafor") String mediafor);
    @Multipart
    @POST(mediaUrl)
    Call<MessagesApiModel> uploadMedia(@Header("Authorization") String authorization, @PartMap Map<String, RequestBody> params);
    @PUT(profileUrl)
    Call<MemberApiModel> updateProfile(@Header("Authorization") String authorization, @Path("userId") long userId, @Body UpdateMemberApiModel updateMemberApiModel);

}

