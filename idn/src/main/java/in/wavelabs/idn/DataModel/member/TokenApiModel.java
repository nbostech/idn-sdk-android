package in.wavelabs.idn.DataModel.member;

/**
 * Created by vineelanalla on 12/01/16.
 */
public class TokenApiModel {
    public String getAccess_token() {
        return access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public String getToken_type() {
        return token_type;
    }

    private String access_token;
    private long expires_in;
    private String refresh_token;
    private String scope;
    private String token_type;

}