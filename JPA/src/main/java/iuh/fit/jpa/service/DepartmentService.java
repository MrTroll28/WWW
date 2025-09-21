package iuh.fit.jpa.service;

import iuh.fit.jpa.model.Department;
import iuh.fit.jpa.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private DepartmentRepository repo;

    public DepartmentService(DepartmentRepository repo) {
        this.repo = repo;
    }

    public Department findByDeptName(String name) {
        return repo.findByDeptName(name);
    }

    public List<Department> findDepartmentsWithMoreEmployees(int minSize) {
        return repo.findDepartmentsWithMoreEmployees(minSize);
    }

    public List<Department> findAllWithEmployees() {
        return repo.findAllWithEmployees();
    }
}
