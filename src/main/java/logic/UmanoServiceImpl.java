package logic;

import domain.Pezzo;
import domain.Scacchiera;

import java.io.Serializable;

public class UmanoServiceImpl implements GiocatoreService<Umano>{
    GestioneInput gestioneInput = GestioneInput.getInstance();
    public String getPezzo(Giocatore g, Scacchiera scacchiera) throws MossaNonValida {
        String input = gestioneInput.leggiPezzoInput();
        if(input.equals("o")){
            SessioneGioco.setSelezioneMenu(true);
            return input;
        }
        if(g.getColore().equals("bianco")){
            return input+"W";
        }
        else return input+"B";

    }

    public String getPosizioneMossa(String p, Scacchiera scacchiera) throws MossaNonValida {
        return gestioneInput.inputNonVuoto();

    }
}
