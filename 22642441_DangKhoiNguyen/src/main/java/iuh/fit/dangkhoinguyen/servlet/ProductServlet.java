package iuh.fit.dangkhoinguyen.servlet;

import iuh.fit.dangkhoinguyen.beans.Product;
import iuh.fit.dangkhoinguyen.dao.ProductDao;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet({"/products", "/product"})
public class ProductServlet extends HttpServlet {
    private ProductDao productDao;
    @Resource(name = "jdbc/shopdb" )
    private DataSource dataSource;

    @Override
    public void init() {
        productDao = new ProductDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        System.out.println("Product id = " + id);

        if (id != null) {
            int productId = Integer.parseInt(id);
            Product product = productDao.getProductById(productId);
            if (product != null) {
                req.setAttribute("product", product);
                req.getRequestDispatcher("product-detail.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            }
        } else {
            List<Product> products = productDao.getAllProducts();
            System.out.println("Product list size = " + products.size());
            req.setAttribute("products", products);
            req.getRequestDispatcher("product-list.jsp").forward(req, resp);
        }
    }
}
