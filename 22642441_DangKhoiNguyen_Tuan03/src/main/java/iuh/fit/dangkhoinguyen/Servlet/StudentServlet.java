package iuh.fit.dangkhoinguyen.Servlet;

import iuh.fit.dangkhoinguyen.Model.Gender;
import iuh.fit.dangkhoinguyen.Model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.time.LocalDate;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    public StudentServlet() {
        super();
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        resp.setContentType("text/html");
        java.io.PrintWriter out = resp.getWriter();
        Student student = new Student();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Gender gender = Gender.valueOf(req.getParameter("gender").toUpperCase());
        String address = req.getParameter("address");
        String city = req.getParameter("city");
        Long pinCode = Long.parseLong(req.getParameter("pinCode"));
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String[] hobbies = req.getParameterValues("hobbies");

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDob(dob);
        student.setEmail(email);
        student.setPhone(phone);
        student.setGender(gender);
        student.setAddress(address);
        student.setCity(city);
        student.setPinCode(pinCode);
        student.setState(state);
        student.setCountry(country);
        student.setHobbies(hobbies);

        req.setAttribute("student", student);
        RequestDispatcher rd = req.getRequestDispatcher("student-result.jsp");
        rd.forward(req, resp);
    }
}
