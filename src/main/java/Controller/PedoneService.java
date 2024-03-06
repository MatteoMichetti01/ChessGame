package Controller;
import domain.*;
import logic.*;

public class PedoneService implements Mossa{

    Scacchiera scacchiera;

    public PedoneService (Scacchiera scacchiera) {
        this.scacchiera = scacchiera;
    }

    public Scacchiera move (String nomePezzo, String new_Posizione) {
        for (int i=1; i<9; i++) {
            for (int j = 1; j < 9; j++) {
                if(scacchiera.casella[i][j].getNome().equals(nomePezzo)) {
                    Pedone p1 = new Pedone(nomePezzo);
                    String pos = scacchiera.casella[i][j].getPosizione();
                    scacchiera.casella[i][j] = new Casella("  ",pos,false);
                    for (int k=1; k<9; k++) {
                        for (int y = 1; y < 9; y++) {
                            if (scacchiera.casella[k][y].getPosizione().equals(new_Posizione)) {
                                scacchiera.casella[k][y] = new Casella(new_Posizione,p1,k,y,true);
                            }
                        }
                    }
                }
            }
        }
        return scacchiera;
    }
}
