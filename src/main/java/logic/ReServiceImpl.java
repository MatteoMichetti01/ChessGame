package logic;

import domain.Casella;
import domain.Pezzo;
import domain.Re;
import domain.Scacchiera;

public class ReServiceImpl implements PezzoService<Re> {
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
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
        //verifica che il re non si metta in scacco da solo
        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() == null) {
            boolean autoscacco= controlloAutoScacco(scacchiera, nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY);
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
                if (i != nuovaPosX || j != nuovaPosY) {
                    if (scacchiera.casella[i][j].getPezzo() != null && scacchiera.casella[i][j].getPezzo().getColore().equals(colore)) {
                        if (scacchiera.casella[i][j].getPezzo().getNome().charAt(0) != 'r') {
                            try {
                                PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[i][j].getPezzo().getClass());
                                service.controlloMossa(nuovaPosX, nuovaPosY, i, j, scacchiera);
                                return true;
                            } catch (MossaNonValida m) {}
                        }
                    }
                }
            }
        }
        return false;
    }


    //controlla se il re non fa una mossa che lo metta in scacco. Per fare ciò controlla ogni pezzo del colore opposto, e se ne trova uno che può
    //raggiungere la casella del re che si trova in nuovaPosX,nuovaPosY, allora questa mossa mette in scacco il re per cui non è valida.
    public static boolean controlloAutoScacco(Scacchiera scacchiera, int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY) {
        String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
        for (int i=1; i<9; i++) {
            for (int j=1; j<9; j++) {
                if (scacchiera.casella[i][j].getPezzo() != null && !(scacchiera.casella[i][j].getPezzo().getColore().equals(colore))) {
                    if (scacchiera.casella[i][j].getPezzo().getNome().charAt(0) != 'r') {
                        try {
                            //simula la mossa
                            scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella(scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione(), scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo(), nuovaPosX,nuovaPosY,true);
                            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella("  ",scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), false);
                            //controlla la mossa
                            PezzoService<? extends Pezzo> service = PezzoServiceFactory.getPezzoService(scacchiera.casella[i][j].getPezzo().getClass());
                            service.controlloMossa(nuovaPosX,nuovaPosY,i,j,scacchiera);
                            //annulla la mossa (torna indietro)
                            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                            scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione() , false);
                            return true;
                        } catch (MossaNonValida m) {
                            //nel caso la mossa non va a buon fine essa deve essere in ogni caso annullata
                            scacchiera.casella[vecchiaPosX][vecchiaPosY] = new Casella(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPosizione(), scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo(), vecchiaPosX, vecchiaPosY, true);
                            scacchiera.casella[nuovaPosX][nuovaPosY] = new Casella("   ", scacchiera.casella[nuovaPosX][nuovaPosY].getPosizione() , false);
                        }
                    }
                }
            }
        }
        return false;
    }
}

