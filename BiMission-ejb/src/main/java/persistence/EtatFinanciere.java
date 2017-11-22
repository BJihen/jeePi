package persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class EtatFinanciere implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double CA;
	@Column(nullable = true)
	private double salaireNett;
	@Column(nullable = true)
	private double Totalfrais ;
	@Column(nullable = true)
	private double fraisSalarier ;
	private double coutLoyer ;
	private double coutElectricité;
	private double coutSanitaire ;
	private double objectif ;

	public EtatFinanciere(){}
	
	
	
	public double getObjectif() {
		return objectif;
	}



	public void setObjectif(double objectif) {
		this.objectif = objectif;
	}



	public EtatFinanciere(double cA, double fraisSalarier, double coutLoyer, double coutElectricité,
			double coutSanitaire, int id) {
		super();
		
		Campany c = new Campany();
		c.setCampanyId(id);
		CA = cA;
		this.fraisSalarier = fraisSalarier;
		this.coutLoyer = coutLoyer;
		this.coutElectricité = coutElectricité;
		this.coutSanitaire = coutSanitaire;
 this.campany=c;
	}
/*
 * 
 * 
 * public Campany(String name , String activitySector, int idProUser) {
		
		super();
		
		ProUser p = new ProUser(); 
		p.setPersonId(idProUser);
		       this.name = name;
				this.activitySector = activitySector;
				this.ProUser=p;
				
			}
 * 
 * 
 * */
	private Date date;
	
	@ManyToOne
	private Campany campany;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCA() {
		return CA;
	}

	public void setCA(double cA) {
		CA = cA;
	}

	public double getSalaireNett() {
		return salaireNett;
	}

	public void setSalaireNett(double salaireNett) {
		this.salaireNett = salaireNett;
	}

	public double getTotalfrais() {
		return Totalfrais;
	}

	public void setTotalfrais(double totalfrais) {
		Totalfrais = totalfrais;
	}

	public double getFraisSalarier() {
		return fraisSalarier;
	}

	public void setFraisSalarier(double fraisSalarier) {
		this.fraisSalarier = fraisSalarier;
	}

	public double getCoutLoyer() {
		return coutLoyer;
	}

	public void setCoutLoyer(double coutLoyer) {
		this.coutLoyer = coutLoyer;
	}

	public double getCoutElectricité() {
		return coutElectricité;
	}

	public void setCoutElectricité(double coutElectricité) {
		this.coutElectricité = coutElectricité;
	}

	public double getCoutSanitaire() {
		return coutSanitaire;
	}

	public void setCoutSanitaire(double coutSanitaire) {
		this.coutSanitaire = coutSanitaire;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Campany getCampany() {
		return campany;
	}

	public void setCampany(Campany campany) {
		this.campany = campany;
	}
	
	
	
	
	
	
}
