package dao;

import entity.Prosjekt;
import jakarta.persistence.*;
import util.JPAUtil;
import java.util.List;

public class ProsjektDAO {

    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Prosjekt p = em.find(Prosjekt.class, id);
        em.close();
        return p;
    }

    public List<Prosjekt> finnAlle() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Prosjekt> liste =
                em.createQuery("SELECT p FROM Prosjekt p", Prosjekt.class)
                        .getResultList();

        em.close();
        return liste;
    }

    public Long tellDeltakelser(int prosjektId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Long count = em.createQuery(
                        "SELECT COUNT(d) FROM Deltakelse d WHERE d.prosjekt.prosjekt_id = :id", Long.class)
                .setParameter("id", prosjektId)
                .getSingleResult();

        em.close();
        return count;
    }
}
