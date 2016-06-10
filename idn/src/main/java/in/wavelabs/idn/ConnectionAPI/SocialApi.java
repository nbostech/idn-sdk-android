package in.wavelabs.idn.ConnectionAPI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.DataModel.auth.Connect;
import in.wavelabs.idn.DataModel.auth.DigitsConnect;
import in.wavelabs.idn.DataModel.auth.social.SocialLogin;
import in.wavelabs.idn.DataModel.member.NewMemberApiModel;
import in.wavelabs.idn.Utils.Prefrences;
import in.wavelabs.idn.View.WebViewActivity;
import in.wavelabs.idn.WaveLabsSdk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vineelanalla on 05/02/16.
 */
public class SocialApi {


    public static void authorizeAndConnect(final Context context,String service,String code, String state, final NBOSCallback nbosCallback) {
        String clientToken = Prefrences.getClientToken(context);
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().authorize(clientToken, service, code,state);
        call.enqueue(new Callback<NewMemberApiModel>() {


            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                nbosCallback.onResponse(response);
            }

            @Override
            public void onFailure(Call<NewMemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);
            }
        });
    }
    public static void socialConnect(final Context context,String accessToken, String service, final NBOSCallback nbosCallback) {
        String clientToken = Prefrences.getClientToken(context);

            Connect cn = new Connect();
            cn.setClientId(WaveLabsSdk.clientId);
            cn.setAccessToken(accessToken);
            cn.setExpiresIn("");

            Call<NewMemberApiModel> call = StarterClient.getStarterAPI().connect(clientToken, service, cn);
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

    public static void socialLogin(final Context context, final String service, final NBOSCallback nbosCallback) {
        String clientToken = Prefrences.getAccessToken(context);
        Call<SocialLogin> call = StarterClient.getStarterAPI().socialLogin(clientToken,service);
        call.enqueue(new Callback<SocialLogin>() {
            @Override
            public void onResponse(Call<SocialLogin> call, Response<SocialLogin> response) {
                    Intent i = new Intent(context, WebViewActivity.class);
                    i.putExtra("name", service);
                    i.putExtra("url", response.body().getUrl());
                   // context.startActivity(i);

                ((Activity)context).startActivityForResult(i, 0);

      //              nbosCallback.onSuccess(response);
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<SocialLogin> call, Throwable t) {

            }
        });
    }


    public static void digitsConnect(final Context context, String provider, String authorization, String firstName, String lastName, String emailId, final NBOSCallback nbosCallback){
        DigitsConnect cn = new DigitsConnect();
        cn.setClientId(WaveLabsSdk.clientId);
        cn.setFirstName(firstName);
        cn.setLastName(lastName);
        cn.setEmailId(emailId);
        cn.setProvider(provider);
        cn.setAuthorization(authorization);
        String clientToken = Prefrences.getClientToken(context);

        final ProgressDialog dialog = ProgressDialog.show(context, "Connecting to digits",
                "Loading. Please wait...", true);
        Call<NewMemberApiModel> call = StarterClient.getStarterAPI().digitsConnect(clientToken,cn);
        call.enqueue(new Callback<NewMemberApiModel>() {

            @Override
            public void onResponse(Call<NewMemberApiModel> call, Response<NewMemberApiModel> response) {
                dialog.dismiss();
                    Prefrences.setAccessToken(context, "Bearer " + response.body().getToken().getAccess_token());
                    Prefrences.setRefreshToken(context, "Bearer " + response.body().getToken().getRefresh_token());
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


}
