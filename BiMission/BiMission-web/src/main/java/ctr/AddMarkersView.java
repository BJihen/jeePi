package ctr;


import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
  
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import persistence.Campany;
import services.EntrepriseServiceLocal;
 
@ManagedBean
@ViewScoped
public class AddMarkersView implements Serializable {
     private static double i ;
     private static double j ;
     private static String loc ;
     
    public static String getLoc() {
		return loc;
	}

	public static void setLoc(String loc) {
		AddMarkersView.loc = loc;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MapModel emptyModel;
      
//    private String title;
//      
//    private double lat;
//      
//    private double lng;
    
    
    @EJB
	private EntrepriseServiceLocal entrepriseServiceLocal;
	
	
	private Campany entreprise = new Campany();
    
    
  
    @PostConstruct
    public void init() {
        emptyModel = new DefaultMapModel();
    }
      
    public MapModel getEmptyModel() {
        return emptyModel;
    }
      
//    public String getTitle() {
//        return title;
//    }
//  
//    public void setTitle(String title) {
//        this.title = title;
//    }
//  
//    public double getLat() {
//        return lat;
//    }
//  
//    public void setLat(double lat) {
//        this.lat = lat;
//    }
//  
//    public double getLng() {
//        return lng;
//    }
//  
//    public void setLng(double lng) {
//        this.lng = lng;
//    }
      
    public void addMarker() {
    	Campany c = new Campany();
    	c.setLat(entreprise.getLat());
    	c.setLng(entreprise.getLng());
    	c.setLocation(entreprise.getLocation());
    	
        Marker marker = new Marker(new LatLng(entreprise.getLat(), entreprise.getLng()), entreprise.getLocation());
        emptyModel.addOverlay(marker);
    	
         entrepriseServiceLocal.addEntreprise(c);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + entreprise.getLat() + ", Lng:" + entreprise.getLng()));
    }

	public EntrepriseServiceLocal getEntrepriseServiceLocal() {
		return entrepriseServiceLocal;
	}

	public void setEntrepriseServiceLocal(EntrepriseServiceLocal entrepriseServiceLocal) {
		this.entrepriseServiceLocal = entrepriseServiceLocal;
	}

	public Campany getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Campany entreprise) {
		this.entreprise = entreprise;
	}

	public void setEmptyModel(MapModel emptyModel) {
		this.emptyModel = emptyModel;
	}

	public static double getI() {
		return i;
	}

	public static void setI(double i) {
		AddMarkersView.i = i;
	}

	public static double getJ() {
		return j;
	}

	public static void setJ(double j) {
		AddMarkersView.j = j;
	}
	
}