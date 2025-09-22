package iuh.fit.kn.servlet;

import iuh.fit.kn.dao.DepartmentDAO;
import iuh.fit.kn.dao.EmployeeDAO;
import iuh.fit.kn.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    @Resource(name = "jdbc/tuan5db")
    private DataSource dataSource;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            employeeDAO = new EmployeeDAO(dataSource);
            departmentDAO = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                List<Employee> allEmployees = employeeDAO.getAllEmployees();
                req.setAttribute("employees", allEmployees);
                req.getRequestDispatcher("/employee-list.jsp").forward(req, resp);
                break;
            case "listByDept":
                String deptIdStr = req.getParameter("deptId");
                List<Employee> list;
                if (deptIdStr != null) {
                    int deptId = Integer.parseInt(deptIdStr);
                    list = employeeDAO.getAllByDepartmentId(deptId);
                } else {
                    list = employeeDAO.getAllByDepartmentId(1); // default department ID
                }
                req.setAttribute("employees", list);
                req.setAttribute("departments", departmentDAO.getAllDepartments());
                req.getRequestDispatcher("/employee-list.jsp").forward(req, resp);
                break;
            case "new":
                req.setAttribute("departments", departmentDAO.getAllDepartments());
                req.getRequestDispatcher("/employee-form.jsp").forward(req, resp);
                break;
            case "edit":
                String empIdStr = req.getParameter("id");
                if (empIdStr != null) {
                    int empId = Integer.parseInt(empIdStr);
                    Employee emp = employeeDAO.getById(empId);
                    req.setAttribute("employee", emp);
                    req.setAttribute("departments", departmentDAO.getAllDepartments());
                    req.getRequestDispatcher("/employee-form.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("employees?action=list");
                }
                break;
            case "delete":
                String delEmpIdStr = req.getParameter("id");
                if (delEmpIdStr != null) {
                    int delEmpId = Integer.parseInt(delEmpIdStr);
                    employeeDAO.delete(delEmpId);
                }
                resp.sendRedirect("employees");
                break;
        }
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String empIdStr = req.getParameter("id");
        int id;
        if (empIdStr == null || empIdStr.isEmpty()) {
            id = 0; // New employee
        } else {
            id = Integer.parseInt(empIdStr); // Existing employee
        }

        String name = req.getParameter("name");
        int departmentId = Integer.parseInt(req.getParameter("departmentId"));
        double salary = Double.parseDouble(req.getParameter("salary"));

        Employee emp = new Employee(id, name, departmentId, salary);
        if (id == 0) {
            employeeDAO.save(emp);
        } else {
            employeeDAO.update(emp);
        }
        resp.sendRedirect("employees?deptId=" + departmentId);
    }
}
