package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
        public static EntityManager getEntityManager(){
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
                return factory.createEntityManager();
        }
}
