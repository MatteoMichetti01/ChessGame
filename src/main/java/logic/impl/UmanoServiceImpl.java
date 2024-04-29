package logic.impl;

import domain.Giocatore;
import domain.Scacchiera;
import domain.Umano;
import logic.*;

/**
 * Questa classe fornisce un'implementazione dei metodi per gestire le azioni di un giocatore umano.
 */
public class UmanoServiceImpl implements GiocatoreService<Umano> {
    /**
     * Istanzia un oggetto GestioneInput per la gestione dell'input utente.
     */
    GestioneInput gestioneInput = GestioneInput.getIstanza();

    /**
     * Ottiene il pezzo che il giocatore umano desidera spostare.
     *
     * @param g Il giocatore umano.
     * @param scacchiera La scacchiera su cui si sta giocando.
     * @return Il pezzo selezionato dal giocatore umano, o 'o' per accedere alle opzioni.
     * @throws InputNonValido Se l'input inserito non è valido.
     */
    @Override
    public String getPezzo(Giocatore g, Scacchiera scacchiera) throws InputNonValido {
        gestioneInput.pulisci();
        System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
        String input = gestioneInput.leggiPezzoInput();
        if(input.equals("o")){
            SessioneGioco.setSelezioneMenu(true);
            return input;
        }
        if(g.getColore().equals("bianco")){
            return input+"W";
        }
        else return input+"B";

    }

    /**
     * Ottiene la posizione in cui il giocatore umano vuole muovere il pezzo selezionato.
     *
     * @param p Il pezzo selezionato.
     * @param scacchiera La scacchiera su cui si sta giocando.
     * @return La posizione desiderata per il pezzo.
     */
    @Override
    public String getPosizioneMossa(String p, Scacchiera scacchiera) {
        gestioneInput.pulisci();
        return gestioneInput.inputNonVuoto();
    }
}
