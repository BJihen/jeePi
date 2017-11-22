package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;




@Entity
@DiscriminatorValue(value = "ProUser")
public class ProUser extends People implements Serializable {
	
	@OneToMany(mappedBy = "ProUser")
	private List<Campany> entreprises;
	
	

	public List<Campany> getEntreprises() {
		return entreprises;
	}

	public void setEntreprises(List<Campany> entreprises) {
		this.entreprises = entreprises;
	}


}
