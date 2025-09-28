package me.kn.midterm3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import me.kn.midterm3.model.Account;
import me.kn.midterm3.util.JPAUtil;

import java.util.List;

public class AccountDAO {

    public List<Account> findAll(){
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            String jpql = """
                    SELECT a FROM Account a
                    """;

            TypedQuery<Account> query = em.createQuery(jpql, Account.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void save(Account account) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public Account findById(Long id) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            return em.find(Account.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public List<Account> findAmountInRange(Double min, Double max) {
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            StringBuilder jpql = new StringBuilder("SELECT a FROM Account a WHERE 1=1");
            if (min != null) jpql.append(" AND a.amount >= :min");
            if (max != null) jpql.append(" AND a.amount <= :max");

            TypedQuery<Account> query = em.createQuery(jpql.toString(), Account.class);
            if (min != null) query.setParameter("min", min);
            if (max != null) query.setParameter("max", max);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public List<Account> findByAddress(String address){
        EntityManager em = JPAUtil.getEmf().createEntityManager();
        try {
            String jpql = """
                    SELECT a FROM Account a
                    WHERE a.ownerAddress LIKE concat("%", :address, "%") 
                    """;

            TypedQuery<Account> query = em.createQuery(jpql, Account.class);
            query.setParameter("address", address);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
