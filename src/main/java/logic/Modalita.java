package logic;

import domain.Pezzo;

public abstract class Modalita {
    Giocatore giocatore1;
    Giocatore giocatore2;

    public Modalita(Giocatore giocatore1, Giocatore giocatore2) {
        this.giocatore1=giocatore1;
        this.giocatore2=giocatore2;

    }

    public Modalita(Giocatore giocatore11) {
    this.giocatore1=giocatore1;
    }

    public abstract void startGame() throws MossaNonValida;
    public abstract String opzioni();
}
