package iuh.fit.kn;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "bai04", value = "/processFormUpLoad")
public class Bai04 extends HttpServlet {
    private String message;

    public void init() {
        message = "Đây là nội dung của form";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Get data from form
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String name = first + " " + last;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String facebook = request.getParameter("facebook");
        String shortBio = request.getParameter("bio");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Form Submitted Successfully!</h1>");
        out.println("<p>Name: " + name + "</p>");
        out.println("<p>Username: " + username + "</p>");
        out.println("<p>Password: " + password + "</p>");
        out.println("<p>Facebook: " + facebook + "</p>");
        out.println("<p>Short Bio: " + shortBio + "</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
