package iuh.fit.jpa.controllerWEB;

import iuh.fit.jpa.model.Employee;
import iuh.fit.jpa.service.DepartmentService;
import iuh.fit.jpa.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeWebController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @GetMapping
    public String index(@RequestParam(required=false) String name,
                        @RequestParam(required=false) Long departmentId,
                        Model model) {
        List<Employee> employees;
        if (name != null && !name.trim().isEmpty()) {
            employees = employeeService.getEmployeesByName(name.trim());
        } else if (departmentId != null) {
            employees = employeeService.getEmployeesByDepartment(departmentId);
        } else {
            employees = employeeService.getAllEmployees();
        }
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/form";
    }

    @PostMapping
    public String save(@ModelAttribute Employee e, RedirectAttributes ra) {
        employeeService.addEmployee(e);
        ra.addFlashAttribute("success", "Employee created successfully!");
        return "redirect:/employees";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Employee emp = employeeService.getEmployeeById(id).orElse(null);
        model.addAttribute("employee", emp);
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "employees/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Employee e, RedirectAttributes ra) {
        e.setEmpId(id);
        employeeService.updateEmployee(id, e);
        ra.addFlashAttribute("success", "Employee updated successfully!");
        return "redirect:/employees";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        boolean ok = employeeService.deleteEmployee(id);
        if (ok) ra.addFlashAttribute("success", "Employee deleted successfully!");
        else ra.addFlashAttribute("error", "Employee not found!");
        return "redirect:/employees";
    }
}