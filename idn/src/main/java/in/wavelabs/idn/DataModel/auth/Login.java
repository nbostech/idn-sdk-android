package in.wavelabs.idn.DataModel.auth;

/**
 * Created by vineelanalla on 12/01/16.
 */
public class Login {
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    private String clientId;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;
    private String password;
}
