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

        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null) {
            if (!(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))) {
                boolean controllo = controlloPezzoProtetto(scacchiera,nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY);
                if(controllo)
                    throw new MossaNonValida("il pezzo è protetto");
            }
        }
    }

    public static boolean controlloPezzoProtetto (Scacchiera scacchiera, int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY) {
        String colore = scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore();
        System.out.println(colore);
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
                    PezzoService p1 = new PezzoService(scacchiera);
                    if (scacchiera.casella[i][j].getPezzo().getNome().charAt(0) != 'r') {
                        try {
                            p1.controlloProtetto(scacchiera.casella[i][j].getPezzo().getNome(), nuovaPosX, nuovaPosY, i, j, scacchiera);
                            return true;
                        } catch (MossaNonValida m) {
                        }
                    }
                }
            }
        }
        return false;
    }
}
