package logic;

import domain.Regina;
import domain.Scacchiera;

public class ReginaServiceImpl implements PezzoService<Regina> {
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        //controllo che vada solo avanti o indietro sulla stessa riga o colonna e in diagonale
        if (!((nuovaPosX == vecchiaPosX || nuovaPosY == vecchiaPosY) ||
                (Math.abs(nuovaPosX - vecchiaPosX) == Math.abs(nuovaPosY - vecchiaPosY)))) {
            throw new MossaNonValida("Mossa non valida per la regina");
        }

        //pezzo in mezzo
        if (nuovaPosX == vecchiaPosX) {
            int inizio = Math.min(vecchiaPosY, nuovaPosY) + 1;
            int fine = Math.max(vecchiaPosY, nuovaPosY);
            for (int i = inizio; i < fine; i++) {
                if (scacchiera.casella[vecchiaPosX][i].isOccupata()) {
                    throw new MossaNonValida("Pezzo in mezzo");
                }
            }
            //pezzo in mezzo
        } else if (nuovaPosY == vecchiaPosY) {
            int inizio = Math.min(vecchiaPosX, nuovaPosX) + 1;
            int fine = Math.max(vecchiaPosX, nuovaPosX);
            for (int i = inizio; i < fine; i++) {
                if (scacchiera.casella[i][vecchiaPosY].isOccupata()) {
                    throw new MossaNonValida("Pezzo in mezzo");
                }
            }
        } else {
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
}
