package services;

import javax.ejb.Local;

import persistence.People;

@Local
public interface PeopleServiceLocal {
	public People authentification(String username, String pwd) ;
}
