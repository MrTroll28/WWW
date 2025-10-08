package me.kn.mongo.controller;

import me.kn.mongo.model.Employee;
import me.kn.mongo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService empService;

    public EmployeeRestController(EmployeeService empService) {
        this.empService = empService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return empService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable String id) {
        return empService.findById(id).orElse(null);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getByName(@PathVariable String name) {
        return empService.findByEmpName(name);
    }

    @GetMapping("/department/{deptId}")
    public List<Employee> getByDept(@PathVariable String deptId) {
        return empService.findByDepartmentId(deptId);
    }

    @GetMapping("/salary/{amount}")
    public List<Employee> getBySalary(@PathVariable double amount) {
        return empService.findBySalaryGreaterThan(amount);
    }

    @PostMapping
    public Employee create(@RequestBody Employee e) {
        return empService.create(e);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee e) {
        return empService.update(id, e);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return empService.delete(id);
    }
}
