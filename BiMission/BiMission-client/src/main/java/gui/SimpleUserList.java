package gui;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.SimpleUser;

import services.SimpleUserServiceRemote;

public class SimpleUserList {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
	System.out.print("aaaaaaaaaaaaaaaaa");
		SimpleUserServiceRemote simpleUserServiceRemote = (SimpleUserServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/SimpleUserService!services.SimpleUserServiceRemote");
		
		
		/*System.out.println("aaaaaaaaaaaaaaaaa");
		
		List<SimpleUser> listSimpleUser = simpleUserServiceRemote.getAllSimpleUsers();
		for (SimpleUser u : listSimpleUser) {
			System.out.println(u.getFirstnamePerson());
		}*/

		
		List<SimpleUser> listSimpleUser = simpleUserServiceRemote.rechercheAvancéeWidhDispo("");
		for (SimpleUser u : listSimpleUser) {
			System.out.println(u.getFirstnamePerson());
		}
		
	
		
	}

}
