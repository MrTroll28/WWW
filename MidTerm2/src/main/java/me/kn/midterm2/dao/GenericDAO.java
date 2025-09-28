package me.kn.midterm2.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import me.kn.midterm2.model.Thuoc;
import me.kn.midterm2.util.JPAUtil;

import java.util.List;

public class GenericDAO<T> {

    private Class<T> type;

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    public List<T> findAll() {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            String jpql = """
                    SELECT e 
                    FROM %s e
                    """.formatted(type.getSimpleName());

            TypedQuery<T> query = em.createQuery(jpql, type);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public T findById(Long id) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            return em.find(type, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    public void save(T entity) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
