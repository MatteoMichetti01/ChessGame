package controller;

import domain.Casella;
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
        // verifica che il re non mangi un pezzo protetto.
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null) {
            if (!(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))) {
                boolean controllo = controlloPezzoProtetto(scacchiera,nuovaPosX,nuovaPosY);
                if(controllo)
                    throw new MossaNonValida("il pezzo è protetto");
            }
        }
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() == null) {
            boolean autoscacco= controlloAutoScacco(scacchiera, nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY);
            System.out.println("boolean state controllo" + autoscacco);
            if (autoscacco)
                throw new MossaNonValida("Non puoi darti scacco da solo");

        }

    }


    //controlla se un pezzo è protetto. Per fare ciò controlla ogni pezzo dello stesso colore, e se ne trova uno che può
    //raggiungere la casella del pezzo che si trova in nuovaPosX,nuovaPosY, allora quel pezzo è protetto.
    public static boolean controlloPezzoProtetto (Scacchiera scacchiera, int nuovaPosX, int nuovaPosY) {
        String colore = scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore();
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



public static boolean controlloAutoScacco(Scacchiera scacchiera, int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY) {
    String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
    for (int i=1; i<9; i++) {
        for (int j=1; j<9; j++) {
            if (scacchiera.casella[i][j].getPezzo() != null && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                PezzoService p1 = new PezzoService(scacchiera);
                if (scacchiera.casella[i][j].getPezzo().getNome().charAt(0) != 'r') {
                    try {
                        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX,nuovaPosY,true);
                        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("  ",scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), false);
                       /* if(colore.equals("bianco")){
                                if(scacchiera.casella[nuovaPosX-1][nuovaPosY+1].getPezzo()!=null||scacchiera.casella[nuovaPosX-1][nuovaPosY-1].getPezzo()!=null)
                                    if(!(scacchiera.casella[nuovaPosX-1][nuovaPosY+1].getPezzo().getColore().equals(colore) ) ^ !(scacchiera.casella[nuovaPosX-1][nuovaPosY-1].getPezzo().getColore().equals(colore)))
                                        if(scacchiera.casella[nuovaPosX-1][nuovaPosY+1].getPezzo().getNome().charAt(0)=='p'|| scacchiera.casella[nuovaPosX-1][nuovaPosY-1].getPezzo().getNome().charAt(0)=='p')
                                            return true;
                        }
                        else{
                            if(scacchiera.casella[nuovaPosX+1][nuovaPosY+1].getPezzo()!=null||scacchiera.casella[nuovaPosX+1][nuovaPosY-1].getPezzo()!=null)
                                 if(!(scacchiera.casella[nuovaPosX+1][nuovaPosY+1].getPezzo().getColore().equals(colore) )|| !(scacchiera.casella[nuovaPosX+1][nuovaPosY-1].getPezzo().getColore().equals(colore)))
                                     if(scacchiera.casella[nuovaPosX+1][nuovaPosY+1].getPezzo().getNome().charAt(0)=='p'|| scacchiera.casella[nuovaPosX+1][nuovaPosY-1].getPezzo().getNome().charAt(0)=='p')
                                         return true;
                        }

                        */
                        p1.controlloProtetto(scacchiera.casella[i][j].getPezzo().getNome(), nuovaPosX, nuovaPosY, i, j, scacchiera);
                        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione() , false);
                        return true;
                    } catch (MossaNonValida m) {
                        scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                        scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione() , false);
                    }
                }
            }
        }
    }
    return false;
}
}
