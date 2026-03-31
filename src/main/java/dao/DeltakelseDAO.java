package dao;

import entity.Ansatt;
import entity.Deltakelse;
import entity.Prosjekt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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

    public boolean registrerDeltakelse(int ansattId, int prosjektId, String rolle, int timer) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            if (ansatt == null) {
                tx.rollback();
                return false;
            }

            Prosjekt prosjekt = em.find(Prosjekt.class, prosjektId);
            if (prosjekt == null) {
                tx.rollback();
                return false;
            }

            Deltakelse deltakelse = new Deltakelse();
            deltakelse.setAnsatt(ansatt);
            deltakelse.setProsjekt(prosjekt);
            deltakelse.setRolle(rolle);
            deltakelse.setTimer(timer);

            em.persist(deltakelse);

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

    public boolean forTimer(int ansattId, int prosjektId, int flereTimer) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            List<Deltakelse> liste = em.createQuery(
                            "SELECT d FROM Deltakelse d WHERE d.ansatt.ansatt_id = :ansattId AND d.prosjekt.prosjekt_id = :prosjektId",
                            Deltakelse.class)
                    .setParameter("ansattId", ansattId)
                    .setParameter("prosjektId", prosjektId)
                    .getResultList();

            if (liste.isEmpty()) {
                tx.rollback();
                return false;
            }

            Deltakelse deltakelse = liste.get(0);
            deltakelse.setTimer(deltakelse.getTimer() + flereTimer);

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

    public List<Deltakelse> finnDeltakelserForProsjekt(int prosjektId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        try {
            return em.createQuery(
                            "SELECT d FROM Deltakelse d WHERE d.prosjekt.prosjekt_id = :prosjektId",
                            Deltakelse.class)
                    .setParameter("prosjektId", prosjektId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}