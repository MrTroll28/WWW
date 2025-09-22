package iuh.fit.bai6.servlet;

import iuh.fit.bai6.dao.DanhMucDAO;
import iuh.fit.bai6.model.DanhMuc;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/danhmuc")
public class DanhMucServlet extends HttpServlet {
    @Resource(name = "jdbc/tuan5db")
    private DataSource dataSource;
    private DanhMucDAO dao;

    @Override
    public void init() {
        dao = new DanhMucDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "form":
                req.getRequestDispatcher("/danhmuc-form.jsp").forward(req, resp);
                break;
            case "delete":
                int madm = Integer.parseInt(req.getParameter("madm"));
                dao.delete(madm);
                resp.sendRedirect(req.getContextPath() + "/danhmuc");
                break;
            case "new":
                req.getRequestDispatcher("/danhmuc-form.jsp").forward(req, resp);
                break;
            default:
                List<DanhMuc> list = dao.getAllDanhMuc();
                req.setAttribute("danhmucList", list);
                req.getRequestDispatcher("/danhmuc-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String madmStr = req.getParameter("madm");
        String ten = req.getParameter("tendanhmuc");
        String quanly = req.getParameter("nguoiquanly");
        String ghichu = req.getParameter("ghichu");

        DanhMuc dm = new DanhMuc();
        dm.setTendanhmuc(ten);
        dm.setNguoiquanly(quanly);
        dm.setGhichu(ghichu);

        if (madmStr == null || madmStr.isEmpty()) {
            dao.save(dm); // thêm mới
        } else {
            dm.setMadm(Integer.parseInt(madmStr));
            dao.update(dm); // cập nhật
        }

        resp.sendRedirect(req.getContextPath() + "/danhmuc");
    }
}