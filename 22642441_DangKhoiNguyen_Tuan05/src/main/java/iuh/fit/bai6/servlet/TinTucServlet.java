package iuh.fit.bai6.servlet;

import iuh.fit.bai6.dao.DanhMucDAO;
import iuh.fit.bai6.dao.TinTucDAO;
import iuh.fit.bai6.model.DanhMuc;
import iuh.fit.bai6.model.TinTuc;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/tintuc")
public class TinTucServlet extends HttpServlet {
    @Resource(name = "jdbc/tuan5db")
    private DataSource dataSource;
    private TinTucDAO tinTucDAO;
    private DanhMucDAO danhMucDAO;

    @Override
    public void init() {
        tinTucDAO = new TinTucDAO(dataSource);
        danhMucDAO = new DanhMucDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "form":
                // truyền danh mục vào form để chọn
                List<DanhMuc> dmList = danhMucDAO.getAllDanhMuc();
                req.setAttribute("danhmucList", dmList);
                req.getRequestDispatcher("/tintuc-form.jsp").forward(req, resp);
                break;
            case "delete":
                String matt = req.getParameter("matt");
                tinTucDAO.delete(matt);
                resp.sendRedirect(req.getContextPath() + "/tintuc");
                break;
            case "new":
                // truyền danh mục vào form để chọn
                List<DanhMuc> dmListNew = danhMucDAO.getAllDanhMuc();
                req.setAttribute("danhmucList", dmListNew);
                req.getRequestDispatcher("/tintuc-form.jsp").forward(req, resp);
                break;
            default:
                String madmStr = req.getParameter("madm");
                List<TinTuc> list;
                if (madmStr != null && !madmStr.isEmpty()) {
                    int madm = Integer.parseInt(madmStr);
                    list = tinTucDAO.getByDanhMuc(madm);
                } else {
                    list = tinTucDAO.getAllTinTuc();
                }
                req.setAttribute("tinList", list);
                req.getRequestDispatcher("/tintuc-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String matt = req.getParameter("matt");
        String tieude = req.getParameter("tieude");
        String noidung = req.getParameter("noidung");
        String lienket = req.getParameter("lienket");
        int madm = Integer.parseInt(req.getParameter("madm"));

        TinTuc tt = new TinTuc(matt, tieude, noidung, lienket, madm);
        tinTucDAO.save(tt);

        resp.sendRedirect(req.getContextPath() + "/tintuc");
    }
}
