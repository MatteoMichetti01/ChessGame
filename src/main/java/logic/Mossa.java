package logic;
import domain.*;

public interface Mossa  {
    Scacchiera move(String nomePezzo, String new_Posizione, String colore) throws MossaNonValida;
}
