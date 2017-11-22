package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.People;

/**
 * Session Bean implementation class People
 */
@Stateless
@LocalBean
public class PeopleService implements PeopleServiceRemote, PeopleServiceLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
    EntityManager em;
	
    public PeopleService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public People authentification(String username, String pwd) {
		String jpql = "SELECT z FROM People z WHERE z.login=:username and z.password=:pwd";
		Query query = em.createQuery(jpql);
		query.setParameter("username", username);
		query.setParameter("pwd", pwd);
		People user= null;
		try {
		  user=(People)query.getSingleResult();

	
} catch (Exception e) {
	// TODO: handle exception
}

		return user;
	}

}
