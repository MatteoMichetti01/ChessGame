package controller;
import domain.*;
import logic.MossaNonValida;

public interface Mossa  {
    public Scacchiera move(String nomePezzo, String new_Posizione, String colore) throws MossaNonValida;
}
