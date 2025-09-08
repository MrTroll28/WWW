package iuh.fit.kn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "uploadToDatabase", value = "/uploadDtb")
@MultipartConfig
public class UploadToDatabase extends HttpServlet {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=UploadFileServletDB;encrypt=false;trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "sapassword";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        InputStream inputStream = null;
        Part filePart = req.getPart("portrait");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        String message = "";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "INSERT INTO contacts (first_name, last_name, photo) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);

            if (inputStream != null) {
                stmt.setBinaryStream(3, inputStream, (int) filePart.getSize());
            } else {
                stmt.setNull(3, Types.BLOB);
            }

            int row = stmt.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            message = "ERROR: " + ex.getMessage();
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ignore) {}
            try { if (conn != null) conn.close(); } catch (SQLException ignore) {}
        }

        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<p>First Name: " + firstName + "</p>");
        out.println("<p>Last Name: " + lastName + "</p>");
        if (inputStream != null) {
            out.println("<p>Portrait: " + filePart.getSubmittedFileName() + "</p>");
        } else {
            out.println("<p>No portrait uploaded.</p>");
        }
    }
}
