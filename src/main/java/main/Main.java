package main;

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

            Object result = em.createNativeQuery("select 1").getSingleResult();
            System.out.println("Tilkobling OK! Database svarte: " + result);

        } catch (Exception e) {
            System.out.println("Klarte ikke å koble til databasen.");
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }
    }
}