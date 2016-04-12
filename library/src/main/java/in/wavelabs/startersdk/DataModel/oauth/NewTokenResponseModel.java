package in.wavelabs.startersdk.DataModel.oauth;

import in.wavelabs.startersdk.DataModel.validation.MessagesApiModel;

/**
 * Created by vineelanalla on 29/02/16.
 */
public class NewTokenResponseModel extends MessagesApiModel{
    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

    private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;

    private MessagesApiModel errors;

    public MessagesApiModel getErrors() {
        return errors;
    }
}
