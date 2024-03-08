package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class ReginaService {

    public static void controlloRegina (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (!((nuovaPosX == vecchiaPosX || nuovaPosY == vecchiaPosY) ||
                (Math.abs(nuovaPosX - vecchiaPosX) == Math.abs(nuovaPosY - vecchiaPosY)))) {
            throw new MossaNonValida("La regina può muoversi solo lungo linee rette");
        }

        if (nuovaPosX == vecchiaPosX) {
            int inizio = Math.min(vecchiaPosY, nuovaPosY) + 1;
            int fine = Math.max(vecchiaPosY, nuovaPosY);
            for (int i = inizio; i < fine; i++) {
                if (scacchiera.casella[vecchiaPosX][i].isOccupata()) {
                    throw new MossaNonValida("Pezzo in mezzo");
                }
            }
        } else if (nuovaPosY == vecchiaPosY) {
            int inizio = Math.min(vecchiaPosX, nuovaPosX) + 1;
            int fine = Math.max(vecchiaPosX, nuovaPosX);
            for (int i = inizio; i < fine; i++) {
                if (scacchiera.casella[i][vecchiaPosY].isOccupata()) {
                    throw new MossaNonValida("Pezzo in mezzo");
                }
            }
        } else {
            int startX = Math.min(vecchiaPosX, nuovaPosX) + 1;
            int startY = Math.min(vecchiaPosY, nuovaPosY) + 1;
            int endX = Math.max(vecchiaPosX, nuovaPosX);
            int endY = Math.max(vecchiaPosY, nuovaPosY);
            for (int i = startX, j = startY; i < endX && j < endY; i++, j++) {
                if (scacchiera.casella[i][j].isOccupata()) {
                    throw new MossaNonValida("Pezzo in mezzo");
                }
            }
        }
    }
}
