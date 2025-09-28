package me.kn.midterm2.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.kn.midterm2.dao.LoaiThuocDAO;

import java.io.IOException;

@WebServlet("/loaiThuoc")
public class LoaiThuocServlet extends HttpServlet {

    private LoaiThuocDAO loaiThuocDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        loaiThuocDAO = new LoaiThuocDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("loaiThuocList", loaiThuocDAO.findAll());
        req.getRequestDispatcher("/loaithuoc-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
