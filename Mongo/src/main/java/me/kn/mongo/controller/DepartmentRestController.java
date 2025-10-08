package me.kn.mongo.controller;

import me.kn.mongo.model.Department;
import me.kn.mongo.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

    private final DepartmentService deptService;

    public DepartmentRestController(DepartmentService deptService) {
        this.deptService = deptService;
    }

    // Lấy tất cả phòng ban
    @GetMapping
    public List<Department> getAll() {
        return deptService.findAll();
    }

    // Lấy phòng ban theo ID
    @GetMapping("/{id}")
    public Department getById(@PathVariable String id) {
        return deptService.findById(id).orElse(null);
    }

    // Lấy phòng ban theo tên
    @GetMapping("/name/{name}")
    public Department getByName(@PathVariable String name) {
        return deptService.findByName(name);
    }

    // Tạo phòng ban mới
    @PostMapping
    public Department create(@RequestBody Department dept) {
        return deptService.create(dept);
    }

    // Cập nhật phòng ban
    @PutMapping("/{id}")
    public Department update(@PathVariable String id, @RequestBody Department newDept) {
        return deptService.update(id, newDept);
    }

    // Xóa phòng ban (và cả employee nếu bạn có logic xóa kèm)
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return deptService.delete(id);
    }
}
