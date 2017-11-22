package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Campany;
import persistence.People;
import persistence.ProUser;
import persistence.SimpleUser;

/**
 * Session Bean implementation class ProUserService
 */
@Stateless
@LocalBean
public class ProUserService implements ProUserServiceRemote, ProUserServiceLocal {
	@PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public ProUserService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Campany> getAllEntrepriseByProUser(ProUser user) {
	Query req= em.createQuery("select e from Campany e where e.ProUser = :user");
		
		req.setParameter("user", user);
		return req.getResultList();
	}

	@Override
	public ProUser findProUserById(int id) {
		return em.find(ProUser.class, id);
	}


}
