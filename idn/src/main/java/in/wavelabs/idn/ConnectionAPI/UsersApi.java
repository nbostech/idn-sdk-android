package in.wavelabs.idn.ConnectionAPI;

import android.content.Context;

import com.nbos.capi.modules.identity.v0.MemberApiModel;

import in.wavelabs.idn.ConnectionAPI.service.StarterClient;
import in.wavelabs.idn.DataModel.member.UpdateMemberApiModel;
import in.wavelabs.idn.utils.TokenPrefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vivek Kiran on 05/02/16.
 */
public class UsersApi {

public static void getUserProfile(final Context context,String uuid, final NBOSCallback<MemberApiModel>  nbosCallback){
    String accessToken = TokenPrefrences.getAccessToken(context);
    Call<MemberApiModel> call = StarterClient.getStarterAPI().getProfile(accessToken, uuid);
    call.enqueue(new Callback<MemberApiModel>() {


        @Override
        public void onResponse(Call<MemberApiModel> call, Response<MemberApiModel> response) {
                nbosCallback.onResponse(response);
        }

        @Override
        public void onFailure(Call<MemberApiModel> call, Throwable t) {
            nbosCallback.onFailure(t);


        }
    });
}

    public static void updateProfile(final Context context, String firstName, String lastName,Long phone,String description,String uuid,final NBOSCallback<MemberApiModel> nbosCallback){
        UpdateMemberApiModel um = new UpdateMemberApiModel();
        um.setDescription(description);
        um.setFirstName(firstName);
        um.setLastName(lastName);
        um.setPhone(phone);
        String accessToken = TokenPrefrences.getAccessToken(context);

        Call<MemberApiModel> call = StarterClient.getStarterAPI().updateProfile(accessToken, uuid, um);
        call.enqueue(new Callback<MemberApiModel>() {


            @Override
            public void onResponse(Call<MemberApiModel> call, Response<MemberApiModel> response) {
                nbosCallback.onResponse(response);

            }

            @Override
            public void onFailure(Call<MemberApiModel> call, Throwable t) {
                nbosCallback.onFailure(t);


            }
        });

    }

}
