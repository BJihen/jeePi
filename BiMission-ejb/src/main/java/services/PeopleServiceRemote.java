package services;

import javax.ejb.Remote;

import persistence.People;

@Remote
public interface PeopleServiceRemote {
	public People authentification(String username, String pwd) ;
}
