package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;




@Entity
@DiscriminatorValue(value = "SimpleUser")
public class SimpleUser extends People implements Serializable {

	
	@XmlTransient
	@ManyToOne
	private Campany Campany;
	
	
	@Enumerated(EnumType.STRING)
	private EtatSimpleUser etatSimpleUser;
	
	
	private String specialite ;
	
	
	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public SimpleUser() {
		super();
	}

	private static final long serialVersionUID = 1L;

	public Campany getCampany() {
		return Campany;
	}

	public void setCampany(Campany campany) {
		Campany = campany;
	}

	public EtatSimpleUser getEtatSimpleUser() {
		return etatSimpleUser;
	}

	public void setEtatSimpleUser(EtatSimpleUser etatSimpleUser) {
		this.etatSimpleUser = etatSimpleUser;
	}
	
	
	
	
	
	
}
