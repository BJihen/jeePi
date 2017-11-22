package ctr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EnterpriseBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import persistence.Campany;
import persistence.ProUser;
import services.EntrepriseServiceLocal;

@ManagedBean
@SessionScoped
public class CampanyManagmentBean  {
	
	@EJB
	private EntrepriseServiceLocal entrepriseServiceLocal;
	
	
	private Campany entreprise = new Campany() ;
	

	  private double lng ;
	   private double lat ;
	   private String location;
	
	   public static int id ;
	


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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private MapModel emptyModel;

	private UploadedFile file;


	String name ;
    public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public EntrepriseServiceLocal getEntrepriseServiceLocal() {
		return entrepriseServiceLocal;
	}

	public void setEntrepriseServiceLocal(EntrepriseServiceLocal entrepriseServiceLocal) {
		this.entrepriseServiceLocal = entrepriseServiceLocal;
	}


	  @PostConstruct
	    public void init() {
	        emptyModel = new DefaultMapModel();
	    }
	      

	/////////////////////////// Upload Image
	  
	public void handleFileUpload(FileUploadEvent event) {
		name= event.getFile()
				.getFileName();
		FacesMessage message = new FacesMessage("Succesful", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	
		String localPath = "C:" + File.separator + "Users" + File.separator
								+ "Lenovo" + File.separator + "Documents" + File.separator
								+ "4RP-BI" + File.separator + "Semestre1"
								+ File.separator + "JEE" + File.separator + "BiMission"
				                + File.separator + "BiMission-web"
								+ File.separator + "src" + File.separator + "main"
								+ File.separator + "webapp" + File.separator + "resources"
								+ File.separator + "images" + File.separator + name ;

	
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		String filepath = externalContext.getRealPath("") + File.separator
				+ "resources" + File.separator + "images" + File.separator + name ;

		try (

		InputStream input = event.getFile().getInputstream()) {
			OutputStream os = new FileOutputStream(localPath);
			OutputStream osServer = new FileOutputStream(localPath);
			byte[] b = new byte[2048];
			int length;

			while ((length = input.read(b)) != -1) {
				os.write(b, 0, length);
				osServer.write(b, 0, length);
			}

			input.close();
			os.close();
			osServer.close();

		} catch (IOException e) {
			// Show faces message?
		}

	}



	
	
	
	///////////////////////////// Add Campany
	
	public String doAddCampany(){
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ProUser u = (ProUser)session.getAttribute("people");
	    
		entreprise.setProUser(u);
		
	
	 entreprise.setImage(name);
		entreprise.setLat(AddMarkersView.getI());
		 entreprise.setLng(AddMarkersView.getJ());
		 entreprise.setLocation(AddMarkersView.getLoc());
		// entreprise.setLocation(location);
		 	entrepriseServiceLocal.addEntreprise(entreprise);
		FacesContext context = FacesContext.getCurrentInstance();
	       
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa"+entreprise.getLat());
        context.addMessage(null, new FacesMessage("Successful",  "the product : "+entreprise.getName()+" is successfuly added ") );
		entreprise = new Campany();
		return "ListCampany?faces-redirect=true";
	}

	////////////Add marker Map
	
	 public void addMarker() {
	    	
	    	
		Marker marker = new Marker(new LatLng(entreprise.getLat(), entreprise.getLng()), entreprise.getLocation());
	        emptyModel.addOverlay(marker);
	        
	        lat = marker.getLatlng().getLat();
	        lng = marker.getLatlng().getLng();
	        location = marker.getTitle();
	        System.out.println("********************************************"+location);
	        AddMarkersView.setI(lat);
	        AddMarkersView.setJ(lng);
	        AddMarkersView.setLoc(location);

	        
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + entreprise.getLat() + ", Lng:" + entreprise.getLng()));
	    }
	
	
	public MapModel getEmptyModel() {
		return emptyModel;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	///////////////////////////////////update
	public String doUpdateCampany() {
		entrepriseServiceLocal.updateEntreprise(entreprise);
		
		return "ListCampany?faces-redirect=true";
		
	}
	
	/////////////////////////////Delete
	public String doDeleteCampany(){
//		entreprise =entrepriseServiceLocal.findCampanyById(entreprise.getCampanyId());
//		System.out.print(entreprise.getName());
   entrepriseServiceLocal.DeletCampanyById(entreprise.getCampanyId());
		return "ListCampany?faces-redirect=true";
		
	}
	
	
	////////////////////////////Affichage Map

	public MapModel displayMap()
	{
	 MapModel simpleModel = new DefaultMapModel();
		LatLng coordProduct = new LatLng(entreprise.getLat(), entreprise.getLng());
		simpleModel.addOverlay(new Marker(coordProduct,entreprise.getLocation()));
		return simpleModel;
		
	}
	
		
//public List<Campany> doListCampany(){
//		
//		return entrepriseServiceLocal.findAllCampanies();
//		
//	}
	
	public List<Campany> doListCampany(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		ProUser u = (ProUser)session.getAttribute("people");
	    
		entreprise.setProUser(u);
		return entrepriseServiceLocal.getAllEntrepriseByProUser(u);
		
	}
	
	public Campany getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Campany entreprise) {
		this.entreprise = entreprise;
	}


	
	
	
	
}
