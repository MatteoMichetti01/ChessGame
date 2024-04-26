package logic;

import domain.Scacchiera;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.getIstanza();
    @Override
    public String getPezzo(Giocatore g, Scacchiera scacchiera) throws MossaNonValida {
        gestioneInput.pulisci();
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
@Override
    public String getPosizioneMossa(String p, Scacchiera scacchiera) {
        gestioneInput.pulisci();
        return gestioneInput.inputNonVuoto();

    }
}
