package logic;
import domain.Scacchiera;

import java.util.*;
public class SalvataggioMosse {
    LinkedList<Scacchiera> mosse;

    public SalvataggioMosse() {
        this.mosse = new LinkedList<>();
    }
    public void addMossa(Scacchiera scacchiera){
        if(mosse.size()==5){
            mosse.remove(0);
        }
        mosse.addLast(scacchiera);
    }
    public Scacchiera undoMosse(int quanto) throws MossaNonValida {
        if(quanto> mosse.size()){throw new MossaNonValida("Non hai fatto abbastanza mosse");}
        Scacchiera vecchiaScacchiera;
        vecchiaScacchiera = mosse.remove(mosse.size()-quanto);
        return vecchiaScacchiera;
    }
}
