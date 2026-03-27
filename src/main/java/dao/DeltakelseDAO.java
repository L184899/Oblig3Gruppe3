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
}
