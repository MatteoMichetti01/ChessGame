package logic;

public interface GiocatoreService<T extends Giocatore> {
    String getPezzo(Giocatore giocatore) throws MossaNonValida;
    String getPosizioneMossa(String p) throws MossaNonValida;



}