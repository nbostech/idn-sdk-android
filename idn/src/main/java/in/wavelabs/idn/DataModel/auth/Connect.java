package in.wavelabs.idn.DataModel.auth;

/**
 * Created by vineelanalla on 28/01/16.
 */
public class Connect {
   private String clientId;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    private String accessToken;
    private String expiresIn;
}
