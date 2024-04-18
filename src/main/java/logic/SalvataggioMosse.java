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


    /*
    List<String> mosse;
    List<String> pezzi;
    public SalvataggioMosse() {
        this.mosse = new LinkedList<>();
        this.pezzi = new LinkedList<>();
    }

    public void addMossa(String pezzo, String mossa){
        if(mosse.size()==10){
            mosse.remove(0);
            mosse.remove(0);
            pezzi.remove(0);
            pezzi.remove(0);
        }
        mosse.add(mossa);
        pezzi.add(pezzo);
    }

    public Scacchiera undoMosse(int quanto, Scacchiera scacchiera) throws MossaNonValida {
        int vecchiaPosX=0;
        int vecchiaPosY=0;
        if(quanto> mosse.size()){throw new MossaNonValida("Non hai fatto abbastanza mosse");}
        for (int i = 0; i < quanto; i++) {
            for (int x = 1; x < 9; x++) {
                for (int y = 1; y < 9; y++) {
                    if (scacchiera.casella[x][y].getPosizione().equals(mosse.get(mosse.size()-1))) {
                        //qua ci sta la posizione dove devo rimettere il pezzo
                        vecchiaPosX = x;
                        vecchiaPosY = y;
                    }
                }
            }
            mosse.remove(mosse.size()-1);
            for(int j=0; j<9; j++) {
                for (int z=0; z<9; z++) {
                    if(scacchiera.casella[j][z].isOccupata() && scacchiera.casella[j][z].getPezzo().getNome().equals(pezzi.get(pezzi.size()-1))) {
                        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[j][z].getPezzo(), vecchiaPosX,vecchiaPosY,true);
                        scacchiera.casella[j][z] = new Casella("   ", scacchiera.casella[j][z].getPosizione(),false);
                    }
                }
            }
            pezzi.remove(pezzi.size()-1);
        }
        return scacchiera;
    }*/

}
