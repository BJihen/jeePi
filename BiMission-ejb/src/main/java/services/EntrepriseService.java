package services;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Campany;
import persistence.EtatEntreprise;
import persistence.ProUser;





/**
 * Session Bean implementation class EntrepriseService
 */
@Stateless
public class EntrepriseService implements EntrepriseServiceRemote, EntrepriseServiceLocal {
	@PersistenceContext
    EntityManager em;
    /**
     * Default constructor. 
     */
    public EntrepriseService() {
        // TODO Auto-generated constructor stub
    }
    
    
	@Override
	public void addEntreprise( Campany entreprise) {
		em.persist(entreprise);
		//em.clear();
		
	}



	@Override
	public List<Campany> findAllCampanies() {
		String jpql = "SELECT u FROM Campany u";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}


	@Override
	public Campany findCampanyById(int id) {
		// TODO Auto-generated method stub
		return em.find(Campany.class, id);
	}


	@Override
	public void updateEntreprise(Campany entreprise) {
		em.merge(entreprise);
		
	}


	@Override
	public void deleteEntreprise(Campany entreprise) {
		em.remove(entreprise);
		
	}


	@Override
	public void DeletCampanyById(int id) {
		
		em.remove(findCampanyById(id));
		
	}


	@Override
	public void activerEntreprise(Campany e) {
		Campany entreprise=em.find(Campany.class,e.getCampanyId());
		if (entreprise.getEtat().equals(EtatEntreprise.Desactiver)) {
			
			entreprise.setEtat(EtatEntreprise.activer);
		
			System.out.println("Votre entreprise est Activée");
		}
		
	}


	@Override
	public void desactiverEntreprise(Campany e) {
		
		Campany entreprise=em.find(Campany.class,e.getCampanyId());
		
		if (entreprise.getEtat().equals(EtatEntreprise.activer)) {
			entreprise.setEtat(EtatEntreprise.Desactiver);
			
			System.out.println("Votre entreprise est Désactivée");
		}
		else{
			System.out.println("Erreur");
		}
		
	}


	@Override
	public List<Campany> getAllEntrepriseByProUser(ProUser user) {
	Query req= em.createQuery("select e from Campany e where e.ProUser = :user");
		
		req.setParameter("user", user);
		return req.getResultList();
	}

	
	
	
	

	


}
