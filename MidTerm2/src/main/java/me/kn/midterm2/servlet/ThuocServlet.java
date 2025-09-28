package me.kn.midterm2.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.kn.midterm2.dao.LoaiThuocDAO;
import me.kn.midterm2.dao.ThuocDAO;
import me.kn.midterm2.model.LoaiThuoc;
import me.kn.midterm2.model.Thuoc;

import java.io.IOException;

@WebServlet("/thuoc")
public class ThuocServlet extends HttpServlet {

    private ThuocDAO thuocDAO;
    private LoaiThuocDAO loaiThuocDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        thuocDAO = new ThuocDAO();
        loaiThuocDAO = new LoaiThuocDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("loaiThuocList", loaiThuocDAO.findAll());

        String acction = req.getParameter("action");
        if (acction != null && acction.equals("CREATE")) {
            req.getRequestDispatcher("/thuoc-form.jsp").forward(req, resp);
            return;
        }

        String loaiThuocIdStr = req.getParameter("loaiThuocId");
        if (loaiThuocIdStr != null && !loaiThuocIdStr.equals("All")) {
            Long loaiThuocId = Long.parseLong(loaiThuocIdStr);
            req.setAttribute("thuocList", thuocDAO.findByLoaiThuocId(loaiThuocId));
            req.getRequestDispatcher("/thuoc-list.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("thuocList", thuocDAO.findAll());
        req.getRequestDispatcher("/thuoc-list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tenThuoc = req.getParameter("tenThuoc");
        Double gia = Double.parseDouble(req.getParameter("gia"));
        int namSX = Integer.parseInt(req.getParameter("namSX"));
        Long loaiThuocId = Long.parseLong(req.getParameter("loaiThuocId"));

        LoaiThuoc loaiThuoc = loaiThuocDAO.findById(loaiThuocId);

        Thuoc thuoc = new Thuoc( null, tenThuoc, gia, namSX, loaiThuoc);

        thuocDAO.save(thuoc);
        resp.sendRedirect("thuoc");
    }
}
