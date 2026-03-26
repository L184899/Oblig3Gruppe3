package main;

import entity.Ansatt;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import util.Passwords;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory(
                    "oblig3PU",
                    Map.of("jakarta.persistence.jdbc.password", Passwords.AZURE_PASSWORD)
            );

            em = emf.createEntityManager();

            Ansatt ansatt = em.find(Ansatt.class, 1);

            if (ansatt != null) {
                System.out.println("Fant ansatt:");
                System.out.println(ansatt);
            } else {
                System.out.println("Fant ingen ansatt med id 1.");
            }

        } catch (Exception e) {
            System.out.println("Klarte ikke å hente ansatt.");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}