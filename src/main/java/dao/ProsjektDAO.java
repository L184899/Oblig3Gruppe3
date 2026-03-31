package dao;

import entity.Prosjekt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import util.JPAUtil;

import java.util.List;

public class ProsjektDAO {

    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Prosjekt p = em.find(Prosjekt.class, id);
        em.close();
        return p;
    }

    public List<Prosjekt> finnAlle() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        List<Prosjekt> liste =
                em.createQuery("SELECT p FROM Prosjekt p", Prosjekt.class)
                        .getResultList();

        em.close();
        return liste;
    }

    public Long tellDeltakelser(int prosjektId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        Long count = em.createQuery(
                        "SELECT COUNT(d) FROM Deltakelse d WHERE d.prosjekt.prosjekt_id = :id", Long.class)
                .setParameter("id", prosjektId)
                .getSingleResult();

        em.close();
        return count;
    }

    public boolean leggTilProsjekt(String navn, String beskrivelse) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prosjekt prosjekt = new Prosjekt();
            prosjekt.setNavn(navn);
            prosjekt.setBeskrivelse(beskrivelse);

            em.persist(prosjekt);

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

    public boolean oppdaterProsjekt(int prosjektId, String nyttNavn, String nyBeskrivelse) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prosjekt prosjekt = em.find(Prosjekt.class, prosjektId);
            if (prosjekt == null) {
                tx.rollback();
                return false;
            }

            if (nyttNavn != null && !nyttNavn.isBlank()) {
                prosjekt.setNavn(nyttNavn);
            }

            if (nyBeskrivelse != null && !nyBeskrivelse.isBlank()) {
                prosjekt.setBeskrivelse(nyBeskrivelse);
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
}