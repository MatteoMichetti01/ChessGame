package logic.impl;

import domain.Giocatore;
import domain.Scacchiera;
import domain.Umano;
import logic.*;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.getIstanza();
    @Override
    public String getPezzo(Giocatore g, Scacchiera scacchiera) throws InputNonValido {
        gestioneInput.pulisci();
        System.out.println("Inserisci il pezzo che vuoi spostare o inserisci 'o' per accedere alle opzioni:");
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
