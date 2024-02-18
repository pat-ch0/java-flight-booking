package aeroport;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Vol{

    private String numero;
    private Aeroport depart;
    private Aeroport arrivee;
    private Compagnie compagnie;
    private Date dateDepart;
    private Date dateArrivee;
    private int passagers = 0;
    private int places = 120;
    private boolean ouvert = true;
    private Collection<Escale> escales = new ArrayList<>();
    
    public void ouvrir(){
    	ouvert = true;
    }
    
    public void fermer(){
    	ouvert = false;
    }
    
    public boolean estOuvert(){
    	if (ouvert){
    		return true;
    	}
    	return false;
    }
    
    public void getEscales(){
        for (Escale e : escales){
            System.out.println(e.getAeroport().getNom());
        }
    }
    public void setEscales(Collection<Escale> escales){
    	this.escales = escales;
    }
    public void addEscale(Escale escale){
        this.escales.add(escale);
    }

    public Duration obtenirDuree() {
        if(this.dateDepart != null && this.dateArrivee != null) {
            return Duration.of(dateArrivee.getTime() - dateDepart.getTime(), ChronoUnit.MILLIS);
        }
        return null;
    }

    public Date getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }
    public void setDateArrivee(Date dateArrivee) throws IllegalArgumentException{
        if (dateArrivee.compareTo(dateDepart) >= 0){
        	this.dateArrivee = dateArrivee;
        }
        else{
        	throw new IllegalArgumentException("La date d'arrivée doit être après la date de départ");
        }
    }

    public Vol() {
    }

    protected Vol(String numero){
        this.numero = numero;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }
    
    public void setCompagnie(Compagnie compagnie) {
        if(compagnie!=null) {
            compagnie.addVolWithoutBidirectional(this);
        }
        if(this.compagnie!=null){
            this.compagnie.removeVolWithoutBidirectional(this);
        }
        this.compagnie = compagnie;
    }

    protected void setCompagnieWithoutBidirectional(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Aeroport getDepart() {
        return depart;
    }
    public void setDepart(Aeroport depart) {
        this.depart = depart;
    }

    public Aeroport getArrivee() {
        return arrivee;
    }
    public void setArrivee(Aeroport arrivee) {
        this.arrivee = arrivee;
    }

    public int getPassagers() {
        return passagers;
    }
    public void setPassagers(int passagers) {
        this.passagers = passagers;
    }
    public void ajoutPassager(){
        this.passagers += 1;
    }
    public void retraitPassager(){
        this.passagers -= 1;
    }

    public int getPlaces() {
        return places;
    }
    public void setPlaces(int places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return ((Vol) obj).getNumero().equals(this.numero);
        } catch (Exception e){
            return false;
        }
    }
}
