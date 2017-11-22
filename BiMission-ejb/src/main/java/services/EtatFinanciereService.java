package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import persistence.Campany;
import persistence.EtatFinanciere;

/**
 * Session Bean implementation class EtatFinanciereService
 */
@Stateless
@LocalBean
public class EtatFinanciereService implements EtatFinanciereServiceRemote, EtatFinanciereServiceLocal {

	@PersistenceContext
    EntityManager em;
	
	
    public EtatFinanciereService() {
        
    }


	@Override
	public void addEtatFinanciere(EtatFinanciere etatFinancier) {
		em.persist(etatFinancier);
		em.clear();
	}





	@Override
	public List<EtatFinanciere> findAllEtat() {
		String jpql = "SELECT u FROM EtatFinanciere u";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}


	@Override
	public List<EtatFinanciere> findEtatByCampany(Campany c) {
	Query req= em.createQuery("select e from EtatFinanciere e where e.campany = :user");
		
		req.setParameter("user", c);
		return req.getResultList();
	}


	@Override
	public void updateEtatFinanciere(EtatFinanciere etat) {
		em.merge(etat);
		
	}


	@Override
	public void DeletEtatById(int id) {
		em.remove(findEtatById(id));
		
	}


	@Override
	public EtatFinanciere findEtatById(int id) {
		
		return em.find(EtatFinanciere.class, id);
	}

}
