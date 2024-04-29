package logic;

import domain.Giocatore;

import java.io.IOException;
import java.io.Serializable;

public abstract class Modalita implements Serializable {
    public Giocatore giocatore1;
    public Giocatore giocatore2;

    /**
     * Costruttore della modalità di gioco.
     *
     * @param giocatore1_ Il primo giocatore.
     * @param giocatore2_ Il secondo giocatore.
     */
    public Modalita(Giocatore giocatore1_, Giocatore giocatore2_) {
        giocatore1=giocatore1_;
        giocatore2=giocatore2_;
    }

    /**
     * Avvia la partita della modalità di gioco.
     *
     * @throws MossaNonValida Se viene effettuata una mossa non valida.
     * @throws IOException    Se si verifica un errore di input/output.
     * @throws InputNonValido Se viene fornito un input non valido.
     */
    public abstract void avviaPartita() throws MossaNonValida, IOException, InputNonValido;

    /**
     * Restituisce le opzioni della modalità di gioco.
     *
     * @return Le opzioni della modalità di gioco.
     * @throws MossaNonValida Se si verifica un errore nella gestione delle mosse.
     */
    public abstract String opzioni() throws MossaNonValida;
}
