package logic;
import domain.*;


public interface Mossa  {
    /**
     * Muove un pezzo sulla scacchiera.
     *
     * @param nomePezzo     Il nome del pezzo da muovere.
     * @param new_Posizione La nuova posizione del pezzo.
     * @param colore        Il colore del pezzo.
     * @return La scacchiera dopo il movimento del pezzo.
     * @throws MossaNonValida Eccezione che indica un errore nel movimento del pezzo.
     */
    Scacchiera move(String nomePezzo, String new_Posizione, String colore) throws MossaNonValida;
}
