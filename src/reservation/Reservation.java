package reservation;
import aeroport.*;

import java.time.Duration;
import java.util.*;

public class Reservation {

    private Client client;
    private String numero;
    private Date date;
    private Vol vol;

    public etat etatResa = etat.EN_ATTENTE;
    public enum etat{
        EN_ATTENTE,
        PAYEE,
        CONFIRMEE,
        ANNULEE;
    }

    public Reservation() {
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void confirmer() throws IllegalArgumentException{
        switch(this.etatResa){
            case EN_ATTENTE:
                throw new IllegalArgumentException("La réservation doit être payée");
            case PAYEE:
                this.etatResa = etat.CONFIRMEE;
                break;
            case CONFIRMEE:
                break;
            case ANNULEE:
                throw new IllegalArgumentException("La réservation est annulée");
            default:
                throw new IllegalArgumentException("Erreur lors de la confirmation");
        }
    }

    public void annuler() throws IllegalArgumentException{
        Date dateNow = new Date();
        long diff = this.vol.getDateDepart().getTime() - dateNow.getTime();
        switch(this.etatResa){
            case EN_ATTENTE:
                this.etatResa = etat.ANNULEE;
                break;
            case PAYEE:
                System.out.println("Réservation annulée");
                //la différence entre maintenant et le départ doit être supérieure à 5 jours
                if(diff / (1000*60*60*24) <= 5){ //conversion diff (millisecondes) en jours
                    System.out.println("Remboursement impossible, date de départ trop proche");
                }
                else{
                    System.out.println("Votre remboursement va être effectué");
                }
                this.etatResa = etat.ANNULEE;
                vol.retraitPassager();
                vol.ouvrir();
                break;
            case CONFIRMEE:
                System.out.println("Réservation annulée");
                System.out.println("Remboursement impossible, la réservation était confirmée");
                this.etatResa = etat.ANNULEE;
                break;
            case ANNULEE:
                break;
            default:
                throw new IllegalArgumentException("Erreur lors de l'annulation");
        }
    }

    public void payer() throws IllegalArgumentException{
        switch(this.etatResa){
            case EN_ATTENTE:
                if (vol.estOuvert()){
                    this.etatResa = etat.PAYEE;
                    vol.ajoutPassager();
                    if(vol.getPassagers() == vol.getPlaces()){
                        vol.fermer();
                    }
                    break;
                }
                else{
                    throw new IllegalArgumentException("Ce vol est fermé à la réservation");
                }
            case PAYEE:
                break;
            case CONFIRMEE:
                throw new IllegalArgumentException("La réservation est déjà payée");
            case ANNULEE:
                throw new IllegalArgumentException("La réservation est annulée");
            default:
                throw new IllegalArgumentException("Erreur lors du paiement");
        }
    }

    public Vol getVol() {
        return vol;
    }
    public void setVol(Vol vol) {
        this.vol = vol;
    }
}