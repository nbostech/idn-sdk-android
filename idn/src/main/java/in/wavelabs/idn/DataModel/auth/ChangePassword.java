package in.wavelabs.idn.DataModel.auth;

/**
 * Created by vineelanalla on 08/03/16.
 */
public class ChangePassword {
    private String passsword;

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    private String newPassword;
}
