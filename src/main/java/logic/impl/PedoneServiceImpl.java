package logic.impl;
import domain.*;
import domain.Scacchiera;
import logic.MossaNonValida;
import logic.PezzoService;

/**
 * Questa classe fornisce un'implementazione dei metodi per il controllo delle mosse del Pedone.
 */
public class PedoneServiceImpl implements PezzoService<Pedone> {

    /**
     * Controlla la validità di una mossa per il Pedone sulla scacchiera.
     *
     * @param nuovaPosX   La nuova posizione X del pezzo.
     * @param nuovaPosY   La nuova posizione Y del pezzo.
     * @param vecchiaPosX La posizione X attuale del pezzo.
     * @param vecchiaPosY La posizione Y attuale del pezzo.
     * @param scacchiera  La scacchiera su cui si sta giocando.
     * @throws MossaNonValida Se la mossa del Pedone non è valida.
     */
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {

        if (nuovaPosY == vecchiaPosY && (vecchiaPosX == 2 || vecchiaPosX == 7)) {
            if (Math.abs(nuovaPosX - vecchiaPosX) > 2)
                throw new MossaNonValida("Mossa non valida, il pedone può avanzare alla prima mossa al massimo di due caselle");
        } else {
            if (nuovaPosY == vecchiaPosY && Math.abs(nuovaPosX - vecchiaPosX) > 1)
                throw new MossaNonValida("Mossa non valida, il pedone può avanzare una casella alla volta");
        }
        if (nuovaPosY != vecchiaPosY) {
            if (Math.abs(nuovaPosX - vecchiaPosX) != 1 || Math.abs(nuovaPosY - vecchiaPosY) != 1) {
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");
            }
            if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() == null)
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");
            if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null && scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");
        }

        String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
        if (colore.equals("bianco")) {
            if (vecchiaPosX < nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
        } else {
            if (vecchiaPosX > nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
        }

        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null && nuovaPosY == vecchiaPosY)
            throw new MossaNonValida("il pedone mangia solo in diagonale");

        if (nuovaPosY == vecchiaPosY) {
            int inizio = Math.min(vecchiaPosX, nuovaPosX) + 1;
            int fine = Math.max(vecchiaPosX, nuovaPosX);
            for (int i = inizio; i < fine; i++) {
                if (scacchiera.casella[i][vecchiaPosY].isOccupata()) {
                    throw new MossaNonValida("Pezzo in mezzo");
                }
            }
        }


    }
}
