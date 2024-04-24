package logic;
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
        clone = clone.Clone(scacchiera);
        mosse.add(clone);
    }
    public Scacchiera UndoMosse(int quanto) throws MossaNonValida {
        if(quanto >= mosse.size()){System.out.println("Puoi tornare indietro al più "+mosse.size()/2+" mosse!");
            throw new MossaNonValida("Non hai fatto abbastanza mosse");}
        Scacchiera vecchiaScacchiera;
        vecchiaScacchiera = mosse.get(mosse.size()-1-quanto);
        int size = mosse.size();
        for(int i = 0; i<quanto;i++){
            mosse.remove(size-i-1);
        }
        return vecchiaScacchiera;
    }

}
