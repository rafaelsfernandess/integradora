package br.com.empresa.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
    
    private static final EntityManagerFactory emf;

    /**
     * Static block for creating EntityManagerFactory. The Persistence class looks for META-INF/persistence.xml in the classpath.
     */
    static {
        emf = Persistence.createEntityManagerFactory("ProjetoExemploPU");
    }

    /**
     * Static method returning EntityManager.
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
}