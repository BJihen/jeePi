package ctr;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.primefaces.model.map.DefaultMapModel;

import com.sun.mail.smtp.SMTPTransport;

import persistence.Campany;
import persistence.ProUser;
import persistence.SimpleUser;
import services.EntrepriseServiceLocal;
import services.SimpleUserServiceLocal;
import services.SimpleUserServiceRemote;

@ManagedBean
@ViewScoped
public class SimpleUserManagementBean {

	@EJB
	private SimpleUserServiceLocal simpleUserServiceLocal;

	@EJB
	private EntrepriseServiceLocal entrepriseServiceLocal;

	private List<SimpleUser> lstUser = new ArrayList<>();
	private SimpleUser simpleUser = new SimpleUser();

	private List<Campany> lstCampany;
	private String text = "";

	private int id;

	// lstJurry= uslLocal.findUsersByRole(RoleUser.JURRY);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@PostConstruct
	public void init() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ProUser u = (ProUser) session.getAttribute("people");
		lstCampany = new ArrayList<>();
		lstCampany = entrepriseServiceLocal.getAllEntrepriseByProUser(u);
	}

	public EntrepriseServiceLocal getEntrepriseServiceLocal() {
		return entrepriseServiceLocal;
	}

	public void setEntrepriseServiceLocal(EntrepriseServiceLocal entrepriseServiceLocal) {
		this.entrepriseServiceLocal = entrepriseServiceLocal;
	}

	public List<Campany> getLstCampany() {
		return lstCampany;
	}

	public void setLstCampany(List<Campany> lstCampany) {
		this.lstCampany = lstCampany;
	}

	public List<SimpleUser> getLstUser() {
		return lstUser;
	}

	public void setLstUser(List<SimpleUser> lstUser) {
		this.lstUser = lstUser;
	}

	public SimpleUser getSimpleUser() {
		return simpleUser;
	}

	public void setSimpleUser(SimpleUser simpleUser) {
		this.simpleUser = simpleUser;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<SimpleUser> doListSimpleUser() {

		return simpleUserServiceLocal.getSimpleUserDisponible();

	}

	public List<SimpleUser> doListSimpleUserByCampany() {

		return simpleUserServiceLocal.getSimpleUserByEntreprise(3);

	}

	private void sendMail () throws AddressException, MessagingException{
		
		 Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.gmail.com");
	        props.put("mail.smtps.auth","true");
	        Session session = Session.getInstance(props, null);
	        Message msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress("jihen.bach@esprit.tn"));;
	        msg.setRecipients(Message.RecipientType.TO,
	        InternetAddress.parse("gprodbou6@gmail.com", false));
	        msg.setSubject("jiji "+System.currentTimeMillis());
	        msg.setText("Bonjour Mr, /t votre affectation a été effectuer avec succés ");
	        msg.setHeader("X-Mailer", "Tov Are's program");
	        msg.setSentDate(new Date());
	        SMTPTransport t =
	            (SMTPTransport)session.getTransport("smtps");
	        t.connect("smtp.gmail.com", "jihen.bach@esprit.tn", "janouna94");
	        t.sendMessage(msg, msg.getAllRecipients());
	        System.out.println("Response: " + t.getLastServerResponse());
	        t.close();
	} 
	
	
	
	public void doAffecterSimpleUToEntreprise(int idSimplUser) throws AddressException, MessagingException {
		sendMail();
		simpleUserServiceLocal.affecterSimpleUserToEntreprise(idSimplUser, id);
		
	}

	public void doDesAffecterSimpleUToEntreprise(String idSimplUser) {

		int id = Integer.parseInt(idSimplUser);

		simpleUserServiceLocal.deaffecterSimpleUseFromEntreprise(id);
	}

	public List<SimpleUser> doSearchSimpleUser(String x) {
		// if(x.equals(null)){
		// return simpleUserServiceLocal.getSimpleUserDisponible();
		// }else
		return simpleUserServiceLocal.rechercheAvancéeWidhDispo(x);
	}

	public List<SimpleUser> doSearchSimpleUserNotDispo(String x) {

		return simpleUserServiceLocal.rechercheAvancéeWidhNotDispo(x);
	}

	public SimpleUserServiceLocal getSimpleUserServiceLocal() {
		return simpleUserServiceLocal;
	}

	public void setSimpleUserServiceLocal(SimpleUserServiceLocal simpleUserServiceLocal) {
		this.simpleUserServiceLocal = simpleUserServiceLocal;
	}

}
