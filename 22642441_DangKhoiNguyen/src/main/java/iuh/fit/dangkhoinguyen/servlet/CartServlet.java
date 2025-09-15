package iuh.fit.dangkhoinguyen.servlet;

import iuh.fit.dangkhoinguyen.beans.CartBean;
import iuh.fit.dangkhoinguyen.beans.Product;
import iuh.fit.dangkhoinguyen.dao.ProductDao;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private ProductDao productDao;
    @Resource(name = "jdbc/shopdb")
    private DataSource dataSource;

    @Override
    public void init() throws SecurityException{
        try {
            productDao = new ProductDao(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        CartBean cart = (CartBean) session.getAttribute("cart");
        if (cart == null) {
            cart = new CartBean();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");
        try {
            if ("add".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                Product product = productDao.getProductById(productId);
                cart.addProduct(product);
            } else if ("remove".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                cart.removeProduct(productId);
            } else if ("update".equals(action)) {
                int productId = Integer.parseInt(req.getParameter("productId"));
                int quantity = Integer.parseInt(req.getParameter("quantity"));
                cart.updateQuantity(productId, quantity);
            } else if ("clear".equals(action)) {
                cart.clear();
            }
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            throw new ServletException(e);
        }
        resp.sendRedirect("cart");
    }
}
