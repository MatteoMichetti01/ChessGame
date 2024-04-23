package logic;

import java.io.IOException;
import java.io.Serializable;

public abstract class Modalita implements Serializable {
    Giocatore giocatore1;
    Giocatore giocatore2;

    static SessioneGioco s;

    public Modalita(Giocatore giocatore1_, Giocatore giocatore2_) {
        giocatore1=giocatore1_;
        giocatore2=giocatore2_;

    }
    //aggiunto per
    public Modalita(SessioneGioco s1) {
    }

    public Giocatore getGiocatore1() {
        return giocatore1;
    }

    public Giocatore getGiocatore2() {
        return giocatore2;
    }

    public Modalita(Giocatore giocatore1_) {
    giocatore1=giocatore1_;
    }
    public Modalita() {
        // Costruttore vuoto necessario per la deserializzazione
    }

    public abstract void startGame() throws MossaNonValida, IOException;
    public abstract String opzioni() throws MossaNonValida;
}
