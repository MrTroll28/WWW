package iuh.fit.jpa.repository;

import iuh.fit.jpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Tìm danh sách employee theo tên
    @Query("SELECT e FROM Employee e WHERE e.empName = :empName")
    List<Employee> findByEmpName(@Param("empName") String empName);

    // Tìm danh sách employee theo phòng ban
    @Query("SELECT e FROM Employee e WHERE e.department.deptId = :deptId")
    List<Employee> findByDepartmentId(@Param("deptId") Long deptId);

    // Tìm danh sách employee có lương > ?
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findBySalaryGreaterThan(@Param("salary") Double salary);

    // Get all employees with their department (fetch join để tránh lazy loading)
    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN FETCH e.department")
    List<Employee> findAllWithDepartment();
}
