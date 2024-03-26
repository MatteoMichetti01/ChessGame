package logic;
import domain.*;
import logic.MossaNonValida;

public interface PezzoService<T extends Pezzo> {
    public void controlloMossa (int nuovaPosX, int nuovaPosY, int vecchiaPosX, int vecchiaPosY, Scacchiera scacchiera) throws MossaNonValida;
}