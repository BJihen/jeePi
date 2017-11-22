package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import persistence.SimpleUser;
import services.EntrepriseServiceRemote;
import services.SimpleUserServiceRemote;

public class AffectationSimpleUserTest {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		
		SimpleUserServiceRemote simpleUserServiceRemote = (SimpleUserServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/SimpleUserService!services.SimpleUserServiceRemote");
		
		
	EntrepriseServiceRemote entrepriseServiceRemote = (EntrepriseServiceRemote) context
			.lookup("BiMission-ear/BiMission-ejb/EntrepriseService!services.EntrepriseServiceRemote");

	
	
	SimpleUser simpleUser = simpleUserServiceRemote.findSimpleUserById(2);
	Campany campany = entrepriseServiceRemote.findCampanyById(2);
	
		simpleUserServiceRemote.affecterSimpleUserToEntreprise(simpleUser.getPersonId(), campany.getCampanyId());
		
		
	}

}
