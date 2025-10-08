package me.kn.mongo.config;

import me.kn.mongo.model.Department;
import me.kn.mongo.model.Employee;
import me.kn.mongo.repository.DepartmentRepository;
import me.kn.mongo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(DepartmentRepository deptRepo, EmployeeRepository empRepo) {
        return args -> {
            deptRepo.deleteAll();
            empRepo.deleteAll();

            Department hr = deptRepo.save(new Department("Phòng Nhân Sự"));
            Department acc = deptRepo.save(new Department("Phòng Kế Toán"));
            Department it = deptRepo.save(new Department("Phòng IT"));

            empRepo.save(new Employee("Nguyễn Văn A", 1200.0, LocalDate.of(1998,6,21), hr));
            empRepo.save(new Employee("Trần Thị B", 1500.0, LocalDate.of(1999,8,10), acc));
            empRepo.save(new Employee("Lê Văn C", 2000.0, LocalDate.of(2000,1,5), it));
        };
    }
}
