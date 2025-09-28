package me.kn.midterm3.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.kn.midterm3.dao.AccountDAO;
import me.kn.midterm3.model.Account;

import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private AccountDAO accountDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        accountDAO = new AccountDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acction = req.getParameter("action");

        if (acction != null) {
            switch (acction) {
                case "CREATE" -> {
                    req.getRequestDispatcher("account-form.jsp").forward(req, resp);
                }
                case "SEARCH" -> {
                    String tieuChi = req.getParameter("tieuChi");

                    if (tieuChi.equals("AMOUNT")) {
                        String minStr = req.getParameter("min");
                        String maxStr = req.getParameter("max");

                        Double min = minStr.isBlank() ? null : Double.parseDouble(minStr);
                        Double max = maxStr.isBlank() ? null : Double.parseDouble(maxStr);

                        if (min == null & max == null) {
                            req.setAttribute("accountList", accountDAO.findAll());
                        } else {
                            req.setAttribute("accountList", accountDAO.findAmountInRange(min, max));
                        }
                    } else {
                        String address = req.getParameter("address");
                        if (address.isBlank()){
                            req.setAttribute("accountList", accountDAO.findAll());
                        } else {
                            req.setAttribute("accountList", accountDAO.findByAddress(address));
                        }
                    }
                    req.getRequestDispatcher("account-list.jsp").forward(req, resp);
                }
            }
            return;
        }
        req.setAttribute("accountList", accountDAO.findAll());
        req.getRequestDispatcher("account-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerName = req.getParameter("ownerName");
        String cardNumber = req.getParameter("cardNumber");
        String ownerAddress = req.getParameter("ownerAddress");
        double amount = Double.parseDouble(req.getParameter("amount"));

        Account account = new Account(null, ownerName, cardNumber, ownerAddress, amount);
        accountDAO.save(account);
        resp.sendRedirect("account");
    }
}
