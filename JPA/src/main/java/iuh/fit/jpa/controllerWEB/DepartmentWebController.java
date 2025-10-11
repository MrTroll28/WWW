package iuh.fit.jpa.controllerWEB;

import iuh.fit.jpa.model.Department;
import iuh.fit.jpa.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentWebController {
    private final DepartmentService service;

    @GetMapping
    public String index(@RequestParam(required=false) String name,
                        @RequestParam(required=false) Integer minSize,
                        Model model) {
        List<Department> departments;
        if (name != null && !name.trim().isEmpty()) {
            Department d = service.getDepartmentByName(name.trim());
            departments = d != null ? List.of(d) : List.of();
        } else if (minSize != null) {
            departments = service.getDepartmentsWithManyEmployees(minSize);
        } else {
            departments = service.getAllDepartments();
        }
        model.addAttribute("departments", departments);
        return "departments/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("department", new Department());
        return "departments/form";
    }

    @PostMapping
    public String save(@ModelAttribute Department d, RedirectAttributes ra) {
        service.addDepartment(d);
        ra.addFlashAttribute("success", "Department created successfully!");
        return "redirect:/departments";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Department dept = service.getDepartmentById(id).orElse(null);
        model.addAttribute("department", dept);
        return "departments/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Department d, RedirectAttributes ra) {
        d.setDeptId(id);
        service.updateDepartment(id, d);
        ra.addFlashAttribute("success", "Department updated successfully!");
        return "redirect:/departments";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        boolean ok = service.deleteDepartment(id);
        if (ok) ra.addFlashAttribute("success", "Department deleted successfully!");
        else ra.addFlashAttribute("error", "Department not found!");
        return "redirect:/departments";
    }
}