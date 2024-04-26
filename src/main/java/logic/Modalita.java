package logic;

import java.io.IOException;
import java.io.Serializable;

public abstract class Modalita implements Serializable {
    Giocatore giocatore1;
    Giocatore giocatore2;


    public Modalita(Giocatore giocatore1_, Giocatore giocatore2_) {
        giocatore1=giocatore1_;
        giocatore2=giocatore2_;
    }

    public abstract void avviaPartita() throws MossaNonValida, IOException;
    public abstract String opzioni() throws MossaNonValida;
}
