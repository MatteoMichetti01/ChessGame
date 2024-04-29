package logic;

import domain.Giocatore;
import domain.Scacchiera;
/**
 * Questa interfaccia definisce i servizi disponibili per interagire con un giocatore durante una partita agli scacchi.
 *
 * @param <T> Il tipo di giocatore gestito dal servizio.
 */
public interface GiocatoreService<T extends Giocatore> {
    /**
     * Ottiene il pezzo che il giocatore desidera muovere sulla scacchiera.
     *
     * @param giocatore Il giocatore che deve selezionare il pezzo.
     * @param scacchiera La scacchiera corrente.
     * @return La rappresentazione testuale del pezzo selezionato dal giocatore.
     * @throws InputNonValido Se l'input del giocatore non è valido.
     */
    String getPezzo(Giocatore giocatore, Scacchiera scacchiera) throws InputNonValido;
    /**
     * Ottiene la posizione di destinazione della mossa del giocatore.
     *
     * @param p L'input fornito dal giocatore per specificare la posizione di destinazione della mossa.
     * @param scacchiera La scacchiera corrente.
     * @return La rappresentazione testuale della posizione di destinazione della mossa.
     */
    String getPosizioneMossa(String p, Scacchiera scacchiera);



}