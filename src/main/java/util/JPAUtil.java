package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Map;

public class JPAUtil {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory(
                    "oblig3PU",
                    Map.of("jakarta.persistence.jdbc.password", Passwords.AZURE_PASSWORD)
            );

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}