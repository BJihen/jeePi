package gui;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import persistence.EtatFinanciere;

import services.EtatFinanciereServiceRemote;

public class AddEtatFinanciereTest {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		System.out.println("frr");
		EtatFinanciereServiceRemote etatServiceRemote = (EtatFinanciereServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/EtatFinanciereService!services.EtatFinanciereServiceRemote");
		//Campany campany = new Campany();
		
		EtatFinanciere e = new EtatFinanciere(2.2,2.2,2.2,2.2,2.2,1);
		etatServiceRemote.addEtatFinanciere(e);
		

	}

}
