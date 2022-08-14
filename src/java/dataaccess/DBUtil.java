package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("Final_ProjectPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
