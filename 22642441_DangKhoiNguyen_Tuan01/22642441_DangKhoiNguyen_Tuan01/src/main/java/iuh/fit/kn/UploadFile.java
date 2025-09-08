package iuh.fit.kn;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Paths;

@WebServlet(name = "uploadFile", value = "/UploadServlet")
@MultipartConfig
public class UploadFile extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();
        String[] fileInputs = {"file1", "file2", "file3", "file4", "file5"};

        for (int i = 1; i <= 5; i++) {
            Part filePart = req.getPart("file" + i);
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = filePart.getSubmittedFileName();
                out.println("<h3>File #" + i + ": " + fileName + "</h3>");

                // Đọc dữ liệu file (ví dụ text file)
                try (BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
                    String line;
                    out.println("<pre>");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                    out.println("</pre>");
                }
            } else {
                out.println("<h3>File #" + i + ": Chưa chọn file</h3>");
            }
        }
        System.out.println("</body></html>");
    }
}
