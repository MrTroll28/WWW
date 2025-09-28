package me.kn.midterm2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import me.kn.midterm2.model.Thuoc;
import me.kn.midterm2.util.JPAUtil;

import java.util.List;

public class ThuocDAO extends GenericDAO<Thuoc> {

    public ThuocDAO() {
        super(Thuoc.class);
    }

    public List<Thuoc> findByLoaiThuocId(Long loaiThuocId) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            String jpql = """
                    SELECT t FROM Thuoc t
                    WHERE t.loaiThuoc.id = :loaiThuocId
                    """;

            TypedQuery<Thuoc> query = em.createQuery(jpql, Thuoc.class);
            query.setParameter("loaiThuocId", loaiThuocId);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }
}
