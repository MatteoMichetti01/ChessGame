package logic;

public class UmanoServiceImpl implements GiocatoreService<Umano> {
    GestioneInput gestioneInput = GestioneInput.getInstance();
    @Override
    public String getPezzo() throws MossaNonValida {
        return gestioneInput.leggiPezzoInput();
    }
    @Override
    public String getPosizioneMossa() throws MossaNonValida {
        return gestioneInput.inputNonVuoto();

    }
}
