package logic;

import domain.Alfiere;
import domain.Scacchiera;

public class AlfiereServiceImpl implements PezzoService<Alfiere> {

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
