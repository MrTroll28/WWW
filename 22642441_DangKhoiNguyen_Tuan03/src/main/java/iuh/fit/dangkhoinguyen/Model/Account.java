package iuh.fit.dangkhoinguyen.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;

    public Account(String firstName, String lastName, String email, String password, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}
