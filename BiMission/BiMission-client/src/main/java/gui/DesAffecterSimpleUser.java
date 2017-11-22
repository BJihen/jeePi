package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.SimpleUser;
import services.SimpleUserServiceRemote;

public class DesAffecterSimpleUser {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

			SimpleUserServiceRemote simpleUserServiceRemote = (SimpleUserServiceRemote) context
					.lookup("BiMission-ear/BiMission-ejb/SimpleUserService!services.SimpleUserServiceRemote");
			
			
			SimpleUser simpleUser = simpleUserServiceRemote.findSimpleUserById(14);
			simpleUserServiceRemote.deaffecterSimpleUseFromEntreprise(simpleUser.getPersonId());

	}

}
