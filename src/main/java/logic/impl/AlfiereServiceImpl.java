package logic.impl;

import domain.Alfiere;
import domain.Scacchiera;
import logic.MossaNonValida;
import logic.PezzoService;

/**
 * Implementazione del servizio per il pezzo Alfiere.
 */
public class AlfiereServiceImpl implements PezzoService<Alfiere> {

    /**
     * Controlla se la mossa dell'Alfiere è valida.
     *
     * @param nuovaPosX     La nuova posizione X dell'Alfiere.
     * @param nuovaPosY     La nuova posizione Y dell'Alfiere.
     * @param vecchiaPosX   La vecchia posizione X dell'Alfiere.
     * @param vecchiaPosY   La vecchia posizione Y dell'Alfiere.
     * @param scacchiera    La scacchiera attuale.
     * @throws MossaNonValida Se la mossa dell'Alfiere non è valida.
     */
    @Override
    public void controlloMossa (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {

        if (Math.abs(nuovaPosX - vecchiaPosX) != Math.abs(nuovaPosY - vecchiaPosY))
            throw new MossaNonValida("L'alfiere può andare solo in diagonale");

        //CASO SPOSTAMENTO IN DIAGONALE IN AVANTI VERSO DESTRA
        if (vecchiaPosX > nuovaPosX && vecchiaPosY < nuovaPosY) {
            int i = vecchiaPosX - 1;
            int j = vecchiaPosY + 1;
            while (i > nuovaPosX && j < nuovaPosY) {
                if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                i--;
                j++;
            }
        }

        //CASO SPOSTAMENTO IN DIAGONALE IN AVANTI VERSO SINISTRA
        if (vecchiaPosX > nuovaPosX && vecchiaPosY > nuovaPosY) {
            int i = vecchiaPosX - 1;
            int j = vecchiaPosY - 1;
            while (i > nuovaPosX && j > nuovaPosY) {
                if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                i--;
                j--;
            }
        }

        //CASO SPOSTAMENTO IN DIAGONALE INDIETRO VERSO DESTRA
        if (vecchiaPosX < nuovaPosX && vecchiaPosY < nuovaPosY) {
            int i = vecchiaPosX + 1;
            int j = vecchiaPosY + 1;
            while (i < nuovaPosX && j < nuovaPosY) {
                if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                i++;
                j++;
            }
        }

        //CASO SPOSTAMENTO IN DIAGONALE INDIETRO VERSO SINISTRA
        if (vecchiaPosX < nuovaPosX && vecchiaPosY > nuovaPosY) {
            int i = vecchiaPosX + 1;
            int j = vecchiaPosY - 1;
            while (i < nuovaPosX && j > nuovaPosY) {
                if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                i++;
                j--;
            }
        }

    }
}
