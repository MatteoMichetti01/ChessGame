package logic;

import domain.Scacchiera;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.GetInstance();
    @Override
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
@Override
    public String getPosizioneMossa(String p, Scacchiera scacchiera) {
        gestioneInput.Pulisci();
        return gestioneInput.InputNonVuoto();

    }
}
