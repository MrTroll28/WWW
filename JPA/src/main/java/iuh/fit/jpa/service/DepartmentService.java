package iuh.fit.jpa.service;

import iuh.fit.jpa.model.Department;
import iuh.fit.jpa.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // CREATE
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // READ (Tất cả)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // READ (Theo ID)
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // READ (Theo tên)
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByDeptName(name);
    }

    // UPDATE
    public Department updateDepartment(Long id, Department newDept) {
        return departmentRepository.findById(id)
                .map(dept -> {
                    dept.setDeptName(newDept.getDeptName());
                    dept.setEmployees(newDept.getEmployees());
                    return departmentRepository.save(dept);
                })
                .orElse(null);
    }

    // DELETE
    public boolean deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // CUSTOM QUERY (ví dụ)
    public List<Department> getDepartmentsWithManyEmployees(int minSize) {
        return departmentRepository.findDepartmentsWithMoreEmployees(minSize);
    }
}
