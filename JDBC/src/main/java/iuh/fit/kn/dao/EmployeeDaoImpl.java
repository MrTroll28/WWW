package iuh.fit.kn.dao;

import iuh.fit.kn.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {
    private JdbcTemplate jdbcTemplate;

    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // Using BeanPropertyRowMapper to automatically map columns to fields
//    private RowMapper<Employee> rowMapper = new RowMapper<>() {
//        @Override
//        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return new Employee(
//                    rs.getInt("id"),
//                    rs.getString("role"),
//                    rs.getString("name")
//            );
//        }
//    };

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employees (id, role, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getId(), employee.getRole(), employee.getName());
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET role = ?, name = ? WHERE id = ?";
        jdbcTemplate.update(sql, employee.getRole(), employee.getName(), employee.getId());
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Employee.class), id);
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
