package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Campany;
import persistence.EtatFinanciere;

@Local
public interface EtatFinanciereServiceLocal {

	public void addEtatFinanciere(EtatFinanciere etatFinancier);

	public List<EtatFinanciere> findAllEtat();
	
	public List<EtatFinanciere> findEtatByCampany(Campany c);
	public void updateEtatFinanciere(EtatFinanciere etat);
	public void DeletEtatById(int id);
	
	public EtatFinanciere findEtatById(int id);
}
