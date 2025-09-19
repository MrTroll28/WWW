package iuh.fit.kn.controller;


import iuh.fit.kn.dao.EmployeeDAO;
import iuh.fit.kn.model.Employee;
import iuh.fit.kn.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody Employee emp) {
        employeeService.save(emp);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Employee emp) {
        employeeService.update(emp);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        employeeService.deleteById(id);
    }
}
