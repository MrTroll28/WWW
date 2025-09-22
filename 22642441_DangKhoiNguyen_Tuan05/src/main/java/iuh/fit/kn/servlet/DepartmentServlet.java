package iuh.fit.kn.servlet;

import iuh.fit.kn.dao.DepartmentDAO;
import iuh.fit.kn.dao.EmployeeDAO;
import iuh.fit.kn.model.Department;
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

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
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
                List<Department> allDepartments = departmentDAO.getAllDepartments();
                req.setAttribute("departments", allDepartments);
                req.getRequestDispatcher("/department-list.jsp").forward(req, resp);
                break;
            case "new":
                req.setAttribute("department", departmentDAO.getAllDepartments());
                req.getRequestDispatcher("/department-form.jsp").forward(req, resp);
                break;
            case "edit":
                // TODO: Them sau
                break;
            case "delete":
                departmentDAO.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("departments");
            case "listbyid":
                String deptIdStr = req.getParameter("deptId");
                List<Department> list;
                if (deptIdStr != null) {
                    int deptId = Integer.parseInt(deptIdStr);
                    list = departmentDAO.getAllDepartments();
                } else {
                    list = departmentDAO.getAllDepartments(); // default department ID
                }
                req.setAttribute("departments", list);
                req.setAttribute("departments", departmentDAO.getAllDepartments());
                req.getRequestDispatcher("/department-list.jsp").forward(req, resp);
                break;
        }
    }
}
