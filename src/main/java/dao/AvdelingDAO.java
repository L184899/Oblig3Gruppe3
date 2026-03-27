package dao;

import entity.Avdeling;
import jakarta.persistence.*;
import util.JPAUtil;

public class AvdelingDAO {

    public Avdeling finn(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        Avdeling a = em.find(Avdeling.class, id);
        em.close();
        return a;
    }
}
