package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.People;
import services.EntrepriseServiceRemote;
import services.PeopleServiceRemote;

public class AuthentificationTest {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		System.out.println("frr");
		PeopleServiceRemote peopleServiceRemote = (PeopleServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/PeopleService!services.PeopleServiceRemote");
		
		
		People u = peopleServiceRemote.authentification("jihen", "0000");
		System.out.println(u.getFirstnamePerson());
		
		

	}

}
