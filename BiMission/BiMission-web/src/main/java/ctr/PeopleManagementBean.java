package ctr;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpSession;

import persistence.People;
import persistence.ProUser;
import persistence.SimpleUser;
import services.PeopleService;

@ManagedBean
@SessionScoped
public class PeopleManagementBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private PeopleService peopleService ;
	private People user = new People();
	private boolean isLogged ;
	
	private boolean loggedInAsProUser=false ;
	private boolean  loggedInAsSimpleUser=false ; 
	
	
	
	public boolean isLoggedInAsProUser() {
		return loggedInAsProUser;
	}
	public void setLoggedInAsProUser(boolean loggedInAsProUser) {
		this.loggedInAsProUser = loggedInAsProUser;
	}

	
	public boolean isLoggedInAsSimpleUser() {
		return loggedInAsSimpleUser;
	}
	public void setLoggedInAsSimpleUser(boolean loggedInAsSimpleUser) {
		this.loggedInAsSimpleUser = loggedInAsSimpleUser;
	}
	public boolean isLogged() {
		return isLogged;
	}
	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}
	public PeopleService getPeopleService() {
		return peopleService;
	}
	public void setPeopleService(PeopleService peopleService) {
		this.peopleService = peopleService;
	}
	public People getUser() {
		return user;
	}
	public void setUser(People user) {
		this.user = user;
	}
	
	public String logout() {
		isLogged = false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Authentification/Login?faces-redirect=true";
	}
	
	public String doLogin() {
		String navigateTo = "";
		People userLoggedIn = peopleService.authentification(user.getLogin(), user.getPassword());

		if (userLoggedIn != null) {
			isLogged = true;
			user = userLoggedIn;
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("people", user);
			
			if (userLoggedIn instanceof ProUser) {
				loggedInAsProUser=true;
				navigateTo = "/pages/ProUser/AddCWiframe?faces-redirect=true";
			} else if (userLoggedIn instanceof SimpleUser) {
				loggedInAsSimpleUser=true;
				navigateTo = "/pages/SimpleUser/ListSimpleUserDispo?faces-redirect=true";
			} else {
				
				navigateTo = "/pages/SimpleUser/ListSimpleUserNotDispo?faces-redirect=true";
			
				}
		}
		return navigateTo;
	}
}
