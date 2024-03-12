package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class PedoneService {

    public static Boolean controlloPedone (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        System.out.println("vecchia posx: "+vecchiaPosX);
        System.out.println("vecchia posy: "+vecchiaPosY);
        System.out.println("nuova posx: "+nuovaPosX);
        System.out.println("nuova posy: "+nuovaPosY);
        if(scacchiera.casella[nuovaPosX][nuovaPosY].getNome().equals("re")){

        }
        else {
            if (nuovaPosY == vecchiaPosY && (vecchiaPosX == 2 || vecchiaPosX == 7)) {
                if (Math.abs(nuovaPosX - vecchiaPosX) > 2)
                    throw new MossaNonValida("Mossa non valida, il pedone può avanzare alla prima mossa al massimo di due caselle");
            }
            else {
                if (nuovaPosY == vecchiaPosY && Math.abs(nuovaPosX - vecchiaPosX) > 1)
                    throw new MossaNonValida("Mossa non valida, il pedone può avanzare una casella alla volta");
            }
            if (nuovaPosY != vecchiaPosY) throw new MossaNonValida("Mossa non valida, il pedone può andare solo in avanti");

            String colore = scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore();
            if (colore.equals("bianco")) {
                if (vecchiaPosX < nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
            }
            else {
                if (vecchiaPosX > nuovaPosX) throw new MossaNonValida("il pedone non può andare indietro");
            }

        }
        return true;
    }
}
