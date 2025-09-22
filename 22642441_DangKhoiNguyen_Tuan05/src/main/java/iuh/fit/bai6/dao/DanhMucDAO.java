package iuh.fit.bai6.dao;

import iuh.fit.bai6.model.DanhMuc;
import iuh.fit.bai6.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO {
    private DBUtil dbUtil;

    public DanhMucDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<DanhMuc> getAllDanhMuc() {
        List<DanhMuc> list = new ArrayList<>();
        String sql = "SELECT * FROM danhmuc";
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                DanhMuc dm = new DanhMuc();
                dm.setMadm(rs.getInt("MADM"));
                dm.setTendanhmuc(rs.getString("TENDANHMUC"));
                dm.setNguoiquanly(rs.getString("NGUOIQUANLY"));
                dm.setGhichu(rs.getString("GHICHU"));
                list.add(dm);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public DanhMuc findById(int madm) {
        String sql = "SELECT * FROM danhmuc WHERE MADM = ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, madm);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DanhMuc(
                        rs.getInt("madm"),
                        rs.getString("tendanhmuc"),
                        rs.getString("nguoiquanly"),
                        rs.getString("ghichu")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(DanhMuc dm) {
        String sql = "INSERT INTO danhmuc (TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES (?, ?, ?)";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dm.getTendanhmuc());
            ps.setString(2, dm.getNguoiquanly());
            ps.setString(3, dm.getGhichu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(DanhMuc dm) {
        String sql = "UPDATE danhmuc SET TENDANHMUC=?, NGUOIQUANLY=?, GHICHU=? WHERE MADM=?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dm.getTendanhmuc());
            ps.setString(2, dm.getNguoiquanly());
            ps.setString(3, dm.getGhichu());
            ps.setInt(4, dm.getMadm());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int madm) {
        String sql = "DELETE FROM danhmuc WHERE MADM=?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, madm);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}