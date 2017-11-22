package services;

import java.util.List;

import javax.ejb.Local;

import persistence.Campany;
import persistence.People;
import persistence.ProUser;





@Local
public interface ProUserServiceLocal {
	public List<Campany> getAllEntrepriseByProUser(ProUser user);
	
	public ProUser findProUserById(int id);
}
