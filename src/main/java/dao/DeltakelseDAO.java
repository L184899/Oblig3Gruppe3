package dao;

import entity.Deltakelse;
import jakarta.persistence.*;
import util.JPAUtil;

public class DeltakelseDAO {

    public void lagre(Deltakelse d) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();
    }
}
