package in.wavelabs.idn.DataModel.member;

import java.util.List;

import in.wavelabs.idn.DataModel.validation.MessagesApiModel;
import in.wavelabs.idn.DataModel.validation.ValidationMessagesApiModel;


/**
 * Created by vineelanalla on 29/01/16.
 */
public class NewMemberApiModel extends MessagesApiModel{
    public MemberApiModel getMember() {
        return member;
    }

    public TokenApiModel getToken() {
        return token;
    }

    private MemberApiModel member;
    private TokenApiModel token;

    public List<ValidationMessagesApiModel> getValidationErrors() {
        return errors;
    }

    private List<ValidationMessagesApiModel> errors;

}
