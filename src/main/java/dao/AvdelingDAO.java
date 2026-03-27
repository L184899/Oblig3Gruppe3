package dao;

import entity.Avdeling;
import jakarta.persistence.*;
import util.JPAUtil;
import java.util.List;

public class AvdelingDAO {

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Avdeling a = em.find(Avdeling.class, id);
        em.close();
        return a;
    }

    public List<Avdeling> finnAlle() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Avdeling> liste =
                em.createQuery("SELECT a FROM Avdeling a", Avdeling.class)
                        .getResultList();

        em.close();
        return liste;
    }
}
