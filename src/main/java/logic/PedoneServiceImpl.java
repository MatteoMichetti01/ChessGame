package logic;
import domain.*;
import domain.Scacchiera;

import java.util.Scanner;

public class PedoneServiceImpl implements PezzoService<Pedone> {

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


        SessioneGioco m1 = SessioneGioco.getInstance();
        //PROMOZIONE
        if (scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("bianco") && nuovaPosX == 1) {
            Promozione.promozione(m1.giocatore1, scacchiera, nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY);
        }
        if(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals("nero") && nuovaPosX == 8){
            Promozione.promozione(m1.giocatore2, scacchiera, nuovaPosX, nuovaPosY, vecchiaPosX, vecchiaPosY);
        }


    }

}
