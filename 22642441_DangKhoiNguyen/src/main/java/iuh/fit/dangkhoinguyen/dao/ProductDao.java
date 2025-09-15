package iuh.fit.dangkhoinguyen.dao;

import iuh.fit.dangkhoinguyen.beans.Product;
import iuh.fit.dangkhoinguyen.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private DBUtil dbUtil;

    public ProductDao(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setModel(rs.getString("model"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setImageUrl(rs.getString("image_url"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Retrieved " + list.size() + " products from database.");
        return list;
    }

    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE id = " + productId;
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setModel(rs.getString("model"));
                p.setDescription(rs.getString("description"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));
                p.setImageUrl(rs.getString("image_url"));
                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
