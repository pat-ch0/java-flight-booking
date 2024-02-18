import aeroport.*;
import reservation.*;
import reservation.Reservation.etat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Start {

    public static void main(String[] args){
        Vol volFinal = new Vol();

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        String dd = "15/05/2023 13:00";
        String da = "17/05/2023 02:15";

        try {
            volFinal.setDateDepart(format.parse(dd));
            volFinal.setDateArrivee(format.parse(da));
        } catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }

        System.out.println(volFinal.getDateArrivee());
        System.out.println(volFinal.obtenirDuree().toString().substring(2));




        //Bidirectional
        Vol vol = new Vol();
        vol.setNumero("abc1");
        Vol vol2 = new Vol();
        vol2.setNumero("abc2");

        Compagnie compagnie = new Compagnie();
        compagnie.setName("Air France");
        compagnie.addVol(vol);
        compagnie.addVol(vol2);

        for(Vol v : compagnie.getVols()){
            System.out.println(v.getNumero());
        }

        System.out.println(vol.getCompagnie().getName());
        System.out.println(vol2.getCompagnie().getName());

        vol2.setCompagnie(null);
        System.out.println(vol2.getCompagnie());

        for(Vol v : compagnie.getVols()){
            System.out.println(v.getNumero());
        }

        /* Reservation resa = new Reservation();
        resa.setVol(volFinal);
        resa.payer();
        resa.annuler(); */
        
        Escale escal = new Escale();
        Aeroport aero = new Aeroport();
        aero.setNom("MTP");
        String dep = "15/05/2023 18:00";
        String ar = "16/05/2023 03:00";
        try{
            escal.setDepart(format.parse(dep));
            escal.setArrivee(format.parse(ar));
        }
        catch (Exception e){
            throw new RuntimeException("Unable to format to date");
        }
        escal.setAeroport((aero));
        volFinal.addEscale(escal);
        volFinal.getEscales();
    }
}