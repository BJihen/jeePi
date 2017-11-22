package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import services.EntrepriseServiceRemote;

public class AddEntreprise {

	public static void main(String[] args) throws NamingException {

		Context context = new InitialContext();
		System.out.println("frr");
		EntrepriseServiceRemote entrepriseServiceRemote = (EntrepriseServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/EntrepriseService!services.EntrepriseServiceRemote");
		
		System.out.println("frr");
		
		Campany c =new Campany("Informatique","bbbbbb",1);
		Campany c1 =new Campany("Industri","aaaaaaaaaaa",1);
		
		entrepriseServiceRemote.addEntreprise(c);
		entrepriseServiceRemote.addEntreprise(c1);

	}

}
