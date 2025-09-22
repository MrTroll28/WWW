package iuh.fit.bai6.dao;

import iuh.fit.bai6.model.TinTuc;
import iuh.fit.bai6.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TinTucDAO {
    private DBUtil dbUtil;

    public TinTucDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource);
    }

    public List<TinTuc> getAllTinTuc() {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM tintuc";
        try (Connection con = dbUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TinTuc tt = new TinTuc(
                        rs.getString("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                );
                list.add(tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TinTuc> getByDanhMuc(int madm) {
        List<TinTuc> list = new ArrayList<>();
        String sql = "SELECT * FROM tintuc WHERE MADM = ?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, madm);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TinTuc tt = new TinTuc(
                        rs.getString("MATT"),
                        rs.getString("TIEUDE"),
                        rs.getString("NOIDUNGTT"),
                        rs.getString("LIENKET"),
                        rs.getInt("MADM")
                );
                list.add(tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(TinTuc tt) {
        String sql = "INSERT INTO tintuc (MATT, TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tt.getMatt());
            ps.setString(2, tt.getTieude());
            ps.setString(3, tt.getNoidungtt());
            ps.setString(4, tt.getLienket());
            ps.setInt(5, tt.getMadm());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String matt) {
        String sql = "DELETE FROM tintuc WHERE MATT=?";
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, matt);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
