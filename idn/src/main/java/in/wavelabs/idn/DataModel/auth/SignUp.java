package in.wavelabs.idn.DataModel.auth;

/**
 * Created by vineelanalla on 12/01/16.
 */
public class SignUp {
    private String clientId;
    private String username;

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}