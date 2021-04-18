package posjavamavenhibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;
	
	static {
		init();
	}

	private static void init() {	
		try {
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("pos-java-maven-hibernate");
			}			
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
			
	}
	
	public static EntityManager geEntityManager() {
		/*Prove a parte de persistencia*/
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {
		/*Retorna a primary key*/		
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
}
