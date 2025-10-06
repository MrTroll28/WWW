package iuh.fit.jpa.controllerREST;

import iuh.fit.jpa.model.Employee;
import iuh.fit.jpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET BY NAME
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Employee>> getEmployeesByName(@PathVariable String name) {
        List<Employee> list = employeeService.getEmployeesByName(name);
        return ResponseEntity.ok(list);
    }

    // GET BY DEPARTMENT
    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Long deptId) {
        List<Employee> list = employeeService.getEmployeesByDepartment(deptId);
        return ResponseEntity.ok(list);
    }

    // GET BY SALARY
    @GetMapping("/salary/{salary}")
    public ResponseEntity<List<Employee>> getEmployeesBySalary(@PathVariable Double salary) {
        List<Employee> list = employeeService.getEmployeesWithSalaryGreaterThan(salary);
        return ResponseEntity.ok(list);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee saved = employeeService.addEmployee(employee);
        return ResponseEntity.ok(saved);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        Employee updated = employeeService.updateEmployee(id, employee);
        if (updated != null)
            return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    // FETCH JOIN (Employee + Department)
    @GetMapping("/with-department")
    public ResponseEntity<List<Employee>> getEmployeesWithDepartment() {
        return ResponseEntity.ok(employeeService.getAllEmployeesWithDepartment());
    }
}
