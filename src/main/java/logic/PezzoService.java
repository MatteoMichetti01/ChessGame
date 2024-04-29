package logic;
import domain.*;
import logic.MossaNonValida;


public interface PezzoService<T extends Pezzo> {
    /**
     * Controlla se una mossa è valida per il pezzo nelle posizioni specificate sulla scacchiera.
     *
     * @param nuovaPosX La nuova posizione X del pezzo.
     * @param nuovaPosY La nuova posizione Y del pezzo.
     * @param vecchiaPosX La posizione X attuale del pezzo.
     * @param vecchiaPosY La posizione Y attuale del pezzo.
     * @param scacchiera La scacchiera su cui viene effettuata la mossa.
     * @throws MossaNonValida Se la mossa non è valida per il pezzo o se si verifica un errore durante il controllo.
     */
    public void controlloMossa (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida;
}