package logic;

import domain.Cavallo;
import domain.Scacchiera;

public class CavalloServiceImpl implements PezzoService<Cavallo> {
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if(!((nuovaPosX == vecchiaPosX+2 && nuovaPosY == vecchiaPosY+1 )|| (nuovaPosX == vecchiaPosX+2 && nuovaPosY == vecchiaPosY-1)
                ||(nuovaPosX == vecchiaPosX-2 && nuovaPosY == vecchiaPosY-1 )||(nuovaPosX == vecchiaPosX-2 && nuovaPosY == vecchiaPosY+1 )
                || (nuovaPosX == vecchiaPosX+1 && nuovaPosY == vecchiaPosY+2 )|| (nuovaPosX == vecchiaPosX+1 && nuovaPosY == vecchiaPosY-2)
                ||(nuovaPosX == vecchiaPosX-1 && nuovaPosY == vecchiaPosY-2)||(nuovaPosX == vecchiaPosX-1 && nuovaPosY == vecchiaPosY+2 ))){
            throw new MossaNonValida("Il cavallo può muoversi solo ad L");
        }
        if (scacchiera.casella[nuovaPosX][nuovaPosY].isOccupata() && scacchiera.casella[vecchiaPosX][vecchiaPosY].getPezzo().getColore().equals(scacchiera.casella[nuovaPosX][nuovaPosY].getPezzo().getColore()))
            throw new MossaNonValida("la casella è gia occupata");
    }
}
