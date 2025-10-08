package me.kn.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String id;
    private String empName;
    private Double salary;
    private LocalDate dob;

    @DBRef // reference đến Department
    private Department department;

    public Employee(String empName, double salary, LocalDate dob, Department department) {
        this.empName = empName;
        this.salary = salary;
        this.dob = dob;
        this.department = department;
    }
}
