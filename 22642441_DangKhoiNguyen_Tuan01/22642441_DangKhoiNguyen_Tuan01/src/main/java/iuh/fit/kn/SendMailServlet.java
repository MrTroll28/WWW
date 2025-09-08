package iuh.fit.kn;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.activation.DataHandler;
import jakarta.mail.Authenticator;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
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
import java.util.Properties;

@WebServlet("/sendMail")
@MultipartConfig
public class SendMailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Dotenv dotenv = Dotenv.load();
    private final String USERNAME = "nguyenoke123@gmail.com";
    private final String PASSWORD = dotenv.get("PASSWORD");

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        Part filePart = request.getPart("attachment");

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

            MimeMessage mess = new MimeMessage(session);
            mess.setFrom(USERNAME);
            mess.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(to));
            mess.setSubject(subject, "UTF-8");

            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(message, "UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            if (filePart != null && filePart.getSize() > 0) {
                MimeBodyPart attachPart = new MimeBodyPart();
                String fileName = filePart.getSubmittedFileName();
                InputStream fileContent = filePart.getInputStream();
                attachPart.setFileName(fileName);
                attachPart.setDataHandler(new DataHandler(new ByteArrayDataSource(fileContent,
                        getServletContext().getMimeType(fileName))));
                multipart.addBodyPart(attachPart); }
            mess.setContent(multipart);

            // Gửi email
            jakarta.mail.Transport.send(mess);
            out.println("<html><body>");
            out.println("<h1>Email đã được gửi thành công!</h1>");
        } catch (Exception e) {
            throw new ServletException("Lỗi gửi mail: " + e.getMessage(), e);
        }
    }
}
