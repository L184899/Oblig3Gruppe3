package dao;

import entity.Ansatt;
import entity.Avdeling;
import jakarta.persistence.EntityManager;
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

    public String finnSjef(int avdelingId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            Avdeling a = em.find(Avdeling.class, avdelingId);
            if (a == null || a.getSjef() == null) {
                return null;
            }
            return a.getSjef().getFornavn() + " " + a.getSjef().getEtternavn();
        } finally {
            em.close();
        }
    }

    public List<Ansatt> finnAnsatteIAvdeling(int avdelingId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery(
                            "SELECT a FROM Ansatt a WHERE a.avdeling.avdeling_id = :avdelingId",
                            Ansatt.class)
                    .setParameter("avdelingId", avdelingId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}