package dao;

import entity.Deltakelse;
import jakarta.persistence.*;
import util.JPAUtil;
import java.util.List;

public class DeltakelseDAO {

    public List<Deltakelse> finnAlle() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Deltakelse> liste =
                em.createQuery("SELECT d FROM Deltakelse d", Deltakelse.class)
                        .getResultList();

        em.close();
        return liste;
    }

    public Long totalTimer(int prosjektId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Long sum = em.createQuery(
                        "SELECT SUM(d.timer) FROM Deltakelse d WHERE d.prosjekt.prosjekt_id = :id", Long.class)
                .setParameter("id", prosjektId)
                .getSingleResult();

        em.close();
        return sum;
    }
}
