package dao;

import entity.Prosjekt;
import jakarta.persistence.*;
import util.JPAUtil;

public class ProjektDAO {

    public class ProsjektDAO {

        public void lagre(Prosjekt p) {
            EntityManager em = JPAUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            em.close();
        }
    }
}
