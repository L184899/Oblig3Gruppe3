package dao;

import entity.Ansatt;
import entity.Avdeling;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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

    public boolean leggTilAvdeling(String navn, int sjefAnsattId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt sjef = em.find(Ansatt.class, sjefAnsattId);
            if (sjef == null) {
                tx.rollback();
                return false;
            }

            List<Avdeling> avdelingerDerAnsattErSjef = em.createQuery(
                            "SELECT a FROM Avdeling a WHERE a.sjef.ansatt_id = :ansattId",
                            Avdeling.class)
                    .setParameter("ansattId", sjefAnsattId)
                    .getResultList();

            if (!avdelingerDerAnsattErSjef.isEmpty()) {
                tx.rollback();
                return false;
            }

            Avdeling nyAvdeling = new Avdeling();
            nyAvdeling.setNavn(navn);

            em.persist(nyAvdeling);
            em.flush();

            nyAvdeling.setSjef(sjef);
            sjef.setAvdeling(nyAvdeling);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }
}