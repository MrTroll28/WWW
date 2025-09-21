package iuh.fit.jpa.debug;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/debug")
public class DebugJdbcController {
    private final JdbcTemplate jdbcTemplate;
    public DebugJdbcController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/jdbc-employees")
    public List<Map<String, Object>> jdbcEmployees() {
        String sql = "SELECT * FROM employees2";
        return jdbcTemplate.queryForList(sql);
    }
}
