package me.kn.onthuchanh.repo;

import me.kn.onthuchanh.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
