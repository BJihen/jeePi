package gui;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import persistence.EtatFinanciere;
import services.EntrepriseServiceRemote;
import services.EtatFinanciereServiceRemote;

public class FraisTotalTest {

	public static void main(String[] args) throws NamingException {
		
		
		Context context = new InitialContext();
		System.out.println("frr");
		EtatFinanciereServiceRemote etatServiceRemote = (EtatFinanciereServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/EtatFinanciereService!services.EtatFinanciereServiceRemote");

		EntrepriseServiceRemote entrepriseServiceRemote = (EntrepriseServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/EntrepriseService!services.EntrepriseServiceRemote");
		
			double x = 0;
		Campany c = entrepriseServiceRemote.findCampanyById(1);
			
			List<EtatFinanciere>lst = etatServiceRemote.findEtatByCampany(c);
			for (EtatFinanciere etatFinanciere : lst) {
				 x = x+etatFinanciere.getFraisSalarier()+ etatFinanciere.getCoutElectricité()+etatFinanciere.getCoutSanitaire()
				+etatFinanciere.getCoutLoyer();
				 System.out.println(x);
			}
			
			
		
		
		
		
	}

}
