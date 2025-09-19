package iuh.fit.kn.service;

import iuh.fit.kn.dao.EmployeeDAO;
import iuh.fit.kn.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    public void save(Employee emp) {
        employeeDAO.save(emp);
    }

    public void update(Employee emp) {
        employeeDAO.update(emp);
    }

    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}
