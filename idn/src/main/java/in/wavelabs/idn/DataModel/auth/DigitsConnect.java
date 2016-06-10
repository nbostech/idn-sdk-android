package in.wavelabs.idn.DataModel.auth;

/**
 * Created by vineelanalla on 29/01/16.
 */
public class DigitsConnect {
    private String clientId;

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    private String emailId;
    private String firstName;
    private String lastName;
    private String authorization;
    private String provider;
}
