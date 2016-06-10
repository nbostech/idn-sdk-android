package in.wavelabs.idn.DataModel.member;

/**
 * Created by vineelanalla on 09/03/16.
 */
public class UpdateMemberApiModel {
    String description;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    String firstName;
    String lastName;
    Long phone;

}
