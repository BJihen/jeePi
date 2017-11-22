package services;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;

import persistence.SimpleUser;




@Remote
public interface SimpleUserServiceRemote {
	
	public List<SimpleUser> getAllSimpleUsers();
	public SimpleUser findSimpleUserById(int id);
	public String affecterSimpleUserToEntreprise(int SimpleUserId, int entrepriseId) ;
	
  public List<SimpleUser> getSimpleUserDisponible();
  public List<SimpleUser> rechercheAvancéeWidhDispo(String key) ;
  public List<SimpleUser> getSimpleUserByEntreprise(int idEntreprise);
  public String deaffecterSimpleUseFromEntreprise(int simpleUserId);
  
  public List<SimpleUser> rechercheAvancéeWidhNotDispo(String key) ;

}


