package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;





@Entity
public class Campany implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int campanyId;
	private String activitySector;

	private String name;
	private String Image ;
	
	 private String location;
     
	   private double lng ;
	   private double lat ;
	   
	   
	   
	
	
	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@OneToMany(mappedBy="campany",cascade={CascadeType.ALL})
	List<EtatFinanciere> etatFinanciere ;
	
	@ManyToOne
	//@XmlTransient
	private ProUser ProUser;
	public List<EtatFinanciere> getEtatFinanciere() {
		return etatFinanciere;
	}

	public void setEtatFinanciere(List<EtatFinanciere> etatFinanciere) {
		this.etatFinanciere = etatFinanciere;
	}

	private String phone;
	
	
	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public EtatEntreprise getEtat() {
		return etat;
	}

	public void setEtat(EtatEntreprise etat) {
		this.etat = etat;
	}

	private String addresse;
	@Enumerated(EnumType.STRING)
	private EtatEntreprise etat;
	@OneToMany(mappedBy="Campany")
	@XmlTransient
	private List<SimpleUser> simpleUsers;
	
	
	

	
	public List<SimpleUser> getSimpleUsers() {
		return simpleUsers;
	}

	public void setSimpleUsers(List<SimpleUser> simpleUsers) {
		this.simpleUsers = simpleUsers;
	}

	public ProUser getProUser() {
		return ProUser;
	}

	public void setProUser(ProUser proUser) {
		ProUser = proUser;
	}

	public Campany() {
	}
	
	public Campany(String name , String activitySector) {
		
super();
       this.name = name;
		this.activitySector = activitySector;
		
	}
	
	public Campany(String name , String activitySector, int idProUser) {
		
		super();
		
		ProUser p = new ProUser(); 
		p.setPersonId(idProUser);
		       this.name = name;
				this.activitySector = activitySector;
				this.ProUser=p;
				
			}
	
	public int getCampanyId() {
		return this.campanyId;
	}

	public void setCampanyId(int campanyId) {
		this.campanyId = campanyId;
	}

	public String getActivitySector() {
		return this.activitySector;
	}

	public void setActivitySector(String activitySector) {
		this.activitySector = activitySector;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		 this.name=name;
	}
	

	
	
	
	
	
}
