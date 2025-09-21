package iuh.fit.jpa.debug;

import iuh.fit.jpa.model.Employee;
import iuh.fit.jpa.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartupCheckConfig {

    @Bean
    CommandLineRunner printEmployees(EmployeeRepository repo) {
        return args -> {
            System.out.println("----- PRINT EMPLOYEES FROM JPA -----");
            repo.findAll().forEach(e -> {
                System.out.println("EMP toString: " + e); // nếu lombok toString rỗng => lombok ko hoạt động
                try {
                    System.out.println("  getEmpId(): " + e.getEmpId());
                    System.out.println("  getEmpName(): " + e.getEmpName());
                    System.out.println("  getSalary(): " + e.getSalary());
                    System.out.println("  dept (null?): " + (e.getDepartment() == null ? "NULL" : e.getDepartment().getDeptName()));
                } catch (Exception ex) {
                    System.out.println("  Exception accessing getters: " + ex.getMessage());
                }
            });
            System.out.println("----- END PRINT -----");
        };
    }
}
