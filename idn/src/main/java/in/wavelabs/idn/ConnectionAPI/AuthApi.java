package in.wavelabs.idn.ConnectionAPI;

import android.content.Context;
import android.content.SharedPreferences;

import com.nbos.api.v0.RestMessage;
import com.nbos.modules.identity.v0.NewMemberApiModel;

import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.DataModel.auth.ChangePassword;
import in.wavelabs.idn.DataModel.auth.Login;
import in.wavelabs.idn.DataModel.auth.Reset;
import in.wavelabs.idn.DataModel.auth.SignUp;
import in.wavelabs.idn.utils.TokenPrefrences;
import in.wavelabs.idn.WaveLabsSdk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class AuthApi {


    public static void resetPassword(Context context, String email, final NBOSCallback<RestMessage> nbosCallback) {
        Reset rb = new Reset();
        rb.setEmail(email);
        String token = TokenPrefrences.getClientToken(context);
        Call<RestMessage> call = StarterClient.getStarterAPI().forgot(token,rb);
        call.enqueue(new Callback<RestMessage>() {


            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                nbosCallback.onResponse(response);


            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });


    }


    public static void createAccount(final Context context, String email, String username, String firstName, String lastName, String password, final NBOSCallback<NewMemberApiModel> nbosCallback) {
        SignUp sb = new SignUp();
        sb.setClientId(WaveLabsSdk.clientId);
        sb.setEmail(email);
        sb.setUsername(username);
        sb.setFirstName(firstName);
        sb.setLastName(lastName);
        sb.setPassword(password);
        String token = TokenPrefrences.getClientToken(context);

        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().signup(token,sb);
        call.enqueue(new Callback<NewMemberApiModel>() {

            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                    TokenPrefrences.setAccessToken(context, "Bearer "+response.body().getToken().getAccess_token());
                    TokenPrefrences.setRefreshToken(context,response.body().getToken().getRefresh_token());

                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });


    }



    public static void login(final Context context, String email, String password, final NBOSCallback<NewMemberApiModel> nbosCallback) {
            Login lb = new Login();
        lb.setClientId(WaveLabsSdk.clientId);
        lb.setUsername(email);
        lb.setPassword(password);
        String token = TokenPrefrences.getClientToken(context);
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().login(token,lb);
        call.enqueue(new Callback<NewMemberApiModel>() {


            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                    TokenPrefrences.setAccessToken(context, "Bearer "+response.body().getToken().getAccess_token());
                    TokenPrefrences.setRefreshToken(context,response.body().getToken().getRefresh_token());
                    nbosCallback.onResponse(response);

                }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }

        });
    }

    public static void logout(final Context context, String authorization, final NBOSCallback<NewMemberApiModel> nbosCallback) {
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().logout(authorization);
        call.enqueue(new Callback<NewMemberApiModel>() {


            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                    SharedPreferences prefs = context.getSharedPreferences("library", Context.MODE_PRIVATE);
                    prefs.edit().clear().apply();
                    WaveLabsSdk.SdkInitialize(context);
                    nbosCallback.onResponse(response);

                }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });

    }

    public static void changePassword(final Context context, String oldPassword, String newPassword, final NBOSCallback<RestMessage> nbosCallback){
        ChangePassword cp = new ChangePassword();
        cp.setPasssword(oldPassword);
        cp.setNewPassword(newPassword);
        String token = TokenPrefrences.getClientToken(context);
        Call<RestMessage> call = StarterClient.getStarterAPI().changePassword(token,cp);
        call.enqueue(new Callback<RestMessage>() {


            @Override
            public void onResponse(Call<RestMessage> call, Response<RestMessage> response) {
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<RestMessage> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });

    }


}
