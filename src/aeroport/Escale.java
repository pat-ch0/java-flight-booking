package aeroport;

import java.util.*;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Escale{
	private Date depart;
	private Date arrivee;
	private Aeroport aeroport;
	
	public Duration obtenirDuree() {
		if(this.depart != null && this.arrivee != null) {
		    return Duration.of(arrivee.getTime() - depart.getTime(), ChronoUnit.MILLIS);
		}
		return null;
	}
	
	public Date getDepart() {
		return depart;
	}
	public void setDepart(Date depart) {
		this.depart = depart;
	}
	
	public Date getArrivee() {
		return arrivee;
	}
	public void setArrivee(Date arrivee) {
		this.arrivee = arrivee;
	}
	
	public Aeroport getAeroport(){
		return aeroport;
	}
	public void setAeroport(Aeroport aeroport){
		this.aeroport = aeroport;
	}
}
