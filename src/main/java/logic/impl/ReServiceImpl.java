package logic.impl;

import domain.Casella;
import domain.Pezzo;
import domain.Re;
import domain.Scacchiera;
import logic.MossaNonValida;
import logic.PezzoService;
import logic.PezzoServiceFactory;

/**
 * Questa classe fornisce un'implementazione dei metodi per il controllo delle mosse del Re.
 */
public class ReServiceImpl implements PezzoService<Re> {

    /**
     * Controlla la validità di una mossa per il Re sulla scacchiera.
     *
     * @param nuovaPosX   La nuova posizione X del pezzo.
     * @param nuovaPosY   La nuova posizione Y del pezzo.
     * @param vecchiaPosX La posizione X attuale del pezzo.
     * @param vecchiaPosY La posizione Y attuale del pezzo.
     * @param scacchiera  La scacchiera su cui si sta giocando.
     * @throws MossaNonValida Se la mossa del Re non è valida.
     */
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        // Verifica che il re si stia muovendo di una sola casella in qualsiasi direzione
        if (!(Math.abs(nuovaPosX - vecchiaPosX) <= 1 && Math.abs(nuovaPosY - vecchiaPosY) <= 1)) {
            throw new MossaNonValida("Il re può muoversi solo di una casella alla volta");
        }


        // Verifica se la destinazione è occupata dallo stesso colore o meno
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() &&
                scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore())) {
            throw new MossaNonValida("La casella è già occupata dallo stesso colore");
        }

        // verifica che il re non mangi un pezzo protetto.
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null) {
            if (!(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))) {
                boolean controllo = controlloPezzoProtetto(scacchiera,nuovaPosX,nuovaPosY);
                if(controllo)
                    throw new MossaNonValida("il pezzo è protetto");
            }
        }
        //verifica che il re non si metta in scacco da solo
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() == null) {
            boolean autoscacco= controlloAutoScacco(scacchiera, nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY);
            if (autoscacco)
                throw new MossaNonValida("Non puoi darti scacco da solo");

        }

    }


    /**
     * Controlla se un pezzo è protetto da altri pezzi del proprio colore.
     *
     * @param scacchiera La scacchiera su cui si sta giocando.
     * @param nuovaPosX  La nuova posizione X del pezzo.
     * @param nuovaPosY  La nuova posizione Y del pezzo.
     * @return True se il pezzo è protetto, altrimenti False.
     */
    public static boolean controlloPezzoProtetto (Scacchiera scacchiera, int nuovaPosX, int nuovaPosY) {
        String colore = scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore();
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (i != nuovaPosX || j != nuovaPosY) {
                    if (scacchiera.casella[i][j].getPezzo() != null && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().charAt(0) != 'r') {
                            try {
                                PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[i][j].getPezzo().getClass());
                                service.controlloMossa(nuovaPosX, nuovaPosY, i, j, scacchiera);
                                return true;
                            } catch (MossaNonValida m) {}
                        }
                    }
                }
            }
        }
        return false;
    }


    /**
     * Controlla se una mossa del Re mette il Re stesso in scacco.
     *
     * @param scacchiera  La scacchiera su cui si sta giocando.
     * @param nuovaPosX   La nuova posizione X del Re.
     * @param nuovaPosY   La nuova posizione Y del Re.
     * @param vecchiaPosX La posizione X attuale del Re.
     * @param vecchiaPosY La posizione Y attuale del Re.
     * @return True se la mossa mette il Re in scacco, altrimenti False.
     */
    public static boolean controlloAutoScacco(Scacchiera scacchiera, int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY) {
        String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                    if (scacchiera.casella[i][j].getPezzo().getNome().charAt(0) != 'r') {
                        try {
                            //simula la mossa
                            scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX,nuovaPosY,true);
                            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("  ",scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), false);
                            //controlla la mossa
                            PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[i][j].getPezzo().getClass());
                            service.controlloMossa(nuovaPosX,nuovaPosY,i,j,scacchiera);
                            //annulla la mossa (torna indietro)
                            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                            scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione() , false);
                            return true;
                        } catch (MossaNonValida m) {
                            //nel caso la mossa non va a buon fine essa deve essere in ogni caso annullata
                            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                            scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione() , false);
                        }
                    }
                }
            }
        }
        return false;
    }
}

