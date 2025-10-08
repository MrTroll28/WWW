package me.kn.mongo.repository;

import me.kn.mongo.model.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
    Department findByDeptName(String deptName);
}
