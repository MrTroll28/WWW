package me.kn.onthuchanh.controller;

import lombok.RequiredArgsConstructor;
import me.kn.onthuchanh.model.Project;
import me.kn.onthuchanh.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public String list(Model model){
        model.addAttribute("projects", service.getAll());
        return "projects/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addForm(Model model){
        model.addAttribute("project", new Project());
        return "projects/form";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String save(Project p){
        service.save(p);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("project", service.get(id));
        return "projects/form";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/projects";
    }
}
