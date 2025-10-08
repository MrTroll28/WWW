package me.kn.mongo.service;

import me.kn.mongo.model.Department;
import me.kn.mongo.repository.DepartmentRepository;
import me.kn.mongo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository deptRepo;
    private final EmployeeRepository empRepo;

    public DepartmentService(DepartmentRepository deptRepo, EmployeeRepository empRepo) {
        this.deptRepo = deptRepo;
        this.empRepo = empRepo;
    }

    public Department create(Department d) { return deptRepo.save(d); }
    public List<Department> findAll() { return deptRepo.findAll(); }
    public Optional<Department> findById(String id) { return deptRepo.findById(id); }
    public Department findByName(String name) { return deptRepo.findByDeptName(name); }

    public Department update(String id, Department newDept) {
        return deptRepo.findById(id).map(d -> {
            d.setDeptName(newDept.getDeptName());
            return deptRepo.save(d);
        }).orElse(null);
    }

    public boolean delete(String id) {
        if (!deptRepo.existsById(id)) return false;
        // Nếu muốn xóa đồng thời employees, xóa trước employees có ref tới dept
        empRepo.findByDepartmentId(id).forEach(empRepo::delete);
        deptRepo.deleteById(id);
        return true;
    }
}
