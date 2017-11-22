package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Campany;
import persistence.EtatSimpleUser;
import persistence.SimpleUser;



/**
 * Session Bean implementation class SimpleUserService
 */
@Stateless
@LocalBean
public class SimpleUserService implements SimpleUserServiceRemote, SimpleUserServiceLocal {
	@PersistenceContext
	EntityManager em;
    /**
     * Default constructor. 
     */
    public SimpleUserService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<SimpleUser> getAllSimpleUsers() {
		Query req = em.createQuery("select r from SimpleUser r");
		List<SimpleUser> recruiters = req.getResultList();
		return recruiters;
	}

	
	
	@Override
	public SimpleUser findSimpleUserById(int id) {
		return em.find(SimpleUser.class, id);
}
	
	@Override
	public String affecterSimpleUserToEntreprise(int SimpleUserId, int entrepriseId) {
	 Campany entreprise1 = em.find(Campany.class, entrepriseId);
	     SimpleUser recruiter1 = em.find(SimpleUser.class, SimpleUserId);
	     if(recruiter1.getEtatSimpleUser()== EtatSimpleUser.disponible)
	     { 
	    	 recruiter1.setCampany(entreprise1);
    	 recruiter1.setEtatSimpleUser(EtatSimpleUser.notDisponible);
	    	 return "Affected";
	     
	     }
	     else
	     {
	    	 return "Is Affected";
	     }
	}

	@Override
	public List<SimpleUser> getSimpleUserDisponible() {
		Query req=em.createQuery("select r from SimpleUser r where r.etatSimpleUser = 'disponible'");
		return req.getResultList();
	}

	@Override
	public List<SimpleUser> rechercheAvancéeWidhDispo(String key) {
		Query req=em.createQuery("select e from SimpleUser e where (e.firstnamePerson Like CONCAT('%',:key,'%') or e.specialite Like CONCAT('%',:key,'%') or e.lastnamePerson Like CONCAT('%',:key,'%')) and ( e.etatSimpleUser = 'disponible')");
		req.setParameter("key", key);
		return req.getResultList();
	}

	@Override
	public List<SimpleUser> getSimpleUserByEntreprise(int idEntreprise) {
		Query req= em.createQuery("select r from SimpleUser r where r.Campany.campanyId= :idEntreprise");
		req.setParameter("idEntreprise", idEntreprise);
		return req.getResultList();
	}

	@Override
	public String deaffecterSimpleUseFromEntreprise(int simpleUserId) {
		SimpleUser recruiter1 = em.find(SimpleUser.class, simpleUserId);
	     if(recruiter1.getEtatSimpleUser()==EtatSimpleUser.notDisponible)
	     { 
	    	 recruiter1.setCampany(null);
	    	 recruiter1.setEtatSimpleUser(EtatSimpleUser.disponible);
	    	 return "Deaffected";
	     
	     }
	     else
	     {
	    	 return "Is Disponible";
	     }
	}

	@Override
	public List<SimpleUser> rechercheAvancéeWidhNotDispo(String key) {
		Query req=em.createQuery("select e from SimpleUser e where (e.firstnamePerson Like CONCAT('%',:key,'%') or e.specialite Like CONCAT('%',:key,'%') or e.lastnamePerson Like CONCAT('%',:key,'%')) and ( e.etatSimpleUser = 'notDisponible')");
		req.setParameter("key", key);
		return req.getResultList();
	}

	

}
