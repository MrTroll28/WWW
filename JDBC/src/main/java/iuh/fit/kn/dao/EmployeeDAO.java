package iuh.fit.kn.dao;

import iuh.fit.kn.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    void save(Employee employee);
    void update(Employee employee);
    Employee getById(int id);
    List<Employee> getAll();
    void deleteById(int id);
}
