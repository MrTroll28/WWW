package iuh.fit.jpa.controller;

import iuh.fit.jpa.model.Department;
import iuh.fit.jpa.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    // Lấy department theo tên
    @GetMapping("/by-name/{name}")
    public Department getByName(@PathVariable String name) {
        return service.findByDeptName(name);
    }

    // Lấy departments có số nhân viên > minSize
    @GetMapping("/with-min-employees/{minSize}")
    public List<Department> getDepartmentsWithMoreEmployees(@PathVariable int minSize) {
        return service.findDepartmentsWithMoreEmployees(minSize);
    }

    // Lấy tất cả departments kèm danh sách employees
    @GetMapping("/all-with-employees")
    public List<Department> getAllWithEmployees() {
        return service.findAllWithEmployees();
    }
}
