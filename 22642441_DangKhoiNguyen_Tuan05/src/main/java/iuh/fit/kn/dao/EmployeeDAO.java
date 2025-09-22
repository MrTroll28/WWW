package iuh.fit.kn.dao;

import iuh.fit.kn.model.Employee;
import iuh.fit.kn.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbUtil;

    public EmployeeDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setSalary(rs.getDouble("salary"));
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Employee> getAllByDepartmentId(int departmentId) {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = " + departmentId;
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setSalary(rs.getDouble("salary"));
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Employee employee) {
        String sql = "INSERT INTO employees (name, department_id, salary) VALUES (?, ?, ?)";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setDouble(3, employee.getSalary());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, department_id = ?, salary = ? WHERE id = ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, employee.getName());
            ps.setInt(2, employee.getDepartmentId());
            ps.setDouble(3, employee.getSalary());
            ps.setInt(4, employee.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int employeeId) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getById(int empId) {
        Employee emp = null;
        String sql = "SELECT * FROM employees WHERE id = " + empId;
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDepartmentId(rs.getInt("department_id"));
                emp.setSalary(rs.getDouble("salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
}
