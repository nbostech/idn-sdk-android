package in.wavelabs.startersdk.ConnectionAPI;

import android.content.Context;
import android.content.SharedPreferences;

import in.wavelabs.startersdk.ConnectionAPI.service.StarterClient;
import in.wavelabs.startersdk.DataModel.auth.ChangePassword;
import in.wavelabs.startersdk.DataModel.auth.Login;
import in.wavelabs.startersdk.DataModel.auth.Reset;
import in.wavelabs.startersdk.DataModel.auth.SignUp;
import in.wavelabs.startersdk.DataModel.member.NewMemberApiModel;
import in.wavelabs.startersdk.DataModel.validation.MessagesApiModel;
import in.wavelabs.startersdk.Utils.Prefrences;
import in.wavelabs.startersdk.WaveLabsSdk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vineelanalla on 05/02/16.
 */
public class AuthApi {


    public static void resetPassword(Context context, String email, final NBOSCallback nbosCallback) {
        Reset rb = new Reset();
        rb.setEmail(email);
        String token = Prefrences.getClientToken(context);
        Call<MessagesApiModel> call = StarterClient.getStarterAPI().forgot(token,rb);
        call.enqueue(new Callback<MessagesApiModel>() {


            @Override
            public void onResponse(Call<MessagesApiModel> call, Response<MessagesApiModel> response) {
                nbosCallback.onResponse(response);


            }

            @Override
            public void onFailure(Call<MessagesApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });


    }


    public static void createAccount(final Context context, String email, String username, String firstName, String lastName, String password, final NBOSCallback nbosCallback) {
        SignUp sb = new SignUp();
        sb.setClientId(WaveLabsSdk.clientId);
        sb.setEmail(email);
        sb.setUsername(username);
        sb.setFirstName(firstName);
        sb.setLastName(lastName);
        sb.setPassword(password);
        String token = Prefrences.getClientToken(context);

        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().signup(token,sb);
        call.enqueue(new Callback<NewMemberApiModel>() {

            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                    Prefrences.setAccessToken(context, "Bearer "+response.body().getToken().getAccess_token());
                    Prefrences.setRefreshToken(context,"Bearer "+ response.body().getToken().getRefresh_token());
                    Prefrences.setFirstName(context,response.body().getMember().getFirstName());
                    Prefrences.setLastName(context,response.body().getMember().getLastName());
                    Prefrences.setEmailId(context,response.body().getMember().getEmail());
                    Prefrences.setUserId(context,response.body().getMember().getId());
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });


    }



    public static void login(final Context context, String email, String password, final NBOSCallback nbosCallback) {
            Login lb = new Login();
        lb.setClientId(WaveLabsSdk.clientId);
        lb.setUsername(email);
        lb.setPassword(password);
        String token = Prefrences.getClientToken(context);
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().login(token,lb);
        call.enqueue(new Callback<NewMemberApiModel>() {


            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                    Prefrences.setAccessToken(context, "Bearer "+response.body().getToken().getAccess_token());
                    Prefrences.setRefreshToken(context,"Bearer "+ response.body().getToken().getRefresh_token());
                    Prefrences.setFirstName(context,response.body().getMember().getFirstName());
                    Prefrences.setLastName(context,response.body().getMember().getLastName());
                    Prefrences.setEmailId(context,response.body().getMember().getEmail());
                    Prefrences.setUserId(context,response.body().getMember().getId());
                    nbosCallback.onResponse(response);

                }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }

        });
    }

    public static void logout(final Context context, String authorization, final NBOSCallback nbosCallback) {
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

    public static void changePassword(final Context context, String oldPassword, String newPassword, final NBOSCallback nbosCallback){
        ChangePassword cp = new ChangePassword();
        cp.setPasssword(oldPassword);
        cp.setNewPassword(newPassword);
        String token = Prefrences.getClientToken(context);
        Call<MessagesApiModel> call = StarterClient.getStarterAPI().changePassword(token,cp);
        call.enqueue(new Callback<MessagesApiModel>() {


            @Override
            public void onResponse(Call<MessagesApiModel> call, Response<MessagesApiModel> response) {
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<MessagesApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }


        });

    }


}
