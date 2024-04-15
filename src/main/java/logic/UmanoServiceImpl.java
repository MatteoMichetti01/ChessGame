package logic;

import domain.Pezzo;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.getInstance();
    public String getPezzo(Giocatore g) throws MossaNonValida {
        return gestioneInput.leggiPezzoInput();
    }

    public String getPosizioneMossa(String p) throws MossaNonValida {
        return gestioneInput.inputNonVuoto();

    }
}
