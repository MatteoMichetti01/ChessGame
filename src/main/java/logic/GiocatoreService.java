package logic;

import domain.Giocatore;
import domain.Scacchiera;

public interface GiocatoreService<T extends Giocatore> {
    String getPezzo(Giocatore giocatore, Scacchiera scacchiera) throws InputNonValido;
    String getPosizioneMossa(String p, Scacchiera scacchiera);



}