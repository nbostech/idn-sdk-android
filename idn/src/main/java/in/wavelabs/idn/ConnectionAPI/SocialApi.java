package in.wavelabs.idn.ConnectionAPI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.nbos.capi.modules.identity.v0.NewMemberApiModel;
import com.nbos.capi.modules.identity.v0.SocialConnectUrlResponse;

import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.DataModel.auth.Connect;
import in.wavelabs.idn.DataModel.auth.DigitsConnect;
import in.wavelabs.idn.utils.TokenPrefrences;
import in.wavelabs.idn.view.WebViewActivity;
import in.wavelabs.idn.WaveLabsSdk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class SocialApi {

    public static void socialLogin(final Context context, final String service, final NBOSCallback<SocialConnectUrlResponse> nbosCallback) {
        String clientToken = TokenPrefrences.getAccessToken(context);
        Call<SocialConnectUrlResponse> call = StarterClient.getStarterAPI().socialLogin(clientToken,service);
        call.enqueue(new Callback<SocialConnectUrlResponse>() {
            @Override
            public void onResponse(Call<SocialConnectUrlResponse> call, Response<SocialConnectUrlResponse> response) {
                    Intent i = new Intent(context, WebViewActivity.class);
                    i.putExtra("name", service);
                    i.putExtra("url", response.body().getUrl());
                   // context.startActivity(i);

                ((Activity)context).startActivityForResult(i, 0);

                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<SocialConnectUrlResponse> call, Throwable t) {

            }
        });
    }


}
