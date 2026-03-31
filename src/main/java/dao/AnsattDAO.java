package dao;

import entity.Ansatt;
import entity.Avdeling;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import util.JPAUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AnsattDAO {

    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Ansatt a = em.find(Ansatt.class, id);
        em.close();
        return a;
    }

    public Ansatt finnAnsattMedBrukernavn(String brukernavn) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery(
                            "SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn",
                            Ansatt.class)
                    .setParameter("brukernavn", brukernavn)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Ansatt> finnAlle() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Ansatt> liste =
                em.createQuery("SELECT a FROM Ansatt a", Ansatt.class)
                        .getResultList();

        em.close();
        return liste;
    }

    public boolean oppdaterStillingOgLonn(int id, String nyStilling, BigDecimal nyLonn) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt ansatt = em.find(Ansatt.class, id);
            if (ansatt == null) {
                tx.rollback();
                return false;
            }

            if (nyStilling != null && !nyStilling.isBlank()) {
                ansatt.setStilling(nyStilling);
            }

            if (nyLonn != null) {
                ansatt.setMaanedslonn(nyLonn);
            }

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

    public boolean leggTilAnsatt(String brukernavn, String fornavn, String etternavn,
                                 LocalDate ansettelsesdato, String stilling,
                                 BigDecimal maanedslonn, int avdelingId) {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Avdeling avdeling = em.find(Avdeling.class, avdelingId);
            if (avdeling == null) {
                tx.rollback();
                return false;
            }

            Ansatt ansatt = new Ansatt();
            ansatt.setBrukernavn(brukernavn);
            ansatt.setFornavn(fornavn);
            ansatt.setEtternavn(etternavn);
            ansatt.setAnsettelsesdato(ansettelsesdato);
            ansatt.setStilling(stilling);
            ansatt.setMaanedslonn(maanedslonn);
            ansatt.setAvdeling(avdeling);

            em.persist(ansatt);
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

    public boolean byttAvdeling(int ansattId, int nyAvdelingId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            if (ansatt == null) {
                tx.rollback();
                return false;
            }

            Avdeling nyAvdeling = em.find(Avdeling.class, nyAvdelingId);
            if (nyAvdeling == null) {
                tx.rollback();
                return false;
            }

            List<Avdeling> avdelingerDerAnsattErSjef = em.createQuery(
                            "SELECT a FROM Avdeling a WHERE a.sjef.ansatt_id = :ansattId",
                            Avdeling.class)
                    .setParameter("ansattId", ansattId)
                    .getResultList();

            if (!avdelingerDerAnsattErSjef.isEmpty()) {
                tx.rollback();
                return false;
            }

            ansatt.setAvdeling(nyAvdeling);

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