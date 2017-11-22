package services;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.Query;

import persistence.SimpleUser;



@Local
public interface SimpleUserServiceLocal {

	public List<SimpleUser> getAllSimpleUsers();
	public SimpleUser findSimpleUserById(int id);
	public String affecterSimpleUserToEntreprise(int SimpleUserId, int entrepriseId) ;
	
  public List<SimpleUser> getSimpleUserDisponible();
  public List<SimpleUser> rechercheAvancéeWidhDispo(String key) ;
  
  public List<SimpleUser> rechercheAvancéeWidhNotDispo(String key) ;
  
  public List<SimpleUser> getSimpleUserByEntreprise(int idEntreprise);
  
	public String deaffecterSimpleUseFromEntreprise(int simpleUserId);
}
