package logic;

import domain.Pezzo;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.getInstance();
    public String getPezzo(Giocatore g) throws MossaNonValida {
        if(g.getColore().equals("bianco")){
            return gestioneInput.leggiPezzoInput()+"W";
        }
        else return gestioneInput.leggiPezzoInput()+"B";

    }

    public String getPosizioneMossa(String p) throws MossaNonValida {
        return gestioneInput.inputNonVuoto();

    }
}
