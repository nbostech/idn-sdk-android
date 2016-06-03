package in.wavelabs.startersdk.ConnectionAPI;

import android.content.Context;

import in.wavelabs.startersdk.ConnectionAPI.service.StarterClient;
import in.wavelabs.startersdk.DataModel.member.MemberApiModel;
import in.wavelabs.startersdk.DataModel.member.UpdateMemberApiModel;
import in.wavelabs.startersdk.Utils.Prefrences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by vineelanalla on 05/02/16.
 */
public class UsersApi {

public static void getUserProfile(final Context context,final NBOSCallback nbosCallback){
    String accessToken = Prefrences.getAccessToken(context);
    Long userId = Prefrences.getUserId(context);
    Call<MemberApiModel> call = StarterClient.getStarterAPI().getProfile(accessToken, userId);
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

    public static void updateProfile(final Context context, String firstName, String lastName,final NBOSCallback nbosCallback){
        Long phone = Prefrences.getPhoneNumber(context);
        String description = Prefrences.getDescription(context);
        Long userId = Prefrences.getUserId(context);
        UpdateMemberApiModel um = new UpdateMemberApiModel();
        um.setDescription(description);
        um.setFirstName(firstName);
        um.setLastName(lastName);
        um.setPhone(phone);
        String accessToken = Prefrences.getAccessToken(context);

        Call<MemberApiModel> call = StarterClient.getStarterAPI().updateProfile(accessToken, userId, um);
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
