package main;

import entity.Ansatt;
import jakarta.persistence.EntityManager;
import util.JPAUtil;

public class Main {
    public static void main(String[] args) {
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();

            Ansatt ansatt = em.find(Ansatt.class, 1); //l

            if (ansatt != null) {
                System.out.println(ansatt);
            } else {
                System.out.println("Fant ingen ansatt med id 1.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            JPAUtil.close();
        }
    }
}