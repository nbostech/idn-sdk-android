package in.wavelabs.startersdk.DataModel.member;

/**
 * Created by vineelanalla on 22/01/16.
 */
public class SocialApiModel {
    private long id;
    private String email;
    private String socialType;
    private String imageUrl;

    public String getAccessToken() {
        return accessToken;
    }

    private String accessToken;
    public String getSocialType() {
        return socialType;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
