package me.kn.midterm1.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.kn.midterm1.beans.Cart;
import me.kn.midterm1.dao.ProductDAO;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/giohang.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "ADD":
                    String idStr = req.getParameter("id");

                    if (idStr != null) {
                        Long id = Long.parseLong(idStr);
                        cart.add(productDAO.findById(id));
                        resp.sendRedirect("products");
                    }
                    break;
                case "remove":
                    Long removeProductId = Long.parseLong(req.getParameter("productId"));
                    // Logic to remove product from cart
                    // e.g., cartService.removeFromCart(removeProductId);
                    break;
                case "update":
                    Long updateProductId = Long.parseLong(req.getParameter("productId"));
                    Integer newQuantity = Integer.parseInt(req.getParameter("quantity"));
                    // Logic to update product quantity in cart
                    // e.g., cartService.updateCart(updateProductId, newQuantity);
                    break;
                default:
                    // Handle unknown action
                    break;
            }
        }
    }
}
