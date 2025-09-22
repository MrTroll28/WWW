package iuh.fit.kn.dao;

import iuh.fit.kn.model.Department;
import iuh.fit.kn.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private DBUtil dbUtil;

    public DepartmentDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Department dept = new Department();
                dept.setId(rs.getInt("id"));
                dept.setName(rs.getString("name"));
                departments.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departments;
    }

    public void save(Department department) {
        String sql = "INSERT INTO departments (name) VALUES ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, department.getName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Department department) {
        String sql = "UPDATE departments SET name = ? WHERE id = ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int departmentId) {
        String sql = "DELETE FROM departments WHERE id = ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, departmentId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
