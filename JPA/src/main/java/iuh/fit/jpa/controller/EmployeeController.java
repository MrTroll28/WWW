package iuh.fit.jpa.controller;

import iuh.fit.jpa.model.Employee;
import iuh.fit.jpa.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // Lấy tất cả nhân viên
    @GetMapping
    public List<Employee> getAll() {
        return service.findAllWithDepartment();
    }

    // Lấy theo phòng ban
    @GetMapping("/by-dept/{deptId}")
    public List<Employee> getByDeptId(@PathVariable Long deptId) {
        return service.findByDepartmentId(deptId);
    }

    // Lấy theo tên
    @GetMapping("/by-name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        return service.findByEmpName(name);
    }

    // Lấy theo ID
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.findByEmpId(id);
    }

    // Lấy theo lương lớn hơn salary
    @GetMapping("/by-salary/{salary}")
    public List<Employee> getBySalaryGreaterThan(@PathVariable Double salary) {
        return service.findBySalaryGreaterThan(salary);
    }
}
