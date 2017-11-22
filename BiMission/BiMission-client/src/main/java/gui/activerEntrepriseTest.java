package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import services.EntrepriseServiceRemote;

public class activerEntrepriseTest {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		System.out.println("frr");
		EntrepriseServiceRemote entrepriseServiceRemote = (EntrepriseServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/EntrepriseService!services.EntrepriseServiceRemote");
		
		
		
		Campany campany = entrepriseServiceRemote.findCampanyById(2);
		
		entrepriseServiceRemote.desactiverEntreprise(campany);
		
		
	}

}
