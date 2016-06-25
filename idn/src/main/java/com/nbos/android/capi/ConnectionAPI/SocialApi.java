package com.nbos.android.capi.ConnectionAPI;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class SocialApi {

//    public static void socialLogin(final Context context, final String service, final NBOSCallback<SocialConnectUrlResponse> nbosCallback) {
//        String clientToken = TokenPrefrences.getAccessToken(context);
//        Call<SocialConnectUrlResponse> call = StarterClient.getStarterAPI().socialLogin(clientToken,service);
//        call.enqueue(new Callback<SocialConnectUrlResponse>() {
//            @Override
//            public void onResponse(Call<SocialConnectUrlResponse> call, Response<SocialConnectUrlResponse> response) {
//                    Intent i = new Intent(context, WebViewActivity.class);
//                    i.putExtra("name", service);
//                    i.putExtra("url", response.body().getUrl());
//                   // context.startActivity(i);
//
//                ((Activity)context).startActivityForResult(i, 0);
//
//                nbosCallback.onResponse(response);
//
//            }
//
//            @Override
//            public void onFailure(Call<SocialConnectUrlResponse> call, Throwable t) {
//
//            }
//        });
//    }


}
