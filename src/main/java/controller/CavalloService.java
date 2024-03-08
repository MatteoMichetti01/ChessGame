package controller;

import domain.Scacchiera;
import logic.MossaNonValida;

public class CavalloService {

    public static void controlloCavallo (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if(!((nuovaPosX == vecchiaPosX+2 && nuovaPosY == vecchiaPosY+1 )|| (nuovaPosX == vecchiaPosX+2 && nuovaPosY == vecchiaPosY-1)
                ||(nuovaPosX == vecchiaPosX-2 && nuovaPosY == vecchiaPosY-1 )||(nuovaPosX == vecchiaPosX-2 && nuovaPosY == vecchiaPosY+1 )
                || (nuovaPosX == vecchiaPosX+1 && nuovaPosY == vecchiaPosY+2 )|| (nuovaPosX == vecchiaPosX+1 && nuovaPosY == vecchiaPosY-2)
                ||(nuovaPosX == vecchiaPosX-1 && nuovaPosY == vecchiaPosY-2)||(nuovaPosX == vecchiaPosX-1 && nuovaPosY == vecchiaPosY+2 ))){
            throw new MossaNonValida("Il cavallo può muoversi solo ad L");
        }
    }
}
