package gui;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import services.EntrepriseServiceRemote;

public class FindAllEntreprise {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		System.out.println("frr");
		EntrepriseServiceRemote entrepriseServiceRemote = (EntrepriseServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/EntrepriseService!services.EntrepriseServiceRemote");
		
		System.out.println("frr");
		List<Campany> listEntreprise = entrepriseServiceRemote.findAllCampanies();
		for (Campany u : listEntreprise) {
			System.out.println(u.getName());
		}
		
	}

}
