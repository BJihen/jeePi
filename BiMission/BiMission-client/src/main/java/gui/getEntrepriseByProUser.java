package gui;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import persistence.Campany;
import persistence.ProUser;
import persistence.SimpleUser;
import services.EntrepriseServiceRemote;
import services.ProUserServiceRemote;

public class getEntrepriseByProUser {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		System.out.println("frr");
		ProUserServiceRemote proUserServiceRemote = (ProUserServiceRemote) context
				.lookup("BiMission-ear/BiMission-ejb/ProUserService!services.ProUserServiceRemote");
		
		
		
		ProUser simpleUser = proUserServiceRemote.findProUserById(1);
		
		List<Campany> listCampany = proUserServiceRemote.getAllEntrepriseByProUser(simpleUser);
		for (Campany u : listCampany) {
			System.out.println(u.getName());
		}
		
	}

}
