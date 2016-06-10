package in.wavelabs.idn.DataModel.oauth;

/**
 * Created by vineelanalla on 29/02/16.
 */
public class NewTokenModel {
    private String client_id;
    private String client_secret;
    private String grant_type;
    private String scope;


    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }


    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }


    public void setScope(String scope) {
        this.scope = scope;
    }
}
