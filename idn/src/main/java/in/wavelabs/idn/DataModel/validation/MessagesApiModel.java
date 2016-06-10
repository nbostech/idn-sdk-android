package in.wavelabs.idn.DataModel.validation;

import java.util.List;

/**
 * Created by vineelanalla on 18/01/16.
 */
public class MessagesApiModel extends ValidationMessagesApiModel {
    private String messageCode;
    private String message;

    public String getMessageCode() {
        return messageCode;
    }

    public String getMessage() {
        return message;
    }

    public List<ValidationMessagesApiModel> getValidationErrors() {
        return validationErrors;
    }

    private List<ValidationMessagesApiModel> validationErrors;

}
