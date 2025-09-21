package iuh.fit.jpa.repository;

import iuh.fit.jpa.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Tìm department theo tên
    @Query("SELECT d FROM Department d WHERE d.deptName = :name")
    Department findByDeptName(@Param("name") String name);

    // Lấy tất cả department có số lượng nhân viên > minSize
    @Query("SELECT d FROM Department d WHERE SIZE(d.employees) > :minSize")
    List<Department> findDepartmentsWithMoreEmployees(@Param("minSize") int minSize);

    // Get all departments với danh sách employees (fetch join để tránh lazy loading)
    @Query("SELECT DISTINCT d FROM Department d LEFT JOIN FETCH d.employees")
    List<Department> findAllWithEmployees();
}
