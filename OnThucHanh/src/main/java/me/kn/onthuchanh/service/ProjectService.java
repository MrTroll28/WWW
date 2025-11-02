package me.kn.onthuchanh.service;

import lombok.RequiredArgsConstructor;
import me.kn.onthuchanh.model.Project;
import me.kn.onthuchanh.repo.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository repo;

    public List<Project> getAll(){
        return repo.findAll();
    }

    public Project get(Long id){
        return repo.findById(id).orElse(null);
    }

    public void save(Project p){
        repo.save(p);
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
