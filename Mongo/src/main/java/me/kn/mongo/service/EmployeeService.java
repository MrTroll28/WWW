package me.kn.mongo.service;

import me.kn.mongo.model.Employee;
import me.kn.mongo.repository.DepartmentRepository;
import me.kn.mongo.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository empRepo;
    private final DepartmentRepository deptRepo;

    public EmployeeService(EmployeeRepository empRepo, DepartmentRepository deptRepo) {
        this.empRepo = empRepo;
        this.deptRepo = deptRepo;
    }

    public Employee create(Employee e) {
        return empRepo.save(e);
    }

    public List<Employee> findAll() {
        return empRepo.findAll();
    }

    public Optional<Employee> findById(String id) {
        return empRepo.findById(id);
    }

    public List<Employee> findByEmpName(String name) {
        return empRepo.findByEmpName(name);
    }

    public List<Employee> findByDepartmentId(String deptId) {
        return empRepo.findByDepartmentId(deptId);
    }

    public List<Employee> findBySalaryGreaterThan(Double salary) {
        return empRepo.findBySalaryGreaterThan(salary);
    }

    public Employee update(String id, Employee newEmp) {
        return empRepo.findById(id).map(e -> {
            e.setEmpName(newEmp.getEmpName());
            e.setSalary(newEmp.getSalary());
            e.setDob(newEmp.getDob());
            if (newEmp.getDepartment() != null && newEmp.getDepartment().getId() != null) {
                deptRepo.findById(newEmp.getDepartment().getId()).ifPresent(e::setDepartment);
            }
            return empRepo.save(e);
        }).orElse(null);
    }

    public boolean delete(String id) {
        if (!empRepo.existsById(id)) return false;
        empRepo.deleteById(id);
        return true;
    }
}
