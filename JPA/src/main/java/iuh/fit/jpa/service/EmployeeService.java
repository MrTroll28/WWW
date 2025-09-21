package iuh.fit.jpa.service;

import iuh.fit.jpa.model.Employee;
import iuh.fit.jpa.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> findAllWithDepartment() {
        return repo.findAllWithDepartment();
    }

    public List<Employee> findByDepartmentId(Long deptId) {
        return repo.findByDepartmentId(deptId);
    }

    public Employee findByEmpId(Long empId) {
        return repo.findByEmpId(empId);
    }

    public List<Employee> findByEmpName(String empName) {
        return repo.findByEmpName(empName);
    }

    public List<Employee> findBySalaryGreaterThan(Double salary) {
        return repo.findBySalaryGreaterThan(salary);
    }


}
