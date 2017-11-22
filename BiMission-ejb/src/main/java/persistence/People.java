package persistence;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public class People implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personId;
	
	private String firstnamePerson;

	private String lastnamePerson;
	
	private String image ;
	private String  password ;
	
	
	private String login ;
	
	
	
	
	

	public String getLogin() {
		return login;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public People() {
	}



	public int getPersonId() {
		return personId;
	}



	public void setPersonId(int personId) {
		this.personId = personId;
	}



	public String getFirstnamePerson() {
		return firstnamePerson;
	}



	public void setFirstnamePerson(String firstnamePerson) {
		this.firstnamePerson = firstnamePerson;
	}



	public String getLastnamePerson() {
		return lastnamePerson;
	}



	public void setLastnamePerson(String lastnamePerson) {
		this.lastnamePerson = lastnamePerson;
	}


	
	
}
