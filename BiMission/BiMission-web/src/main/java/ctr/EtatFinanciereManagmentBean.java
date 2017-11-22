package ctr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;

import persistence.Campany;
import persistence.EtatFinanciere;
import persistence.ProUser;
import services.EntrepriseServiceLocal;
import services.EtatFinanciereServiceLocal;
import services.EtatFinanciereServiceRemote;

@ManagedBean
@SessionScoped
public class EtatFinanciereManagmentBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id ;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@EJB
	private EtatFinanciereServiceLocal etatFinanciereServiceLocal;
	
	@EJB
	private EntrepriseServiceLocal entrepriseServiceLocal;
	
	private EtatFinanciere etatFinancier = new EtatFinanciere();
	
	  private BarChartModel barModel;
	  private MeterGaugeChartModel meterGaugeModel1;
	
	


	
	
	
	

	public EntrepriseServiceLocal getEntrepriseServiceLocal() {
		return entrepriseServiceLocal;
	}
	public void setEntrepriseServiceLocal(EntrepriseServiceLocal entrepriseServiceLocal) {
		this.entrepriseServiceLocal = entrepriseServiceLocal;
	}


	public MeterGaugeChartModel getMeterGaugeModel1() {
		return meterGaugeModel1;
	}
	public void setMeterGaugeModel1(MeterGaugeChartModel meterGaugeModel1) {
		this.meterGaugeModel1 = meterGaugeModel1;
	}



	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}
	public BarChartModel getBarModel() {
		return barModel;
	}

	public EtatFinanciereServiceLocal getEtatFinanciereServiceLocal() {
		return etatFinanciereServiceLocal;
	}
	public void setEtatFinanciereServiceLocal(EtatFinanciereServiceLocal etatFinanciereServiceLocal) {
		this.etatFinanciereServiceLocal = etatFinanciereServiceLocal;
	}



	public EtatFinanciere getEtatFinancier() {
		return etatFinancier;
	}
	public void setEtatFinancier(EtatFinanciere etatFinancier) {
		this.etatFinancier = etatFinancier;
	}


	
	
	
	////////////////// Ajout Etat
	
	public String doAddEtatFinancier(){
		Campany  campany = new Campany();
		campany.setCampanyId(id);
	etatFinancier.setCampany(campany);
	System.out.println("--------------------"+etatFinancier.getCA()+"-----"+etatFinancier.getCoutElectricité());
		etatFinanciereServiceLocal.addEtatFinanciere(etatFinancier);
		etatFinancier= new EtatFinanciere();
		return "ListEtatFinancier?faces-redirect=true";
	}
	
	
	////////////////////////// list Etat
	public List<EtatFinanciere> doListEtatFinanciere(){
				return etatFinanciereServiceLocal.findAllEtat();
			}
	
	//////////////////////////////update 
	
	public String doUpdateEtat() {
		
		etatFinanciereServiceLocal.updateEtatFinanciere(etatFinancier);
		return "ListEtatFinancier?faces-redirect=true";
		
	}
	
	////////////////////////////////Delete
	
	public String doDeleteEtat(){

		etatFinanciereServiceLocal.DeletEtatById(etatFinancier.getId());
		return "ListEtatFinancier?faces-redirect=true";
		
	}
	
	//////////////////////////Calcul Frais Total
	
	public double calculFraisTotal(int id) {
		double x = 0;
		Campany c = entrepriseServiceLocal.findCampanyById(id);

		List<EtatFinanciere> lst = etatFinanciereServiceLocal.findEtatByCampany(c);
		for (EtatFinanciere etatFinanciere : lst) {
			x = x + etatFinanciere.getFraisSalarier() + etatFinanciere.getCoutElectricité()
					+ etatFinanciere.getCoutSanitaire() + etatFinanciere.getCoutLoyer();
		}

		return x;
	}
	
	
	///////////////////// calcul salaire Nett
	
	public double calculsalaireNett() {
		double x = 0;
		Campany c = entrepriseServiceLocal.findCampanyById(id);
		System.out.println(c.getCampanyId() + "**************************************");
		List<EtatFinanciere> lst = etatFinanciereServiceLocal.findEtatByCampany(c);

		x = lst.get(0).getCA() - calculFraisTotal(id);
		System.out.println(x + "**************************************");

		return x;
	}
	//////////////////////////////////
	
	@PostConstruct
	public void init() {
		// createBarModels();
	//	createMeterGaugeModels();
	}
	
	////////////////////////////////////////////BarChart
	
	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();
		Campany c = entrepriseServiceLocal.findCampanyById(id);
		List<EtatFinanciere> lst = etatFinanciereServiceLocal.findEtatByCampany(c);
		
		ChartSeries CA = new ChartSeries();
		ChartSeries TotalFrais = new ChartSeries();
		
		TotalFrais.setLabel("TotalFrais");
		CA.setLabel("CA");
		
		for (EtatFinanciere et : lst) {
			CA.set(et.getDate(), et.getCA());
			double e =et.getFraisSalarier() + et.getCoutElectricité()
			+ et.getCoutSanitaire() + et.getCoutLoyer();
			TotalFrais.set(et.getDate(), e);
		}

		model.addSeries(CA);
		model.addSeries(TotalFrais);

		return model;
	}
	
	
	 public BarChartModel createBarModel() {
			barModel = initBarModel();
			barModel.setAnimate(true);
			barModel.setTitle("Chiffre d'affaire/Total frais par date");
			barModel.setLegendPosition("ne");

			Axis xAxis = barModel.getAxis(AxisType.X);
			xAxis.setLabel("Dates");

			Axis yAxis = barModel.getAxis(AxisType.Y);
			yAxis.setLabel("CA/Total Frais");
			yAxis.setMin(0);
			yAxis.setMax(10000);
			return  barModel;
		}
	
	 

//	  private void createBarModels() {
//	        createBarModel();
//	       
//	    }
	 
	 
	 ////////////////////////////////// MeterGaugeChart
	
	public MeterGaugeChartModel initMeterGaugeModel() {
		Campany c = entrepriseServiceLocal.findCampanyById(id);
		List<EtatFinanciere> lst = etatFinanciereServiceLocal.findEtatByCampany(c);

		List<Number> intervals = new ArrayList<Number>() {
			{

				System.out.print(lst.get(0).getObjectif() + "zzzzeuuuuu");
				add((lst.get(0).getObjectif() / 3));
				add(2 * (lst.get(0).getObjectif() / 3));
				add(lst.get(0).getObjectif());

			}
		};

		return new MeterGaugeChartModel(calculsalaireNett(), intervals);
	}

	public MeterGaugeChartModel createMeterGaugeModels() {
		meterGaugeModel1 = initMeterGaugeModel();
		meterGaugeModel1.setTitle("Gain par rapport à l'Objectif");
		meterGaugeModel1.setGaugeLabel("$");
		return meterGaugeModel1;
	}
	
	
	
	
	

	

	
	     
	 
	
	
	
}
