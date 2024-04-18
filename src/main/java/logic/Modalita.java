package logic;

import java.io.IOException;

public abstract class Modalita {
    static Giocatore giocatore1;
    static Giocatore giocatore2;

    static SessioneGioco s;

    public Modalita(Giocatore giocatore1_, Giocatore giocatore2_) {
        giocatore1=giocatore1_;
        giocatore2=giocatore2_;

    }
    //aggiunto per
    public Modalita(SessioneGioco s1) {
    }

    public static Giocatore getGiocatore1() {
        return giocatore1;
    }

    public static Giocatore getGiocatore2() {
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
