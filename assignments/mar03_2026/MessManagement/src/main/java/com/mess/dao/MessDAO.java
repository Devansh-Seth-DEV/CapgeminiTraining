package com.mess.dao;

import com.mess.entity.Item;
import com.mess.entity.Supplier;
import com.mess.entity.Supply;
import com.mess.util.EntityManagerFactoryProvider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class MessDAO {
    public void populateDatabase() {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Supplier s1 = new Supplier(1, "Rajan enterprices", 4.5, "Delhi");
            Supplier s2 = new Supplier(2, "Indian supply", 3.4, "Gurugram");

            Item i1 = new Item(1, "Tomato", "Veg");
            Item i2 = new Item(2, "Onion", "Veg");
            Item i3 = new Item(3, "Sugar", "Groc");

            Supply sup1 = new Supply(1, s1, i2, LocalDate.of(2019, 3, 3), 36, 50);
            Supply sup2 = new Supply(2, s1, i3, LocalDate.of(2019, 5, 5), 10, 45);
            Supply sup3 = new Supply(3, s2, i1, LocalDate.of(2019, 8, 15), 50, 10);

            em.persist(s1);
            em.persist(s2);

            em.persist(i1);
            em.persist(i2);
            em.persist(i3);

            em.persist(sup1);
            em.persist(sup2);
            em.persist(sup3);

            transaction.commit();

            System.out.println("Database populated successfully!");
        } catch (Exception e) {
            if (transaction.isActive())
            	transaction.rollback();

            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // 1. Write the query to find all suppliers name.
    public List<String> getAllSupplierNames() {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        String hql = "SELECT s.sname FROM Supplier s";

        List<String> names = em.createQuery(hql, String.class)
        		.getResultList();

        em.close();
        return names;
    }

    // 2. Write query to find the suppliers from Delhi.
    public List<Supplier> getSuppliersByCity(String city) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        String hql = "SELECT s FROM Supplier s WHERE s.city = :cityName";

        TypedQuery<Supplier> query = em.createQuery(hql, Supplier.class);
        query.setParameter("cityName", city);

        List<Supplier> list = query.getResultList();

        em.close();

        return list;
    }

    // 3. Find the names of items supplieed by supplier with sid = 1
    public List<String> getItemNamesBySupplierId(int sid) {
        EntityManager em = EntityManagerFactoryProvider.getEntityManager();
        String hql = "SELECT s.item.name FROM Supply s WHERE s.supplier.sid = :id";

        TypedQuery<String> query = em.createQuery(hql, String.class);
        query.setParameter("id", sid);

        List<String> names = query.getResultList();

        em.close();

        return names;
    }
}
