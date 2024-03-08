package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class AlfiereService {

    public static void controlloAlfiere(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida  {

        if (Math.abs(nuovaPosX - vecchiaPosX) != Math.abs(nuovaPosY - vecchiaPosY))
            throw new MossaNonValida("L'alfiere può andare solo in diagonale");

        //CASO SPOSTAMENTO IN DIAGONALE IN AVANTI VERSO DESTRA
        if (vecchiaPosX > nuovaPosX && vecchiaPosY < nuovaPosY) {
            for (int i = vecchiaPosX; i > nuovaPosX; i--) {
                for (int j = vecchiaPosY; j < nuovaPosY; j++) {
                    if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                }
            }
        }

        //CASO SPOSTAMENTO IN DIAGONALE IN AVANTI VERSO SINISTRA
        if (vecchiaPosX > nuovaPosX && vecchiaPosY > nuovaPosY) {
            for (int i = vecchiaPosX; i > nuovaPosX; i--) {
                for (int j = vecchiaPosY; j > nuovaPosY; j--) {
                    if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                }
            }
        }

        //CASO SPOSTAMENTO IN DIAGONALE INDIETRO VERSO DESTRA
        if (vecchiaPosX < nuovaPosX && vecchiaPosY < nuovaPosY) {
            for (int i = vecchiaPosX; i < nuovaPosX; i++) {
                for (int j = vecchiaPosY; j < nuovaPosY; j++) {
                    if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                }
            }
        }

        //CASO SPOSTAMENTO IN DIAGONALE INDIETRO VERSO SINISTRA
        if (vecchiaPosX < nuovaPosX && vecchiaPosY > nuovaPosY) {
            for (int i = vecchiaPosX; i < nuovaPosX; i++) {
                for (int j = vecchiaPosY; j > nuovaPosY; j--) {
                    if (scacchiera.casella[i][j].isOccupata()) throw new MossaNonValida("pezzo in mezzo");
                }
            }
        }

    }
}
