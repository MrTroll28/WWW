package iuh.fit.dangkhoinguyen.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String FirstName;
    private String LastName;
    private LocalDate dob;
    private String email;
    private String phone;
    private Gender gender;
    private String address;
    private String city;
    private Long pinCode;
    private String state;
    private String country;
    private String[] hobbies;
}
