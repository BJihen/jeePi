package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Campany;
import persistence.ProUser;



@Remote
public interface EntrepriseServiceRemote {
	
	public void addEntreprise(Campany entreprise);
	/*public void desactiverEntreprise(Campany entreprise);
	public List<Campany> getEntrepriseByName(String name);
	public void updateEntreprise(Campany entreprise);
	public List<Campany> getAllEntreprise(People user);
	public List<Campany> getAllEntrepriseActivator(People user);
	public List<Campany> getAllEntrepriseDeactivator(People user);*/
	public void DeletCampanyById(int id);
	List<Campany> findAllCampanies();
	public void activerEntreprise(Campany e) ;
	public Campany findCampanyById(int id);
	
	public void updateEntreprise(Campany entreprise);
	
	public void deleteEntreprise(Campany entreprise);
	public void desactiverEntreprise(Campany e);
	public List<Campany> getAllEntrepriseByProUser(ProUser user);
}
