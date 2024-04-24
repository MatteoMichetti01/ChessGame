package logic;

import domain.Scacchiera;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.GetInstance();
    public String getPezzo(Giocatore g, Scacchiera scacchiera) throws MossaNonValida {
        gestioneInput.Pulisci();
        String input = gestioneInput.LeggiPezzoInput();
        if(input.equals("o")){
            SessioneGioco.SetSelezioneMenu(true);
            return input;
        }
        if(g.getColore().equals("bianco")){
            return input+"W";
        }
        else return input+"B";

    }

    public String getPosizioneMossa(String p, Scacchiera scacchiera) throws MossaNonValida {
        gestioneInput.Pulisci();
        return gestioneInput.InputNonVuoto();

    }
}
