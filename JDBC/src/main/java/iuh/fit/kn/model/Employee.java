package iuh.fit.kn.model;
import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("employees")
public class Employee {
    private int id;
    private String role;
    private String name;
}
