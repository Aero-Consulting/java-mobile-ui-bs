package helpers.data;

import static helpers.data.StringUtils.formatPhone;
import static java.text.MessageFormat.format;

import api.models.clients.auth.Token;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private String newPhone;
    private String email;
    private String smsCode;
    private Token token;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String phone;
    private String password;
    private String gender;

    public String formattedPhone() {
        return formatPhone(phone);
    }

    public String getFullName() {
        return format("{0} {1}", firstName, lastName);
    }

    @Override
    public String toString() {
        return "User{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", phone='" + phone + '\'' +
            ", gender='" + gender + '\'' +
            ", email='" + email + '\'' +
            '}';
    }

}
