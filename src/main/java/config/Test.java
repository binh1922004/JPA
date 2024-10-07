package config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Test {
        public static void main(String[] args) {
                EntityManager enma = JPAConfig.getEntityManager();
                EntityTransaction trans = enma.getTransaction();
                try {
                        trans.begin();
                        trans.commit();
                } catch (Exception e) {
                        e.printStackTrace();
                        trans.rollback();
                        throw e;


                }
                finally {
                        enma.close();
                }
        }
}
