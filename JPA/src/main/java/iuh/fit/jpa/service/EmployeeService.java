package iuh.fit.jpa.service;

import iuh.fit.jpa.model.Employee;
import iuh.fit.jpa.model.Department;
import iuh.fit.jpa.repository.EmployeeRepository;
import iuh.fit.jpa.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // CREATE
    public Employee addEmployee(Employee employee) {
        // Kiểm tra department tồn tại trước khi lưu
        if (employee.getDepartment() != null) {
            Long deptId = employee.getDepartment().getDeptId();
            Optional<Department> dept = departmentRepository.findById(deptId);
            dept.ifPresent(employee::setDepartment);
        }
        return employeeRepository.save(employee);
    }

    // READ (tất cả)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // READ (theo ID)
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // READ (theo tên)
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByEmpName(name);
    }

    // READ (theo phòng ban)
    public List<Employee> getEmployeesByDepartment(Long deptId) {
        return employeeRepository.findByDepartmentId(deptId);
    }

    // READ (theo mức lương)
    public List<Employee> getEmployeesWithSalaryGreaterThan(Double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    // UPDATE
    public Employee updateEmployee(Long id, Employee newEmp) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setEmpName(newEmp.getEmpName());
                    emp.setSalary(newEmp.getSalary());
                    emp.setDob(newEmp.getDob());
                    emp.setDepartment(newEmp.getDepartment());
                    return employeeRepository.save(emp);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // FETCH JOIN
    public List<Employee> getAllEmployeesWithDepartment() {
        return employeeRepository.findAllWithDepartment();
    }
}
