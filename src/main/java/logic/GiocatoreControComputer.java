package logic;

import controller.MossaNonValida;

public class GiocatoreControComputer extends Modalita{

    public GiocatoreControComputer(Giocatore giocatore1) {

        super(giocatore1);

    }

    @Override
    public void startGame() throws MossaNonValida {

    }

    @Override
    public String opzioni() {

        return null;
    }
}
