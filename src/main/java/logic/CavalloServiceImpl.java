package logic;

import domain.Cavallo;
import domain.Scacchiera;

public class CavalloServiceImpl implements PezzoService<Cavallo> {
    @Override
    public void controlloMossa(int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida {
        if (!((nuovaPosX == vecchiaPosX + 2 && nuovaPosY == vecchiaPosY + 1) || (nuovaPosX == vecchiaPosX + 2 && nuovaPosY == vecchiaPosY - 1)
                || (nuovaPosX == vecchiaPosX - 2 && nuovaPosY == vecchiaPosY - 1) || (nuovaPosX == vecchiaPosX - 2 && nuovaPosY == vecchiaPosY + 1)
                || (nuovaPosX == vecchiaPosX + 1 && nuovaPosY == vecchiaPosY + 2) || (nuovaPosX == vecchiaPosX + 1 && nuovaPosY == vecchiaPosY - 2)
                || (nuovaPosX == vecchiaPosX - 1 && nuovaPosY == vecchiaPosY - 2) || (nuovaPosX == vecchiaPosX - 1 && nuovaPosY == vecchiaPosY + 2))) {
            throw new MossaNonValida("Il cavallo può muoversi solo ad L");
        }
        //if (MossaServiceImpl.pezzoInchiodato(nuovaPosX,nuovaPosY,vecchiaPosX,vecchiaPosY,scacchiera))
            //throw new MossaNonValida("Non puoi metterti in scacco da solo");
    }

}
