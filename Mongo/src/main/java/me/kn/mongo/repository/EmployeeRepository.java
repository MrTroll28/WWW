package me.kn.mongo.repository;

import me.kn.mongo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    List<Employee> findByEmpName(String empName);

    List<Employee> findByDepartmentId(String deptId);

    List<Employee> findBySalaryGreaterThan(Double salary);

    // Ví dụ JPQL-like Mongo query (JSON) với @Query
    @Query("{ 'empName' : { $regex: ?0, $options: 'i' } }")
    List<Employee> searchByNameRegex(String regex);
}
