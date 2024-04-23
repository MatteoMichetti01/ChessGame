package logic;
import domain.Casella;
import domain.Pezzo;
import domain.Scacchiera;

import java.io.Serializable;
import java.util.*;
public class SalvataggioMosse implements Serializable {

    List<Scacchiera> mosse;
    public SalvataggioMosse() {
        this.mosse = new LinkedList<>();
    }
    public void addMossa(Scacchiera scacchiera){
        if(mosse.size()==10){
            mosse.remove(0);
            mosse.remove(0);
        }
        Scacchiera clone = new Scacchiera();
        clone = clone.clone(scacchiera);
        mosse.add(clone);
    }
    public Scacchiera undoMosse(int quanto) throws MossaNonValida {
        if(quanto >= mosse.size()){throw new MossaNonValida("Non hai fatto abbastanza mosse");}
        Scacchiera vecchiaScacchiera;
        vecchiaScacchiera = mosse.remove(mosse.size()-1-quanto);
        return vecchiaScacchiera;
    }

}
