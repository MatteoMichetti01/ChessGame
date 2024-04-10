package logic;

public interface GiocatoreService<T extends Giocatore> {
    String getPezzo() throws MossaNonValida;
    String getPosizioneMossa() throws MossaNonValida;
}