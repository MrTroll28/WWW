package iuh.fit.jpa.controllerREST;

import iuh.fit.jpa.model.Department;
import iuh.fit.jpa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET BY NAME
    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
        Department dept = departmentService.getDepartmentByName(name);
        if (dept != null) {
            return ResponseEntity.ok(dept);
        }
        return ResponseEntity.notFound().build();
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department saved = departmentService.addDepartment(department);
        return ResponseEntity.ok(saved);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {
        Department updated = departmentService.updateDepartment(id, department);
        if (updated != null)
            return ResponseEntity.ok(updated);
        return ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (deleted)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }

    // CUSTOM QUERY
    @GetMapping("/many-employees/{minSize}")
    public ResponseEntity<List<Department>> getDepartmentsWithManyEmployees(@PathVariable int minSize) {
        return ResponseEntity.ok(departmentService.getDepartmentsWithManyEmployees(minSize));
    }
}
