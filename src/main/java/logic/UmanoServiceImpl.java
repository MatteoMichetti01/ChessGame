package logic;

import domain.Pezzo;
import domain.Scacchiera;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.getInstance();
    public String getPezzo(Giocatore g, Scacchiera scacchiera) throws MossaNonValida {
        if(g.getColore().equals("bianco")){
            return gestioneInput.leggiPezzoInput()+"W";
        }
        else return gestioneInput.leggiPezzoInput()+"B";

    }

    public String getPosizioneMossa(String p, Scacchiera scacchiera) throws MossaNonValida {
        return gestioneInput.inputNonVuoto();

    }
}
