package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class PedoneService {

    public static void controlloPedone (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (nuovaPosY == vecchiaPosY && (vecchiaPosX == 2 || vecchiaPosX == 7)) {
            if (Math.abs(nuovaPosX - vecchiaPosX) > 2)
                throw new MossaNonValida("Mossa non valida, il pedone può avanzare alla prima mossa al massimo di due caselle");
        }
        else {
            if (nuovaPosY == vecchiaPosY && Math.abs(nuovaPosX - vecchiaPosX) > 1)
                throw new MossaNonValida("Mossa non valida, il pedone può avanzare una casella alla volta");
        }
        if (nuovaPosY != vecchiaPosY) {
            if (Math.abs(nuovaPosX - vecchiaPosX) != 1 || Math.abs(nuovaPosY - vecchiaPosY) != 1){
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti1");
            }
            if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo()==null)
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti2");
            if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null && scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore().equals(scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore()))
                throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti3");
        }

        String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
        if (colore.equals("bianco")) {
            if (vecchiaPosX < nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
        }
        else {
            if (vecchiaPosX > nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
        }

        if (scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() != null && nuovaPosY == vecchiaPosY)
            throw new MossaNonValida("il pedone mangia solo in diagonale");
    }
}
//&& scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo() == null && Math.abs(nuovaPosX - vecchiaPosX) != 1 && Math.abs(nuovaPosY - vecchiaPosY) != 1
