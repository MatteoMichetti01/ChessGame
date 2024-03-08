package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class ReService {
    public static void controlloRe(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
// Verifica che il re si stia muovendo di una sola casella in qualsiasi direzione
        if (!(Math.abs(nuovaPosX - vecchiaPosX) <= 1 && Math.abs(nuovaPosY - vecchiaPosY) <= 1)) {
            throw new MossaNonValida("Il re può muoversi solo di una casella alla volta");
        }

        // Verifica se la destinazione è occupata dallo stesso colore o meno
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() &&
                scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore())) {
            throw new MossaNonValida("La casella è già occupata dallo stesso colore");
        }
    }
}
