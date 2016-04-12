package in.wavelabs.startersdk.DataModel.member;

import java.util.List;

import in.wavelabs.startersdk.DataModel.validation.MessagesApiModel;

/**
 * Created by vineelanalla on 12/01/16.
 */
public class MemberApiModel {
    private String description;
    private String email;
    private String firstName;

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhone() {
        return phone;
    }

    public List<SocialApiModel> getSocialAccounts() {
        return socialAccounts;
    }

    private long id;
    private String lastName;
    private long phone;
    private List<SocialApiModel> socialAccounts;

    public MessagesApiModel getErrors() {
        return errors;
    }

    private MessagesApiModel errors;

    public String getUuid() {
        return uuid;
    }

    private String uuid;

}
