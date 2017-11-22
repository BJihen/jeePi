package ctr;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import persistence.Campany;
import persistence.ProUser;
import persistence.SimpleUser;
import services.EntrepriseServiceLocal;
import services.ProUserServiceLocal;


@ManagedBean
@ViewScoped
public class ProUserManagementBean {
	@EJB
	private ProUserServiceLocal proUserServiceLocal ;
	
	@EJB
	private EntrepriseServiceLocal entrepriseServiceLocal;
	
	private ProUser proUser = new ProUser();

	public ProUserServiceLocal getProUserServiceLocal() {
		return proUserServiceLocal;
	}

	public void setProUserServiceLocal(ProUserServiceLocal proUserServiceLocal) {
		this.proUserServiceLocal = proUserServiceLocal;
	}

	public ProUser getProUser() {
		return proUser;
	}

	public void setProUser(ProUser proUser) {
		this.proUser = proUser;
	}
	
	
	
	
	public List<Campany> doListCampanyByProUser(){
		
		ProUser proUser = proUserServiceLocal.findProUserById(1);
		
		return proUserServiceLocal.getAllEntrepriseByProUser(proUser);
	
	}
	
	

	public void doActiverEntreprise(Campany e){
		
		entrepriseServiceLocal.activerEntreprise(e);
	}
	
	
public void doDesActiverEntreprise(Campany e){
		
		entrepriseServiceLocal.desactiverEntreprise(e);
	}
	
	
}
