package dao;

import entity.Ansatt;
import jakarta.persistence.*;
import util.JPAUtil;

import java.util.List;

public class AnsattDAO {

    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Ansatt a = em.find(Ansatt.class, id);
        em.close();
        return a;
    }

    public List<Ansatt> finnAlle() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Ansatt> liste =
                em.createQuery("SELECT a FROM Ansatt a", Ansatt.class)
                        .getResultList();

        em.close();
        return liste;
    }
}
