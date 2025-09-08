package iuh.fit.dangkhoinguyen.Servlet;

import iuh.fit.dangkhoinguyen.Model.Account;
import iuh.fit.dangkhoinguyen.Util.AccountUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountUtil accountUtil;
    @Resource(name = "jdbc/storedb")
    private DataSource dataSource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            accountUtil = new AccountUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        java.io.PrintWriter out = resp.getWriter();
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        java.sql.Date dob = java.sql.Date.valueOf(req.getParameter("dob"));

        Account account = new Account(firstName, lastName, email, password, dob);

        try {
            accountUtil.addAccount(account);
            List<Account> accounts = accountUtil.getAccounts();
            req.setAttribute("accounts", accounts);
            req.getRequestDispatcher("account-result.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
