package logic;

import domain.Scacchiera;

public interface GiocatoreService<T extends Giocatore> {
    String getPezzo(Giocatore giocatore, Scacchiera scacchiera) throws MossaNonValida;
    String getPosizioneMossa(String p, Scacchiera scacchiera) throws MossaNonValida;



}