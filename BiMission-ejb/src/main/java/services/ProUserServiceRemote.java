package services;

import java.util.List;

import javax.ejb.Remote;

import persistence.Campany;
import persistence.People;
import persistence.ProUser;

@Remote
public interface ProUserServiceRemote {
	public List<Campany> getAllEntrepriseByProUser(ProUser user);
	public ProUser findProUserById(int id);
}
